package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

@PublishedApi
internal val LayerBuilder.datasetBuilder: DatasetBuilder
        get() = when (this) {
            is LayerBuilderImpl -> datasetBuilder
            is SingleLayerPlotBuilder -> datasetBuilder
        }

@PublishedApi
internal val LayerBuilder.layerFeatures: MutableMap<FeatureName, LayerFeature>
    get() = when (this) {
        is LayerBuilderImpl -> layerFeatures
        is SingleLayerPlotBuilder -> layerFeatures
    }

@PublishedApi
internal val LayerBuilder.bindingHandler: BindingHandler
    get() = when (this) {
        is LayerBuilderImpl -> bindingHandler
        is SingleLayerPlotBuilder -> bindingHandler
    }

@PublishedApi
internal val PlotBuilder.datasetBuilder: DatasetBuilder
    get() = when (this) {
        is MultiLayerPlotBuilder -> datasetBuilder
        is CustomPlotBuilder -> datasetBuilder
    }

@PublishedApi
internal val PlotBuilder.plotFeatures: MutableMap<FeatureName, PlotFeature>
    get() = when (this) {
        is MultiLayerPlotBuilder -> plotFeatures
        is CustomPlotBuilder -> plotFeatures
    }

@PublishedApi
internal val PlotBuilder.bindingHandler: BindingHandler
    get() = when (this) {
        is MultiLayerPlotBuilder -> bindingHandler
        is CustomPlotBuilder -> bindingHandler
    }

