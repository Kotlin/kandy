/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnAccessor
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_INTERCEPT
import kotlin.reflect.KProperty

/**
 * Interface for managing the 'xIntercept' aesthetic in vertical line plots.
 *
 * The `xIntercept` aesthetic represents the x-coordinate at which the vertical line will intercept the x-axis.
 * This interface provides various methods for specifying this value,
 * either as a constant or by mapping it to data columns.
 *
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithXIntercept : WithAes {

    /**
     * Provides a constant setter for the `xIntercept` value.
     *
     * @property xIntercept a [ConstantSetter] object to directly set the x-intercept.
     */
    public val xIntercept: org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.ConstantSetter
        get() = org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.ConstantSetter(
            X_INTERCEPT,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `xIntercept` aesthetic to a data column by [ColumnAccessor].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xIntercept(column: ColumnAccessor<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_INTERCEPT, column.name(), null)
    }

    /**
     * Maps the `xIntercept` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xIntercept(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_INTERCEPT, column.name, null)
    }

    /**
     * Maps the `xIntercept` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun xIntercept(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(X_INTERCEPT, column, null)
    }

    /**
     * Maps the `xIntercept` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xIntercept(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_INTERCEPT, values.toList(), null, null)
    }

    /**
     * Maps the `xIntercept` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xIntercept(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_INTERCEPT, values, null)
    }
}