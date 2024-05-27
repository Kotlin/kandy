package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.api.toColumnGroup
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder

/**
 * Represents a context specifically tailored for managing and visualizing grouped datasets.
 *
 * @property key `ColumnGroup` of key columns
 */
public class GroupByScope<T, G> @PublishedApi internal constructor(
    @PublishedApi
    internal val groupBy: GroupBy<T, G>,
    override val plotBuilder: MultiLayerPlotBuilder,
    override val datasetIndex: Int
): LayerCreatorScope(), GroupedDataScope<T, G> {

    @Suppress("UNCHECKED_CAST")
    public override val key: ColumnGroup<T> =
        groupBy.concatFixed().getColumns(*groupBy.keys.columnNames().toTypedArray()).toColumnGroup(
            "key"
        ) as ColumnGroup<T>

    override val layersInheritMappings: Boolean = true
}