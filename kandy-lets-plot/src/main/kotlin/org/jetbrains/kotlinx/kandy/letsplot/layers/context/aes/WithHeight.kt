/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.HEIGHT
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `height` aesthetic specifically used for tile-like plot elements.
 * This interface provides methods for setting the aesthetic directly or through data mappings.
 *
 * Implementing this interface allows the aesthetic to be directly set as a [Double], or mapped to a data column.
 */
public interface WithHeight : BindingContext {

    /**
     * Sets the `height` for tile-like plot elements.
     *
     * @property height a [Double] value to set as the height.
     */
    public var height: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(HEIGHT, value)
        }

    /**
     * Maps the `height` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the color.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> height(
        column: ColumnReference<T>,
    ): NonPositionalMapping<T, Double> =
        addNonPositionalMapping<T, Double>(HEIGHT, column.name(), null)

    /**
     * Maps the `height` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the color.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> height(
        column: KProperty<T>,
    ): NonPositionalMapping<T, Double> =
        addNonPositionalMapping<T, Double>(HEIGHT, column.name, null)

    /**
     * Maps the `height` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the color.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun height(column: String): NonPositionalMapping<Any?, Double> =
        addNonPositionalMapping(HEIGHT, column, null)

    /**
     * Maps the `height` aesthetic to the iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @param name optional name for this aesthetic mapping.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> height(values: Iterable<T>, name: String? = null): NonPositionalMapping<T, Double> =
        addNonPositionalMapping(HEIGHT, values.toList(), name, null)

    /**
     * Maps the `height` aesthetic to a data column.
     *
     * @param values the data column to map to the color.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> height(values: DataColumn<T>): PositionalMapping<T> =
        addPositionalMapping(HEIGHT, values, null)
}