package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * An abstract class representing a builder for creating plots by adding layers.
 * This class provides methods for adding layers, datasets, and other plot features.
 */
public abstract class MultiLayerPlotBuilder internal constructor(): LayerCreatorScope(), PlotBuilder {
    /**
     * Adds a new layer.
     */
    @PublishedApi
    internal fun addLayer(layer: Layer) {
        layers.add(layer)
    }

    internal abstract val datasetHandlers: MutableList<DatasetHandler>
    @PublishedApi
    internal val layers: MutableList<Layer> = mutableListOf()
    @PublishedApi
    internal val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    override val plotBuilder: MultiLayerPlotBuilder
        get() = this
    override val datasetIndex: Int
        get() = 0
    override val layersInheritMappings: Boolean
        get() = true

    internal val bindingHandler: BindingHandler = BindingHandler { datasetHandler }
    internal val bindingCollector
        get() = bindingHandler.bindingCollector

    override fun toPlot(): Plot {
        check (layers.isNotEmpty()) { "No layers in plot." }
        return Plot(
            datasetHandlers.map { it.data() },
            layers,
            bindingCollector.mappings,
            bindingCollector.settings,
            plotFeatures,
            bindingCollector.freeScales
        )
    }

    /**
     * Adds a new dataset.
     *
     * @return new dataset handler index in [datasetHandlers].
     */
    internal fun addDataset(dataset: TableData, initialBuffer: DataFrame<*>? = null): Int {
        datasetHandlers.add(DatasetHandler(dataset, initialBuffer))
        return datasetHandlers.lastIndex
    }

}