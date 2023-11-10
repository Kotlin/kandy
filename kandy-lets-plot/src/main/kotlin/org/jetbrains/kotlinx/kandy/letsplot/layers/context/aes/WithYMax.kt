/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import kotlin.reflect.KProperty

/**
 * Interface for managing the 'yMax' aesthetic in rectangle plots.
 *
 * The `yMax` aesthetic defines the maximum y-coordinate of each rectangle in a plot.
 * This interface provides various methods to set this value, either as a constant
 * or by mapping it to different types of data columns.
 *
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
public interface WithYMax : BindingContext {

    /**
     * Provides a constant setter for the `yMax` value.
     *
     * @property yMax a [ConstantSetter] object to directly set the maximum y-coordinate.
     */
    public val yMax: ConstantSetter
        get() = ConstantSetter(Y_MAX, bindingCollector)

    /**
     * Maps the `yMax` aesthetic to a data column specified by a [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMax(column: ColumnReference<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MAX, column.name(), null)
    }

    /**
     * Maps the `yMax` aesthetic to a data column specified by a [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMax(column: KProperty<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MAX, column.name, null)
    }

    /**
     * Maps the `yMax` aesthetic to a data column specified by a [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMax(column: String): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MAX, column, null)
    }


    /**
     * Maps the `yMax` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMax(values: Iterable<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MAX, values.toList(), null, null)
    }

    /**
     * Maps the `yMax` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yMax(values: DataColumn<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(Y_MAX, values, null)
    }
}