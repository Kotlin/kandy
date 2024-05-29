package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.api.toColumnGroup
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData

/**
 * Represents a context specifically tailored for managing and visualizing grouped datasets.
 *
 * @property key `ColumnGroup` of key columns
 */
public class GroupByScope<T, G> @PublishedApi internal constructor(
    @PublishedApi
    internal val groupBy: GroupBy<T, G>,
    initialBuffer: DataFrame<*>,
    override val plotBuilder: MultiLayerPlotBuilder,
    internal val dataframe: DataFrame<G> = groupBy.concatFixed()
): LayerCreatorScope(), GroupedDataScope<T, G>, ColumnsContainer<G> by dataframe {

    @Suppress("UNCHECKED_CAST")
    public override val key: ColumnGroup<T> =
        dataframe.getColumns(*groupBy.keys.columnNames().toTypedArray()).toColumnGroup(
            "key"
        ) as ColumnGroup<T>

    override val datasetIndex: Int = plotBuilder.addDataset(
        GroupedData(dataframe, groupBy.keys.columnNames()), initialBuffer
    )

    override val layersInheritMappings: Boolean = true
}
