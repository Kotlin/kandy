package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup

/// todo docs
// must be LayerCreatorScope, marker of containing grouped data
// (needed for transformations, especially statistics)
public sealed interface GroupedDataScope<T, R>: ColumnsContainer<R> {
    public val key: ColumnGroup<T>
}
