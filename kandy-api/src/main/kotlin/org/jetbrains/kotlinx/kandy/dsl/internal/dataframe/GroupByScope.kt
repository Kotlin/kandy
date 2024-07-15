package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.api.toColumnGroup
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder

/**
 * Represents a plot builder data scope with grouped dataset
 * created by [DataFramePlotBuilder.groupBy].
 *
 * @param T The type of the key columns.
 * @param G The type of the data columns.
 *
 * @property key `ColumnGroup` of key columns.
 */
public class GroupByScope<T, G> @PublishedApi internal constructor(
    @PublishedApi
    internal val groupBy: GroupBy<T, G>,
    override val plotBuilder: MultiLayerPlotBuilder,
    override val datasetIndex: Int,
    internal val dataframe: DataFrame<G> = groupBy.concatFixed()
) : LayerCreatorScope(), GroupedDataScope<T, G>, ColumnsContainer<G> by dataframe {

    @Suppress("UNCHECKED_CAST")
    public override val key: ColumnGroup<T> =
        dataframe.getColumns(*groupBy.keys.columnNames().toTypedArray()).toColumnGroup(
            "key"
        ) as ColumnGroup<T>

    override val layersInheritMappings: Boolean = true
}
