/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.FONT_FACE
import org.jetbrains.kotlinx.kandy.letsplot.settings.font.FontFace
import kotlin.reflect.KProperty
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*

/**
 * Interface for configuring the `face` aesthetic, which refers to the font face in text elements of a plot layer.
 *
 * This interface provides methods to either directly set the font face or map a data column to it.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithFace : WithAes {

    /**
     * Sets a constant font `face` for text elements in the plot layer.
     *
     * @property face the font face to be set, represented as a [FontFace] object.
     */
    public var face: FontFace?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(FONT_FACE, value)
        }

    /**
     * Maps the `face` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> face(
        column: ColumnReference<T>,
    ): NonPositionalMapping<T, FontFace> {
        return bindingHandler.addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            column.name(),
            null
        )
    }

    /**
     * Maps the `face` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> face(
        column: KProperty<T>,
    ): NonPositionalMapping<T, FontFace> {
        return bindingHandler.addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            column.name,
            null
        )
    }

    /**
     * Maps the `face` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun face(
        column: String,
    ): NonPositionalMapping<Any?, FontFace> {
        return bindingHandler.addNonPositionalMapping<Any?, FontFace>(
            FONT_FACE,
            column,
            null
        )
    }

    /**
     * Maps the `face` aesthetic to the iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @param name optional name for this aesthetic mapping.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> face(
        values: Iterable<T>,
        name: String? = null
    ): NonPositionalMapping<T, FontFace> {
        return bindingHandler.addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            values.toList(),
            name,
            null
        )
    }

    /**
     * Maps the `face` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> face(
        values: DataColumn<T>,
    ): NonPositionalMapping<T, FontFace> {
        return bindingHandler.addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            values,
            null
        )
    }
}