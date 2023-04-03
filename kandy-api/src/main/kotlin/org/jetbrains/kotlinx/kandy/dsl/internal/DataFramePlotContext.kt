package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

public class DataFramePlotContext<T>(
    private val dataFrame: DataFrame<T>
) : org.jetbrains.kotlinx.kandy.dsl.internal.LayerPlotContext, ColumnsContainer<T> by dataFrame {
    override val bindingCollector: org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector =
        org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val plotContext: org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext = this
    override val datasetIndex: Int = 0
    override val datasetHandlers: MutableList<org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler> = mutableListOf(
        org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler(NamedData(dataFrame))
    )
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    public fun <C> columns(selector: ColumnsSelector<T, C>): List<DataColumn<C>> = dataFrame.get(selector)
    public fun <C> columns(vararg columns: String): List<AnyCol> = dataFrame.getColumns(*columns)

    public inline fun groupBy(
        vararg columnReferences: ColumnReference<*>,
        block: org.jetbrains.kotlinx.kandy.dsl.internal.GroupedContext.() -> Unit
    ) {
        datasetHandlers.add(
            org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler(
                GroupedData(
                    datasetHandler.initialDataset as NamedData,
                    columnReferences.map { it.name() })
            )
        )
        org.jetbrains.kotlinx.kandy.dsl.internal.GroupedContext(
            datasetHandlers.size - 1,
            this
        ).apply(block)
    }
}