package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public class DataFramePlotContext<T>(
    private val dataFrame: DataFrame<T>
) : LayerPlotContext, ColumnsContainer<T> by dataFrame {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val plotContext: PlotContext = this
    override val datasetIndex: Int = 0
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(
        DatasetHandler(NamedData(dataFrame))
    )
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    public fun <C> columns(selector: ColumnsSelector<T, C>): List<DataColumn<C>> = dataFrame.get(selector)
    public fun <C> columns(vararg columns: String): List<AnyCol> = dataFrame.getColumns(*columns)
}