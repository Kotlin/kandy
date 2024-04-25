/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LOWER
import kotlin.reflect.KProperty

/**
 * Interface for managing the `lower` aesthetic in boxes.
 *
 * The `lower` aesthetic represents the lower quartile of the data,
 * often displayed as the bottom edge of the box in boxes.
 *
 * This interface allows you to specify the label as a constant, map it to a column, or provide iterable of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithLower : WithAes {

    /**
     * Provides a constant setter for the `lower` value.
     *
     * @property lower A [ConstantSetter] object to directly set the lower.
     */
    public val lower: org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.ConstantSetter
        get() = org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.ConstantSetter(
            LOWER,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `lower` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> lower(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(LOWER, column.name(), null)
    }

    /**
     * Maps the `lower` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> lower(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(LOWER, column.name, null)
    }

    /**
     * Maps the `lower` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun lower(
        column: String,
    ): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(LOWER, column, null)
    }

    /**
     * Maps the `lower` aesthetic to iterable of values.
     *
     * @param values The iterable of values to be mapped.
     * @return A [PositionalMapping] object representing the mapping.
     */
    public fun <T> lower(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(LOWER, values.toList(), null, null)
    }

    /**
     * Maps the `lower` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> lower(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(LOWER, values, null)
    }
}