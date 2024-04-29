package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup

/// todo docs
// must be LayerCreatorScope, marker for containing grouped data
public sealed interface GroupedDataScope<T> {
    public val key: ColumnGroup<T>
}
