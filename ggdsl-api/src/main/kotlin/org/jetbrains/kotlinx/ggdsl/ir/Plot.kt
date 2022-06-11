package org.jetbrains.kotlinx.ggdsl.ir

import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

/**
 * Plot consists of a set of layers.
 *
 * @param layers the [List] of [Layer]
 * @param layout layout settings
 * @param features the [Map] of the plot features; keys are feature names,
 * values are features with corresponding names.
 */
data class Plot(
    val dataset: NamedData, // todo remove???
    val layers: List<Layer>,
    val layout: Layout,
    val features: Map<FeatureName, PlotFeature> = emptyMap()
)
