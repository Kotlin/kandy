/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MIN
import kotlin.reflect.KProperty

/**
 * Interface for managing the 'xMin' aesthetic in rectangle plots.
 *
 * The `xMin` aesthetic defines the minimum x-coordinate of each rectangle in a plot.
 * This interface offers multiple methods to set this value, either as a constant
 * or by mapping it to different types of data columns.
 *
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
public interface WithXMin : BindingContext {


    /**
     * Provides a constant setter for the `xMin` value.
     *
     * @property xMin a [ConstantSetter] object to directly set the minimum x-coordinate.
     */
    public val xMin: ConstantSetter
        get() = ConstantSetter(X_MIN, bindingCollector)

    /**
     * Maps the `xMin` aesthetic to a data column specified by a [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMin(column: ColumnReference<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MIN, column.name(), null)
    }

    /**
     * Maps the `xMin` aesthetic to a data column specified by a [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMin(column: KProperty<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MIN, column.name, null)
    }

    /**
     * Maps the `xMin` aesthetic to a data column specified by a [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMin(column: String): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MIN, column, null)
    }

    /**
     * Maps the `xMin` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMin(values: Iterable<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MIN, values.toList(), null, null)
    }

    /**
     * Maps the `xMin` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMin(values: DataColumn<T>): PositionalMapping<T> {
        return addPositionalMapping<T>(X_MIN, values, null)
    }
}