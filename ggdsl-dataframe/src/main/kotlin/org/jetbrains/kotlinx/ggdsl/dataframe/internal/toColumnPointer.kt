package org.jetbrains.kotlinx.ggdsl.dataframe.internal

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference

/**
 * Creates a [ColumnReference] from this [ColumnReference].
 *
 * @return a column pointer with the same name and type as a receiver [ColumnReference].
 */
public fun <T> ColumnReference<T>.toColumnReference(): ColumnReference<T> {
    return ColumnReference(name())
}