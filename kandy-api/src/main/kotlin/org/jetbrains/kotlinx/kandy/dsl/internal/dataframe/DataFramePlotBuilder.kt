package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder
import org.jetbrains.kotlinx.kandy.ir.data.TableData

public class DataFramePlotBuilder<T> @PublishedApi internal constructor(
    @PublishedApi
    internal val dataFrame: DataFrame<T>,
) : MultiLayerPlotBuilder(), ColumnsContainer<T> by dataFrame {
    override val datasetBuilders: MutableList<DatasetBuilder> = mutableListOf(NamedDataBuilder(dataFrame))
    /**
     * Fetches the specified columns from the dataframe.
     *
     * @param selector a selector to determine the columns to be fetched.
     * @return a list of selected data columns.
     */
    public fun <C> columns(selector: ColumnsSelector<T, C>): List<DataColumn<C>> = dataFrame.get(selector)

    /**
     * Fetches the specified columns from the dataframe by their names.
     *
     * @param columns names of the desired columns.
     * @return a list of selected data columns.
     */
    public fun <C> columns(vararg columns: String): List<AnyCol> = dataFrame.getColumns(*columns)

    /**
     * Creates and initializes a new context with the dataframe grouped by the specified column names.
     *
     * @param columns the column names to group the dataframe by.
     * @param block a lambda with receiver block that configures the new grouped context.
     */
    public inline fun groupBy(
        columns: Iterable<String>,
        block: GroupByScope<T, T>.() -> Unit
    ) {
        val groupBy = dataFrame.groupBy(*columns.toList().toTypedArray())
        GroupByScope(
            groupBy,
            this,
            addDataset(groupBy)
        ).apply(block)
    }

    /**
     * Creates and initializes a new context with the dataframe grouped by the specified column names.
     *
     * @param columns the column names to group the dataframe by.
     * @param block a lambda with receiver block that configures the new grouped context.
     */
    public inline fun groupBy(
        vararg columns: String,
        block: GroupByScope<T, T>.() -> Unit
    ): Unit = groupBy(columns.toList(), block)

    /**
     * Creates and initializes a new context with the dataframe grouped by the given column references.
     *
     * @param columnReferences references to the columns to group by.
     * @param block a lambda with receiver block that configures the new grouped context.
     */
    public inline fun groupBy(
        vararg columnReferences: ColumnReference<*>,
        block: GroupByScope<T, T>.() -> Unit
    ): Unit = groupBy(columnReferences.map { it.name() }, block)

    /**
     * Creates and initializes a new context with the dataframe grouped by the given column references.
     *
     * @param columnReferences a list of references to the columns to group by.
     * @param block a lambda with receiver block that configures the new grouped context.
     */
    public inline fun groupBy(
        columnReferences: List<ColumnReference<*>>,
        block: GroupByScope<T, T>.() -> Unit
    ): Unit = groupBy(columnReferences.map { it.name() }, block)

    @PublishedApi
    internal fun addDataset(groupBy: GroupBy<*, *>): Int {
        datasetBuilders.add(DatasetBuilderImpl.fromData(groupBy, datasetBuilder as DatasetBuilderImpl))
        return datasetBuilders.lastIndex
    }

    override fun addDataset(dataset: TableData, initialBuilder: DatasetBuilder?): Int {
        datasetBuilders.add(DatasetBuilderImpl.fromData(dataset, datasetBuilder as DatasetBuilderImpl))
        return datasetBuilders.lastIndex
    }

    override fun addEmptyDataset(): Int {
        return addDataset(NamedData(DataFrame.Empty))
    }
}
