package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.api.toColumnGroup
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

public class GroupByPlotBuilder<T, G> @PublishedApi internal constructor(
    @PublishedApi
    internal val groupBy: GroupBy<T, G>,
    internal val dataframe: DataFrame<G> = groupBy.concatFixed()
) : MultiLayerPlotBuilder(), ColumnsContainer<G> by dataframe, GroupedDataScope<T, G> {
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(DatasetHandler(GroupedData(
        dataframe, groupBy.keys.columnNames()
    )))

    @Suppress("UNCHECKED_CAST")
    public override val key: ColumnGroup<T> =
        dataframe.getColumns(*groupBy.keys.columnNames().toTypedArray()).toColumnGroup(
            "key"
        ) as ColumnGroup<T>
}
