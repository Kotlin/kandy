package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.INTERCEPT

public interface WithIntercept : BindingContext {
    public val intercept: ConstantSetter
        get() = ConstantSetter(INTERCEPT, bindingCollector)

    /*
    public fun <T> lower(value: T): PositionalSetting<T> {
        return addPositionalSetting(LOWER, value)
    }

     */

    public fun <T> intercept(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(INTERCEPT, column.name(), null)
    }

    public fun <T> intercept(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(INTERCEPT, column, null)
    }

    public fun <T> intercept(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            INTERCEPT,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> intercept(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            INTERCEPT,
            values,
            null
        )
    }
}