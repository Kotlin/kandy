/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.MIDDLE
import kotlin.reflect.KProperty

/**
 * Interface for managing the `middle` aesthetic in both boxplot and crossbars.
 *
 * In boxplot, the `middle` aesthetic represents the median of the data, commonly displayed
 * as a line inside the box.
 *
 * In crossbars, the `middle` aesthetic typically represents the central value around which
 * error bars are constructed.
 *
 * This interface allows you to specify the label as a constant, map it to a column, or provide iterable of values.
 */
public interface WithMiddle : BindingContext {

    /**
     * Provides a constant setter for the `middle` value.
     *
     * @property middle A [ConstantSetter] object to directly set the middle.
     */
    public val middle: ConstantSetter
        get() = ConstantSetter(MIDDLE, bindingCollector)

    /**
     * Maps the `middle` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> middle(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, column.name(), null)
    }

    /**
     * Maps the `middle` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> middle(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, column.name, null)
    }

    /**
     * Maps the `middle` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> middle(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, column, null)
    }

    /**
     * Maps the `middle` aesthetic to iterable of values.
     *
     * @param values The iterable of values to be mapped.
     * @return A [PositionalMapping] object representing the mapping.
     */
    public fun <T> middle(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, values.toList(), null, null)
    }

    /**
     * Maps the `middle` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> middle(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(MIDDLE, values, null)
    }
}