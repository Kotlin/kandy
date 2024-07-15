/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.UPPER
import kotlin.reflect.KProperty

/**
 * Interface for managing the `upper` aesthetic in boxes.
 *
 * The `upper` aesthetic represents the upper quartile of the data,
 * often displayed as the top edge of the box in boxes.
 *
 * This interface allows you to specify the upper quartile as a constant,
 * map it to a column, or provide iterable of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithUpper : WithAes {

    /**
     * Provides a constant setter for the `upper` value.
     *
     * @property upper A [ConstantSetter] object to directly set the upper quartile.
     */
    public val upper: ConstantSetter
        get() = ConstantSetter(
            UPPER,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `upper` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(column: ColumnReference<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(UPPER, column.name(), null)
    }

    /**
     * Maps the `upper` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(UPPER, column.name, null)
    }

    /**
     * Maps the `upper` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun upper(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(UPPER, column, null)
    }

    /**
     * Maps the `upper` aesthetic to iterable of values.
     *
     * @param values The iterable of values to be mapped.
     * @return A [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(UPPER, values.toList(), null, null)
    }

    /**
     * Maps the `upper` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(UPPER, values, null)
    }
}