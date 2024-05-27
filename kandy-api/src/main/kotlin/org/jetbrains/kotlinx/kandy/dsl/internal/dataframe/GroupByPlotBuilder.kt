package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.api.toColumnGroup
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder

public class GroupByPlotBuilder<T, G> @PublishedApi internal constructor(
    @PublishedApi
    internal val groupBy: GroupBy<T, G>,
) : MultiLayerPlotBuilderImpl(), ColumnsContainer<G> by groupBy.concat(), GroupedDataScope<T, G> {
    override val datasetBuilders: MutableList<DatasetBuilder> = mutableListOf(DatasetBuilderImpl.fromData(groupBy))

    @Suppress("UNCHECKED_CAST")
    public override val key: ColumnGroup<T> =
        groupBy.concatFixed().getColumns(*groupBy.keys.columnNames().toTypedArray()).toColumnGroup(
            "key"
        ) as ColumnGroup<T>
}
