/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LABEL
import kotlin.reflect.KProperty

public interface WithLabel : BindingContext {
    public var label: String?
        get() = null
        set(value) {
            addNonPositionalSetting(LABEL, value)
        }

    public fun <T> label(
        column: ColumnReference<T>,
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(
            LABEL,
            column.name(),
            null
        )
    }

    public fun <T> label(
        column: KProperty<T>,
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(
            LABEL,
            column.name,
            null
        )
    }

    public fun label(
        column: String,
    ): NonPositionalMapping<Any?, String> {
        return addNonPositionalMapping<Any?, String>(
            LABEL,
            column,
            null
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> label(
        values: Iterable<T>,
        name: String? = null,
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(
            LABEL,
            values.toList(),
            name,
            null
        )
    }

    public fun <T> label(
        values: DataColumn<T>,
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(
            LABEL,
            values,
            null
        )
    }
}