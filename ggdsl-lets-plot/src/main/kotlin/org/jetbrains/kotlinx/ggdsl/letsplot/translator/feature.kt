package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FACET_X
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FACET_Y
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetGridFeature
import jetbrains.letsPlot.facet.facetGrid
import jetbrains.letsPlot.intern.Feature
import jetbrains.letsPlot.intern.OptionsMap

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

internal fun PlotFeature.wrap(featureBuffer: MutableList<Feature>) {
    // todo featureName
    //TODO check is le feature
    when (featureName) {
        FacetGridFeature.FEATURE_NAME -> {
            featureBuffer.add((this as FacetGridFeature).wrap())
        }
        else -> TODO()
    }
    //return featureBuffer
}