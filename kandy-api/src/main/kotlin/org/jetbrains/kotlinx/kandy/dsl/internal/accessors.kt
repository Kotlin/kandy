package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

@PublishedApi
internal val LayerBuilder.datasetBuilder: DatasetBuilder
    get() = when (this) {
        is LayerBuilderImpl -> this.datasetBuilder
        is SingleLayerPlotBuilder -> this.datasetBuilder
    }

@PublishedApi
internal val LayerBuilder.layerFeatures: MutableMap<FeatureName, LayerFeature>
    get() = when (this) {
        is LayerBuilderImpl -> this.layerFeatures
        is SingleLayerPlotBuilder -> this.layerFeatures
    }

@PublishedApi
internal val LayerBuilder.bindingHandler: BindingHandler
    get() = when (this) {
        is LayerBuilderImpl -> this.bindingHandler
        is SingleLayerPlotBuilder -> this.bindingHandler
    }

@PublishedApi
internal val PlotBuilder.datasetBuilder: DatasetBuilder
    get() = when (this) {
        is MultiLayerPlotBuilder -> this.datasetBuilder
        is CustomPlotBuilder -> this.datasetBuilder
    }

@PublishedApi
internal val PlotBuilder.plotFeatures: MutableMap<FeatureName, PlotFeature>
    get() = when (this) {
        is MultiLayerPlotBuilder -> this.plotFeatures
        is CustomPlotBuilder -> this.plotFeatures
    }

@PublishedApi
internal val PlotBuilder.bindingHandler: BindingHandler
    get() = when (this) {
        is MultiLayerPlotBuilder -> this.bindingHandler
        is CustomPlotBuilder -> this.bindingHandler
    }

