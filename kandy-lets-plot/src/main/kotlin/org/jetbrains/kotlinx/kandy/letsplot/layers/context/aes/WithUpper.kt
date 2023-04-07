/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.UPPER
import kotlin.reflect.KProperty

public interface WithUpper : BindingContext {
    public val upper: ConstantSetter
        get() = ConstantSetter(UPPER, bindingCollector)

    /*
        public fun <T> upper(value: T): PositionalSetting<T> {
            return addPositionalSetting(UPPER, value)
        }


     */
    public fun <T> upper(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, column.name(), null)
    }

    public fun <T> upper(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, column.name, null)
    }

    public fun <T> upper(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, column, null)
    }

    public fun <T> upper(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            UPPER,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> upper(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            UPPER,
            values,
            null
        )
    }
}