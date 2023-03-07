package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.MIDDLE

public interface WithMiddle : BindingContext {
    public fun <T> middle(value: T): PositionalSetting<T> {
        return addPositionalSetting(MIDDLE, value)
    }

    public fun <T> middle(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, column.name(), null)
    }

    public fun <T> middle(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            MIDDLE,
            values.toList(),
            null,
            null
        )
    }
}