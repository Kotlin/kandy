package org.jetbrains.kotlinx.ggdsl.dataframe.internal

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer

public fun <T : Any> ColumnReference<T>.toColumnPointer(): ColumnPointer<T> {
    return ColumnPointer(name())
}