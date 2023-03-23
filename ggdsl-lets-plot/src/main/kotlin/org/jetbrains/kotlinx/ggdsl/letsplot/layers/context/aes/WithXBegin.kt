package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.X_BEGIN

public interface WithXBegin : BindingContext {
    public val xBegin: ConstantSetter
        get() = ConstantSetter(X_BEGIN, bindingCollector)

    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> xBegin(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_BEGIN, column.name(), null)
    }

    public fun <T> xBegin(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_BEGIN, column, null)
    }

    public fun <T> xBegin(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_BEGIN,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> xBegin(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_BEGIN,
            values,
            null
        )
    }
}