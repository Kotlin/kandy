package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup

/**
 * Represents a scope specifically tailored for managing and visualizing grouped datasets.
 *
 * @param T The type of the key columns.
 * @param G The type of the data columns.
 *
 * @property key The key columns as [ColumnGroup].
 */
public sealed interface GroupedDataScope<T, G>: ColumnsContainer<G> {
    public val key: ColumnGroup<T>
}
