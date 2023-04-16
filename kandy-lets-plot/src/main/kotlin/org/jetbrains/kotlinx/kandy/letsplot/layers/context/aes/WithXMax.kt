/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MAX
import kotlin.reflect.KProperty

public interface WithXMax : BindingContext {
    public val xMax: ConstantSetter
        get() = ConstantSetter(X_MAX, bindingCollector)

    /*
    public fun <T> xMax(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MAX, value)
    }

     */

    public fun <T> xMax(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MAX, column.name(), null)
    }

    public fun <T> xMax(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MAX, column.name, null)
    }

    public fun <T> xMax(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MAX, column, null)
    }

    public fun <T> xMax(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_MAX,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> xMax(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X_MAX,
            values,
            null
        )
    }
}