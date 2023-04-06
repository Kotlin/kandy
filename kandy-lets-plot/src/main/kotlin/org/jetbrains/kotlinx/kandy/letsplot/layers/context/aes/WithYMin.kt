/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import kotlin.reflect.KProperty

public interface WithYMin : BindingContext {
    public val yMin: ConstantSetter
        get() = ConstantSetter(Y_MIN, bindingCollector)

    /*
    public fun <T> yMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(Y_MIN, value)
    }

     */

    public fun <T> yMin(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, column.name(), null)
    }

    public fun <T> yMin(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, column.name, null)
    }

    public fun <T> yMin(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, column, null)
    }

    public fun <T> yMin(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_MIN,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> yMin(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_MIN,
            values,
            null
        )
    }
}