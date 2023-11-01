/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LABEL
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `label` aesthetic, used for adding text labels to visual elements.
 *
 * This interface allows you to specify the label as a constant, map it to a column, or provide iterable of values.
 */
public interface WithLabel : BindingContext {

    /**
     * Sets a constant `label` for the layer.
     *
     * @property label the value to be set.
     */
    public var label: String?
        get() = null
        set(value) {
            addNonPositionalSetting(LABEL, value)
        }

    /**
     * Maps the `label` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the color.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> label(
        column: ColumnReference<T>,
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(LABEL, column.name(), null)
    }

    /**
     * Maps the `label` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the color.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> label(
        column: KProperty<T>,
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(LABEL, column.name, null)
    }

    /**
     * Maps the `label` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the color.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun label(
        column: String,
    ): NonPositionalMapping<Any?, String> {
        return addNonPositionalMapping<Any?, String>(LABEL, column, null)
    }

    /**
     * Maps the `label` aesthetic to iterable of values.
     *
     * @param values the iterable containing the values.
     * @param name optional name for this aesthetic mapping.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> label(
        values: Iterable<T>,
        name: String? = null,
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(LABEL, values.toList(), name, null)
    }

    /**
     * Maps the `label` aesthetic to a data column.
     *
     * @param values the data column to map to the color.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> label(
        values: DataColumn<T>,
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(LABEL, values, null)
    }
}