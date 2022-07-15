package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import jetbrains.letsPlot.*
import jetbrains.letsPlot.facet.facetGrid
import jetbrains.letsPlot.facet.facetWrap
import jetbrains.letsPlot.intern.Feature
import jetbrains.letsPlot.intern.OptionsMap
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.tooltips.TooltipOptions
import jetbrains.letsPlot.tooltips.layerTooltips
import jetbrains.letsPlot.tooltips.tooltipsNone
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FACET_X
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FACET_Y
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetGridFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetWrapFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.LayerTooltips

internal fun FacetGridFeature.wrap(): OptionsMap {
    return facetGrid(
        x = mappings[FACET_X]?.id,
        y = mappings[FACET_Y]?.id,
        xOrder = xOrder.value,
        yOrder = yOrder.value,
        /* TODO
        xFormat = xFormat,
        yFormat = yFormat
         */
    )
}

internal fun FacetWrapFeature.wrap(): OptionsMap {
    return facetWrap(
        facets.map { it.id },
        nCol,
        nRow,
        scalesSharing.name,
        order.value,
        /* TODO
       format
        */
        dir = direction.name

    )
}

internal fun PlotFeature.wrap(featureBuffer: MutableList<Feature>) {
    // todo featureName
    //TODO check is le feature
    when (featureName) {
        FacetGridFeature.FEATURE_NAME -> {
            featureBuffer.add((this as FacetGridFeature).wrap())
        }
        FacetWrapFeature.FEATURE_NAME -> {
            featureBuffer.add((this as FacetWrapFeature).wrap())
        }
        else -> TODO()
    }
    //return featureBuffer
}


internal fun Position.wrap(): PosOptions {
    return when (this) {
        is Position.Identity -> return Pos.identity
        is Position.Stack -> return Pos.stack
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
    var buffer =  layerTooltips(*(variables.map { it.id }.toTypedArray()))
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