package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_BEGIN
import kotlin.reflect.KProperty

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
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_BEGIN, column.name, null)
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