/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_INTERCEPT
import kotlin.reflect.KProperty

public interface WithYIntercept : BindingContext {
    public val yIntercept: ConstantSetter
        get() = ConstantSetter(Y_INTERCEPT, bindingCollector)

    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> yIntercept(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_INTERCEPT, column.name(), null)
    }

    public fun <T> yIntercept(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_INTERCEPT, column.name, null)
    }

    public fun <T> yIntercept(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_INTERCEPT, column, null)
    }

    public fun <T> yIntercept(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_INTERCEPT,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> yIntercept(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y_INTERCEPT,
            values,
            null
        )
    }
}