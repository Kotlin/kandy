package org.jetbrains.kotlinx.ggdsl.dataframe.internal

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer

/**
 * Creates a [ColumnPointer] from this [ColumnReference].
 *
 * @return a column pointer with the same name and type as a receiver [ColumnReference].
 */
public fun <T> ColumnReference<T>.toColumnPointer(): ColumnPointer<T> {
    return ColumnPointer(name())
}