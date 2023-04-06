/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_END
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