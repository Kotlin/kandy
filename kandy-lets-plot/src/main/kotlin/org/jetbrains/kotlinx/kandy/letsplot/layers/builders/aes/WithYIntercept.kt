/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_INTERCEPT
import kotlin.reflect.KProperty

/**
 * Interface for managing the 'yIntercept' aesthetic in horizontal line plots.
 *
 * The `yIntercept` aesthetic represents the y-coordinate at which the horizontal line will intercept the y-axis.
 * This interface provides various methods for specifying this value,
 * either as a constant or by mapping it to data columns.
 *
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithYIntercept : WithAes {

    /**
     * Provides a constant setter for the `yIntercept` value.
     *
     * @property yIntercept a [ConstantSetter] object to directly set the y-intercept.
     */
    public val yIntercept: ConstantSetter
        get() = ConstantSetter(
            Y_INTERCEPT,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `yIntercept` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yIntercept(column: ColumnReference<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_INTERCEPT, column.name(), null)
    }

    /**
     * Maps the `yIntercept` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yIntercept(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_INTERCEPT, column.name, null)
    }

    /**
     * Maps the `yIntercept` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun yIntercept(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(Y_INTERCEPT, column, null)
    }

    /**
     * Maps the `yIntercept` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yIntercept(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_INTERCEPT, values.toList(), null, null)
    }

    /**
     * Maps the `yIntercept` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yIntercept(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_INTERCEPT, values, null)
    }
}