/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.SLOPE
import kotlin.reflect.KProperty
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*

/**
 * Interface for managing the `slope` aesthetic.
 *
 * The `slope` aesthetic is used primarily in abLine plots to control the slope of the line,
 * allowing for the representation of linear models or trend lines within the plot.
 * A positive slope value results in a line angled upwards from left to right,
 * while a negative value results in a line angled downwards from left to right.
 * A slope of zero results in a horizontal line.
 *
 * Implementing this interface allows you to bind the `slope` aesthetic to a data column,
 * enabling dynamic creation of lines based on your dataset.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithSlope : WithAes {

    /**
     * Provides a constant setter for the `slope` value.
     *
     * @property slope A [ConstantSetter] object to directly set the slope.
     */
    public val slope: ConstantSetter
        get() = ConstantSetter(
            SLOPE,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `slope` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> slope(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(SLOPE, column.name(), null)
    }

    /**
     * Maps the `slope` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> slope(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(SLOPE, column.name, null)
    }

    /**
     * Maps the `slope` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun slope(
        column: String,
    ): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping<Any?>(SLOPE, column, null)
    }

    /**
     * Maps the `slope` aesthetic to iterable of values.
     *
     * @param values The iterable of values to be mapped.
     * @return A [PositionalMapping] object representing the mapping.
     */
    public fun <T> slope(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(SLOPE, values.toList(), null, null)
    }

    /**
     * Maps the `slope` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> slope(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(SLOPE, values, null)
    }
}