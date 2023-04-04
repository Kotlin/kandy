package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.Y_END
import kotlin.reflect.KProperty

public interface WithYEnd : BindingContext {
    public val yEnd: ConstantSetter
        get() = ConstantSetter(Y_END, bindingCollector)

    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> yEnd(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_END, column.name(), null)
    }

    public fun <T> yEnd(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_END, column.name, null)
    }

    public fun <T> yEnd(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_END, column, null)
    }

    public fun <T> yEnd(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_END,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> yEnd(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_END,
            values,
            null
        )
    }
}