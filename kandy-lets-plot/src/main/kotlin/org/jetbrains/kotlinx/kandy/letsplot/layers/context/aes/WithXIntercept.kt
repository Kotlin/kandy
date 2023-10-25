/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnAccessor
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_INTERCEPT
import kotlin.reflect.KProperty

public interface WithXIntercept : BindingContext {
    public val xIntercept: ConstantSetter
        get() = ConstantSetter(X_INTERCEPT, bindingCollector)

    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> xIntercept(
        column: ColumnAccessor<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_INTERCEPT, column.name(), null)
    }

    public fun <T> xIntercept(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_INTERCEPT, column.name, null)
    }

    public fun <T> xIntercept(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_INTERCEPT, column, null)
    }

    public fun <T> xIntercept(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_INTERCEPT,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> xIntercept(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_INTERCEPT,
            values,
            null
        )
    }
}