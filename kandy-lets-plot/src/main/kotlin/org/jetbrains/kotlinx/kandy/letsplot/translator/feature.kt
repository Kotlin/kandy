/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.letsplot.feature.*
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetGridFeature
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetWrapFeature
import org.jetbrains.kotlinx.kandy.letsplot.style.Theme
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.feature.LayerTooltips
import org.jetbrains.letsPlot.coord.coordCartesian
import org.jetbrains.letsPlot.coord.coordFixed
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

internal fun Theme.wrap(): OptionsMap {
    return when (this) {
        Theme.DARCULA -> flavorDarcula()
        Theme.SOLARIZED_LIGHT -> flavorSolarizedLight()
        Theme.SOLARIZED_DARK -> flavorSolarizedDark()
        Theme.HIGH_CONTRAST_LIGHT -> flavorHighContrastLight()
        Theme.HIGH_CONTRAST_DARK -> flavorHighContrastDark()
    }
}

internal fun Layout.wrap(featureBuffer: MutableList<Feature>) {
    val labs = labs(
        title, subtitle, caption
    )
    featureBuffer.addAll(labs.elements)
    size?.let {
        featureBuffer.add(ggsize(it.first, it.second))
    }
    style?.let {
        featureBuffer.add(it.wrap())
    }
    customStyle?.let {
        featureBuffer.add(it.wrap())
    }
    theme?.let {
        featureBuffer.add(it.wrap())
    }
}


internal fun CoordinatesTransformation.wrap(plot: Plot): OptionsMap? {
    val axes = plot.axes()
    val xLimits = axes[X]?.limits()
    val yLimits = axes[Y]?.limits()

    // If user doesn't adjust axes limits && coordinates, use Lets-Plot default (null)
    if (this is DefaultCoordinatesTransformation &&
        (xLimits == null || (xLimits.bothNull())) &&
        (yLimits == null || (yLimits.bothNull()))
    ) {
        return null
    }

    return when (this) {
        is DefaultCoordinatesTransformation, CartesianCoordinatesTransformation -> coordCartesian(
            xlim = xLimits,
            ylim = yLimits,
            flip = false
        )

        is CartesianFixedCoordinatesTransformation -> coordFixed(
            ratio = ratio,
            xlim = xLimits,
            ylim = yLimits,
            flip = false
        )

        is CartesianFlippedCoordinatesTransformation -> coordFlip(xlim = xLimits, ylim = yLimits)
        is CartesianFlippedFixedCoordinatesTransformation -> coordFixed(
            ratio = ratio,
            xlim = xLimits,
            ylim = yLimits,
            flip = true
        )

        is CustomCoordinatesTransformation -> error("unreachable")
    }
}

internal fun PlotFeature.wrap(featureBuffer: MutableList<Feature>, plot: Plot) {
    if (this is ExternalLetsPlotFeature) {
        featureBuffer += wrap(plot)
        return
    }

    // TODO(https://github.com/Kotlin/kandy/issues/409)
    when (featureName) {
        FacetGridFeature.FEATURE_NAME -> {
            featureBuffer.add((this as FacetGridFeature).wrap())
        }

        FacetWrapFeature.FEATURE_NAME -> {
            featureBuffer.add((this as FacetWrapFeature).wrap())
        }

        CoordinatesTransformation.FEATURE_NAME -> {
            (this as CoordinatesTransformation).wrap(plot)?.let { featureBuffer.add(it) }
        }

        Layout.NAME -> {
            (this as Layout).wrap(featureBuffer)
        }

        else -> error("Unexpected feature: $featureName")
    }
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
    // TODO(https://github.com/Kotlin/kandy/issues/321)
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
