/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import kotlin.reflect.KProperty


/**
 * Interface for managing the 'yMin' aesthetic in rectangle plots.
 *
 * The `yMin` aesthetic defines the minimum y-coordinate of each rectangle in a plot.
 * This interface offers multiple methods to set this value, either as a constant
 * or by mapping it to different types of data columns.
 *
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
public interface WithYMin : BindingContext {


    /**
     * Provides a constant setter for the `yMin` value.
     *
     * @property yMin a [ConstantSetter] object to directly set the minimum y-coordinate.
     */
    public val yMin: ConstantSetter
        get() = ConstantSetter(Y_MIN, bindingCollector)

    /**
     * Maps the `yMin` aesthetic to a data column specified by a [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMin(column: ColumnReference<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, column.name(), null)
    }

    /**
     * Maps the `yMin` aesthetic to a data column specified by a [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMin(column: KProperty<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, column.name, null)
    }

    /**
     * Maps the `yMin` aesthetic to a data column specified by a [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMin(column: String): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, column, null)
    }

    /**
     * Maps the `yMin` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMin(values: Iterable<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, values.toList(), null, null)
    }

    /**
     * Maps the `yMin` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMin(values: DataColumn<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MIN, values, null)
    }
}