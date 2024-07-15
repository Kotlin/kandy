/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.SLICE
import kotlin.reflect.KProperty

/**
 * Interface for managing the `slice` aesthetic.
 *
 * The `slice` aesthetic is primarily used in pie plots to determine the angle of each pie segment.
 * This aesthetic allows for the representation of categorical or numerical data in the form of a pie chart,
 * where each slice represents a data category and its size is proportional to the value of the data point.
 *
 * By using this interface, you can bind the `slice` aesthetic to a data column,
 * allowing you to dynamically create pie segments based on your dataset.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithSlice : WithAes {

    /**
     * Maps the `slice` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> slice(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(SLICE, column.name(), null)
    }

    /**
     * Maps the `slice` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> slice(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(SLICE, column.name, null)
    }

    /**
     * Maps the `slice` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun slice(
        column: String,
    ): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping<Any?>(SLICE, column, null)
    }

    /**
     * Maps the `slice` aesthetic to iterable of values.
     *
     * @param values The iterable of values to be mapped.
     * @return A [PositionalMapping] object representing the mapping.
     */
    public fun <T> slice(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(SLICE, values.toList(), null, null)
    }

    /**
     * Maps the `slice` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> slice(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(SLICE, values, null)
    }
}