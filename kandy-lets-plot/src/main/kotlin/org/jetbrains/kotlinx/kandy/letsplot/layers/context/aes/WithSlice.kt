/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.SLICE
import kotlin.reflect.KProperty

public interface WithSlice : BindingContext {
    /*public val slice: ConstantSetter
        get() = ConstantSetter(SLICE, bindingCollector)
*/
    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> slice(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(SLICE, column.name(), null)
    }

    public fun <T> slice(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(SLICE, column.name, null)
    }

    public fun <T> slice(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(SLICE, column, null)
    }

    public fun <T> slice(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            SLICE,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> slice(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            SLICE,
            values,
            null
        )
    }
}