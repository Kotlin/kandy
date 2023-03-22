package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LOWER

public interface WithLower : BindingContext {
    public val lower: ConstantSetter
        get() = ConstantSetter(LOWER, bindingCollector)

    /*
    public fun <T> lower(value: T): PositionalSetting<T> {
        return addPositionalSetting(LOWER, value)
    }

     */

    public fun <T> lower(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(LOWER, column.name(), null)
    }

    public fun <T> lower(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            LOWER,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> lower(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            LOWER,
            values,
            null
        )
    }
}