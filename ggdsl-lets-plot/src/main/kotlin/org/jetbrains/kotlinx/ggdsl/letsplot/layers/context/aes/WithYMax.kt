package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.Y_MAX
import kotlin.reflect.KProperty

public interface WithYMax : BindingContext {
    public val yMax: ConstantSetter
        get() = ConstantSetter(Y_MAX, bindingCollector)

    /*
    public fun <T> yMax(value: T): PositionalSetting<T> {
        return addPositionalSetting(Y_MAX, value)
    }

     */

    public fun <T> yMax(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MAX, column.name(), null)
    }

    public fun <T> yMax(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MAX, column.name, null)
    }

    public fun <T> yMax(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MAX, column, null)
    }

    public fun <T> yMax(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_MAX,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> yMax(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_MAX,
            values,
            null
        )
    }
}