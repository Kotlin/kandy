package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

@PublishedApi
internal val LayerBuilder.datasetHandler: DatasetHandler
        get() = when (this) {
            is LayerBuilderImpl -> datasetHandler
            is SingleLayerPlotBuilder -> datasetHandler
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
        is LayerBuilderImpl -> bindingContext
        is SingleLayerPlotBuilder -> bindingContext
    }

@PublishedApi
internal val PlotBuilder.datasetHandler: DatasetHandler
    get() = when (this) {
        is MultiLayerPlotBuilder -> datasetHandler
        is SingleLayerPlotBuilder -> datasetHandler
    }

@PublishedApi
internal val PlotBuilder.plotFeatures: MutableMap<FeatureName, PlotFeature>
    get() = when (this) {
        is MultiLayerPlotBuilder -> plotFeatures
        is SingleLayerPlotBuilder -> plotFeatures
    }


