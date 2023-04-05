package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * Standard plotting context with [DataFrame] as an initial dataset.
 * Implements [ColumnsContainer] (delegated to [dataFrame]), which allows to use the columns of [dataFrame].
 *
 * @param dataFrame initial [DataFrame].
 */
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

    /**
     * Creates a new context with dataframe grouped by given columns as dataset.
     *
     * @param columns grouping column names
     */
    public inline fun groupBy(
        columns: Iterable<String>,
        block: GroupedContext.() -> Unit
    ) {
        datasetHandlers.add(
            DatasetHandler(
                GroupedData(
                    datasetHandler.initialDataset as NamedData,
                    columns.toList()
                )
            )
        )
        GroupedContext(
            datasetHandlers.size - 1,
            this
        ).apply(block)
    }

    /**
     * Creates a new context with dataframe grouped by given columns as dataset.
     *
     * @param columns grouping column names
     */
    public inline fun groupBy(
        vararg columns: String,
        block: GroupedContext.() -> Unit
    ): Unit = groupBy(columns.toList(), block)

    /**
     * Creates a new context with dataframe grouped by given columns as dataset.
     *
     * @param columnReferences grouping columns
     */
    public inline fun groupBy(
        vararg columnReferences: ColumnReference<*>,
        block: GroupedContext.() -> Unit
    ):  Unit = groupBy(columnReferences.map { it.name() }, block)

    /**
     * Creates a new context with dataframe grouped by given columns as dataset.
     *
     * @param columnReferences grouping columns
     */
    public inline fun groupBy(
        columnReferences: List<ColumnReference<*>>,
        block: GroupedContext.() -> Unit
    ):  Unit = groupBy(columnReferences.map { it.name() }, block)
}