package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_END
import kotlin.reflect.KProperty

public interface WithXEnd : BindingContext {
    public val xEnd: ConstantSetter
        get() = ConstantSetter(X_END, bindingCollector)

    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> xEnd(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_END, column.name(), null)
    }

    public fun <T> xEnd(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_END, column.name, null)
    }

    public fun <T> xEnd(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_END, column, null)
    }

    public fun <T> xEnd(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_END,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> xEnd(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_END,
            values,
            null
        )
    }
}