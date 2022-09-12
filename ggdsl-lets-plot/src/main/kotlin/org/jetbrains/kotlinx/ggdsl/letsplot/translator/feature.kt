/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.CoordFlip
import org.jetbrains.kotlinx.ggdsl.letsplot.Reversed
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetGridFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetWrapFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.LayerTooltips
import org.jetbrains.letsPlot.coord.coordFlip
import org.jetbrains.letsPlot.facet.facetGrid
import org.jetbrains.letsPlot.facet.facetWrap
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.pos.*
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

internal fun PlotFeature.wrap(featureBuffer: MutableList<Feature>) {
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

        else -> TODO()
    }
    //return featureBuffer
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