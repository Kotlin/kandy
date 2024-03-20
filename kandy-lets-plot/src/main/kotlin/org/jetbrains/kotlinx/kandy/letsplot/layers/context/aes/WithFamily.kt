/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.FONT_FAMILY
import org.jetbrains.kotlinx.kandy.letsplot.settings.font.FontFamily
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `family` aesthetic, which refers to the font family in text elements of a plot layer.
 *
 * This interface provides methods to either directly set the font face or map a data column to it.
 */
public interface WithFamily : BindingContext {

    /**
     * Sets a constant font `family` for text elements in the plot layer.
     *
     * @property family the font family to be set, represented as a [FontFamily] object.
     */
    public var family: FontFamily?
        get() = null
        set(value) {
            addNonPositionalSetting(FONT_FAMILY, value)
        }

    /**
     * Maps the `family` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> family(
        column: ColumnReference<T>,
    ): NonPositionalMapping<T, FontFamily> {
        return addNonPositionalMapping<T, FontFamily>(
            FONT_FAMILY,
            column.name(),
            null
        )
    }

    /**
     * Maps the `family` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> family(
        column: KProperty<T>,
    ): NonPositionalMapping<T, FontFamily> {
        return addNonPositionalMapping<T, FontFamily>(
            FONT_FAMILY,
            column.name,
            null
        )
    }

    /**
     * Maps the `family` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun family(
        column: String,
    ): NonPositionalMapping<Any?, FontFamily> {
        return addNonPositionalMapping<Any?, FontFamily>(
            FONT_FAMILY,
            column,
            null
        )
    }

    /**
     * Maps the `family` aesthetic to the iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @param name optional name for this aesthetic mapping.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> family(
        values: Iterable<T>,
        name: String? = null,
    ): NonPositionalMapping<T, FontFamily> {
        return addNonPositionalMapping<T, FontFamily>(
            FONT_FAMILY,
            values.toList(),
            name,
            null
        )
    }

    /**
     * Maps the `family` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> family(
        values: DataColumn<T>,
    ): NonPositionalMapping<T, FontFamily> {
        return addNonPositionalMapping<T, FontFamily>(
            FONT_FAMILY,
            values,
            null
        )
    }
}