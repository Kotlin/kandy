package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.UPPER

public interface WithUpper : BindingContext {
    public fun <T> upper(value: T): PositionalSetting<T> {
        return addPositionalSetting(UPPER, value)
    }

    public fun <T> upper(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, column.name(), null)
    }

    public fun <T> upper(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            UPPER,
            values.toList(),
            null,
            null
        )
    }
}