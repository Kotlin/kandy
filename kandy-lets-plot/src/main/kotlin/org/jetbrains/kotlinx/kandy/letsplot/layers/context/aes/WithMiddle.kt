package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.MIDDLE
import kotlin.reflect.KProperty

public interface WithMiddle : BindingContext {
    public val middle: ConstantSetter
        get() = ConstantSetter(MIDDLE, bindingCollector)

    /*
    public fun <T> middle(value: T): PositionalSetting<T> {
        return addPositionalSetting(MIDDLE, value)
    }

     */

    public fun <T> middle(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, column.name(), null)
    }

    public fun <T> middle(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, column.name, null)
    }

    public fun <T> middle(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, column, null)
    }

    public fun <T> middle(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            MIDDLE,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> middle(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            MIDDLE,
            values,
            null
        )
    }
}