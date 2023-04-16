/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.INTERCEPT
import kotlin.reflect.KProperty

public interface WithIntercept : BindingContext {
    public val intercept: ConstantSetter
        get() = ConstantSetter(INTERCEPT, bindingCollector)

    /*
    public fun <T> lower(value: T): PositionalSetting<T> {
        return addPositionalSetting(LOWER, value)
    }

     */

    public fun <T> intercept(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(INTERCEPT, column.name(), null)
    }

    public fun <T> intercept(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(INTERCEPT, column.name, null)
    }

    public fun <T> intercept(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(INTERCEPT, column, null)
    }

    public fun <T> intercept(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            INTERCEPT,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> intercept(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            INTERCEPT,
            values,
            null
        )
    }
}