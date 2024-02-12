/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.letsplot.feature.CoordFlip
import org.jetbrains.kotlinx.kandy.letsplot.feature.Layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.Reversed
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetGridFeature
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetWrapFeature
import org.jetbrains.kotlinx.kandy.letsplot.theme.Flavor
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.feature.LayerTooltips
import org.jetbrains.letsPlot.coord.coordFlip
import org.jetbrains.letsPlot.facet.facetGrid
import org.jetbrains.letsPlot.facet.facetWrap
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.pos.*
import org.jetbrains.letsPlot.themes.*
import org.jetbrains.letsPlot.tooltips.TooltipOptions
import org.jetbrains.letsPlot.tooltips.layerTooltips
import org.jetbrains.letsPlot.tooltips.tooltipsNone

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

internal fun Flavor.wrap(): OptionsMap {
    return when (this) {
        Flavor.DARCULA -> flavorDarcula()
        Flavor.SOLARIZED_LIGHT -> flavorSolarizedLight()
        Flavor.SOLARIZED_DARK -> flavorSolarizedDark()
        Flavor.HIGH_CONTRAST_LIGHT -> flavorHighContrastLight()
        Flavor.HIGH_CONTRAST_DARK -> flavorHighContrastDark()
    }
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
    flavor?.let {
        featureBuffer.add(it.wrap())
    }
}

internal fun PlotFeature.wrap(featureBuffer: MutableList<Feature>) {
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

        /*
        GatheringList.FEATURE_NAME -> {
            (this as GatheringList).gatheringList.forEach {
                it.toLayer().wrap(featureBuffer, null)
            }
        }

         */

        else -> TODO()
    }
    //return featureBuffer
}

internal fun Position.wrap(): PosOptions {
    return when (this) {
        is Position.Identity -> return positionIdentity
        is Position.Stack -> return positionStack()
        is Position.Dodge -> return positionDodge(width)
        is Position.Jitter -> return positionJitter(width, height)
        is Position.Nudge -> return positionNudge(x, y)
        is Position.JitterDodge -> positionJitterDodge(dodgeWidth, jitterWidth, jitterHeight)
    }
}

internal fun LayerTooltips.wrap(): TooltipOptions {
    if (!enable) {
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
    lines?.forEach {
        buffer = buffer.line(it)
    }
    // TODO: temporary solution (#321), moves side tooltips to main
    if (variables.isNotEmpty() || !lines.isNullOrEmpty()) {
        buffer = buffer.disableSplitting()
    }

    return buffer
}

internal fun Reversed.wrap(): String = if (value) {
    "y"
} else {
    "x"
}
