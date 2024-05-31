/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.WIDTH
import kotlin.reflect.KProperty
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*

/**
 * Interface for managing the `width` aesthetic, which determines the width of visual elements like bars in a bar chart.
 *
 * This interface allows setting the width as a constant value,
 * mapping it to a column, or providing iterable of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithWidth : WithAes {

    /**
     * Sets a constant width for visual elements in the layer.
     *
     * @property width the constant width value to set.
     */
    public var width: Double?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(WIDTH, value)
        }

    /**
     * Maps the `width` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the width.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> width(column: ColumnReference<T>): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping<T, Double>(WIDTH, column.name(), null)
    }

    /**
     * Maps the `width` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the width.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> width(column: KProperty<T>): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping<T, Double>(WIDTH, column.name, null)
    }

    /**
     * Maps the `width` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the width.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun width(column: String): NonPositionalMapping<Any?, Double> {
        return bindingHandler.addNonPositionalMapping(WIDTH, column, null)
    }

    /**
     * Maps the `width` aesthetic to iterable of values.
     *
     * @param values the iterable containing the width values.
     * @param name optional name for this aesthetic mapping.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> width(values: Iterable<T>, name: String? = null): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(WIDTH, values.toList(), name, null)
    }

    /**
     * Maps the `width` aesthetic to a data column.
     *
     * @param values the data column to map to the width.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> width(values: DataColumn<T>): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(WIDTH, values, null)
    }
}