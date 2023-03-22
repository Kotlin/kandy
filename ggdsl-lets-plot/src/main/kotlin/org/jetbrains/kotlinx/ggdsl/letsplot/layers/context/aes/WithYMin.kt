package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.Y_MIN

public interface WithYMin : BindingContext {
    public val yMin: ConstantSetter
        get() = ConstantSetter(Y_MIN, bindingCollector)

    /*
    public fun <T> yMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(Y_MIN, value)
    }

     */

    public fun <T> yMin(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, column.name(), null)
    }

    public fun <T> yMin(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_MIN,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> yMin(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_MIN,
            values,
            null
        )
    }
}