/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.dsl.categorical
import org.jetbrains.kotlinx.ggdsl.dsl.column.invoke
import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetGridFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetWrapFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.series.Gathering
import org.jetbrains.kotlinx.ggdsl.letsplot.series.GatheringList
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.LayerTooltips
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.letsPlot.coord.coordFlip
import org.jetbrains.letsPlot.facet.facetGrid
import org.jetbrains.letsPlot.facet.facetWrap
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.pos.*
import org.jetbrains.letsPlot.tooltips.TooltipOptions
import org.jetbrains.letsPlot.tooltips.layerTooltips
import org.jetbrains.letsPlot.tooltips.tooltipsNone
import kotlin.reflect.KType
import kotlin.reflect.typeOf

internal fun FacetGridFeature.wrap(): OptionsMap {
    return facetGrid(
        x = x,
        y = y,
        scales = scalesSharing?.name,
        xOrder = xOrder.value,
        yOrder = yOrder.value,
        xFormat = xFormat,
        yFormat = yFormat
    )
}

internal fun FacetWrapFeature.wrap(): OptionsMap {
    return facetWrap(
        facets = facets,
        ncol = nCol,
        nrow = nRow,
        scales = scalesSharing.name,
        order = orders.map { it.value },
        format = formats,
        dir = direction.name
    )
}

internal fun Layout.wrap(featureBuffer: MutableList<Feature>) {
    val labs = labs(
        title, subtitle, caption, xAxisLabel, yAxisLabel
    )
    featureBuffer.addAll(labs.elements)
    size?.let {
        featureBuffer.add(ggsize(it.first, it.second))
    }
    theme?.let {
        featureBuffer.add(it.wrap())
    }
    customTheme?.let {
        featureBuffer.add(it.wrap())
    }
}

internal fun PlotFeature.wrap(featureBuffer: MutableList<Feature>, columnTypes: Map<String, KType>) {
    if (this is ExternalLetsPlotFeature) {
        featureBuffer += wrap()
        return
    }
    // todo featureName
    //TODO check is lp feature
    when (featureName) {
        FacetGridFeature.FEATURE_NAME -> {
            featureBuffer.add((this as FacetGridFeature).wrap())
        }

        FacetWrapFeature.FEATURE_NAME -> {
            featureBuffer.add((this as FacetWrapFeature).wrap())
        }

        CoordFlip.FEATURE_NAME -> {
            featureBuffer.add(coordFlip())
        }

        Layout.NAME -> {
            (this as Layout).wrap(featureBuffer)
        }

        GatheringList.FEATURE_NAME -> {
            (this as GatheringList).gatheringList.forEach {
                it.toLayer().wrap(featureBuffer, false, columnTypes)
            }
        }

        else -> TODO()
    }
    //return featureBuffer
}

//internal val palette = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE)

internal fun Gathering.toLayer(): Layer {
    val size = data.nameToValues.values.first().values.size
    val mappingAesNames = series.first().settings.keys
    val xBuffer = mutableListOf<Any>()
    val yBuffer = mutableListOf<Any>()
    val labelBuffer = mutableListOf<String>()
    val scaleBuffer = mappingAesNames.associateWith {
        mutableListOf<String>() to mutableListOf<Any>()
    }

    val xType = series.first().mappings[X]!!.domainType
    val yType = series.first().mappings[Y]!!.domainType

    series.forEach {series ->
        xBuffer.addAll(data.nameToValues[series.mappings[X]!!.wrap().second]!!.values)
        yBuffer.addAll(data.nameToValues[series.mappings[Y]!!.wrap().second]!!.values)
        labelBuffer.addAll(List(size){series.label})
        series.settings.forEach { (aesName, setting) ->
            scaleBuffer[aesName]!!.let {
                it.first.add(series.label)
                it.second.add((setting as NonPositionalSetting<*>).value)
            }
        }
    }


    val nonPosScales: Map<AesName, Mapping> = if (scaleBuffer.isEmpty()) {
        mapOf(COLOR to ScaledNonPositionalUnspecifiedMapping<String, Color>(
            COLOR,
            "label"<String>().scaled(categorical()),
            typeOf<String>()
        ))
    } else {
        scaleBuffer.map { (aesName, buffer) ->
            aesName to ScaledNonPositionalMapping<String, Any>(
                aesName,
                "label"<String>().scaled(
                    categorical(
                        buffer.first,
                        buffer.second
                    )
                ),
                typeOf<String>()
            )
        }.toMap()
    }

    val newData = NamedData(
        mapOf(
            "x" to TypedList(typeOf<Double>(), xBuffer),
            "y" to TypedList(typeOf<Double>(), yBuffer),
            "label" to TypedList(typeOf<String>(), labelBuffer)
        )
    )
    return Layer(
        newData,
        geom,
        mapOf(
            X to ScaledUnspecifiedDefaultPositionalMapping<Any>(
                X,
                "x"<Any>().scaled(),
                xType
            ),
            Y to ScaledUnspecifiedDefaultPositionalMapping<Any>(
                Y,
                "y"<Any>().scaled(),
                yType
            )
        ) + nonPosScales,
        globalSettings,
        mapOf(Position.FEATURE_NAME to position)
    )
}


internal fun Position.wrap(): PosOptions {
    return when (this) {
        is Position.Identity -> return positionIdentity
        is Position.Stack -> return positionStack
        is Position.Dodge -> return positionDodge(width)
        is Position.Jitter -> return positionJitter(width, height)
        is Position.Nudge -> return positionNudge(x, y)
        is Position.JitterDodge -> positionJitterDodge(dodgeWidth, jitterWidth, jitterHeight)
    }
}

internal fun LayerTooltips.wrap(): TooltipOptions {
    if (hide) {
        return tooltipsNone
    }
    var buffer = layerTooltips(*(variables.toTypedArray()))
    title?.let {
        buffer = buffer.title(it)
    }
    anchor?.let {
        buffer = buffer.anchor(it.value)
    }
    minWidth?.let {
        buffer = buffer.minWidth(it)
    }
    formats.forEach {
        buffer = buffer.format(it.first, it.second)
    }
    lines.forEach {
        buffer = buffer.line(it)
    }
    return buffer
}

internal fun Reversed.wrap(): String = if (value) {
    "y"
} else {
    "x"
}