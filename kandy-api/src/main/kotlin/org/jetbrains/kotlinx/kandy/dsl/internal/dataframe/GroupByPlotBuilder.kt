package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.api.toColumnGroup
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder

/**
 * Represents a plotting context where the data is initially grouped using [GroupBy].
 * This context facilitates the creation and management of visualizations with data that's grouped by certain criteria.
 * It also provides column access via delegation to the concatenated representation of [groupBy].
 *
 * @param T The type of the key columns.
 * @param G The type of the data columns.
 *
 * @property key The key columns as a ColumnGroup.
 */
public class GroupByPlotBuilder<T, G> @PublishedApi internal constructor(
    @PublishedApi
    internal val groupBy: GroupBy<T, G>,
    internal val dataframe: DataFrame<G> = groupBy.concatFixed()
) : MultiLayerPlotBuilderImpl(), ColumnsContainer<G> by dataframe, GroupedDataScope<T, G> {
    override val datasetBuilders: MutableList<DatasetBuilder> = mutableListOf(DatasetBuilderImpl(groupBy))

    @Suppress("UNCHECKED_CAST")
    public override val key: ColumnGroup<T> =
        dataframe.getColumns(*groupBy.keys.columnNames().toTypedArray()).toColumnGroup(
            "key"
        ) as ColumnGroup<T>
}
