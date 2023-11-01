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

/**
 * Interface for managing the `upper` aesthetic in a boxplot.
 *
 * The `upper` aesthetic represents the upper quartile of the data,
 * often displayed as the top edge of the box in a boxplot.
 *
 * This interface allows you to specify the upper quartile as a constant,
 * map it to a column, or provide iterable of values.
 */
public interface WithUpper : BindingContext {

    /**
     * Provides a constant setter for the `upper` value.
     *
     * @property upper A [ConstantSetter] object to directly set the upper quartile.
     */
    public val upper: ConstantSetter
        get() = ConstantSetter(UPPER, bindingCollector)

    /**
     * Maps the `upper` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(column: ColumnReference<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, column.name(), null)
    }

    /**
     * Maps the `upper` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(column: KProperty<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, column.name, null)
    }

    /**
     * Maps the `upper` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(column: String): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, column, null)
    }

    /**
     * Maps the `upper` aesthetic to iterable of values.
     *
     * @param values The iterable of values to be mapped.
     * @return A [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(values: Iterable<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, values.toList(), null, null)
    }

    /**
     * Maps the `upper` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> upper(values: DataColumn<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(UPPER, values, null)
    }
}