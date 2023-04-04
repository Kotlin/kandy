package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.X_MIN

public interface WithXMin : BindingContext {
    public val xMin: ConstantSetter
        get() = ConstantSetter(X_MIN, bindingCollector)

    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> xMin(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MIN, column.name(), null)
    }

    public fun <T> xMin(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MIN, column, null)
    }

    public fun <T> xMin(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_MIN,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> xMin(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_MIN,
            values,
            null
        )
    }
}