package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.X_MAX

public interface WithXMax : BindingContext {
    public fun <T> xMax(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MAX, value)
    }

    public fun <T> xMax(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MAX, column.name(), null)
    }

    public fun <T> xMax(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_MAX,
            values.toList(),
            null,
            null
        )
    }
}