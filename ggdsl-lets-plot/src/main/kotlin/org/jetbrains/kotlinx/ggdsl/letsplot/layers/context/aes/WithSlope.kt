package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.SLOPE

public interface WithSlope : BindingContext {
    public val slope: ConstantSetter
        get() = ConstantSetter(SLOPE, bindingCollector)

    /*
    public fun <T> lower(value: T): PositionalSetting<T> {
        return addPositionalSetting(LOWER, value)
    }

     */

    public fun <T> slope(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(SLOPE, column.name(), null)
    }

    public fun <T> slope(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            SLOPE,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> slope(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            SLOPE,
            values,
            null
        )
    }
}