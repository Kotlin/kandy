package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_BEGIN

public interface WithYBegin : BindingContext {
    public val yBegin: ConstantSetter
        get() = ConstantSetter(Y_BEGIN, bindingCollector)

    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> yBegin(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_BEGIN, column.name(), null)
    }

    public fun <T> yBegin(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_BEGIN, column, null)
    }

    public fun <T> yBegin(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_BEGIN,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> yBegin(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_BEGIN,
            values,
            null
        )
    }
}