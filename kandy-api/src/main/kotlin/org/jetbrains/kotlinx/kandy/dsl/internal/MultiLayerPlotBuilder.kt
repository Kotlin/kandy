package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

public abstract class MultiLayerPlotBuilder internal constructor(): LayerCreatorScope(), PlotBuilder {

    @PublishedApi
    internal fun addLayer(layer: Layer) {
        layers.add(layer)
    }

    internal abstract val datasetHandlers: MutableList<DatasetHandler>
    @PublishedApi
    internal val layers: MutableList<Layer> = mutableListOf()
    internal val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    override val plotBuilder: MultiLayerPlotBuilder
        get() = this
    override val datasetIndex: Int
        get() = 0
    override val layersInheritMappings: Boolean
        get() = true

    internal open val bindingHandler: BindingHandlerDefault = BindingHandlerDefault { datasetHandler }
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

    internal fun addDataset(df: DataFrame<*>): Int {
        datasetHandlers.add(DatasetHandler(NamedData(df)))
        return datasetHandlers.lastIndex
    }

    internal fun addDataset(gb: GroupBy<*, *>, initialBuffer: DataFrame<*>): Int {
        datasetHandlers.add(DatasetHandler(GroupedData(gb), initialBuffer))
        return datasetHandlers.lastIndex
    }
}