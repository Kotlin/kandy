/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_BEGIN
import kotlin.reflect.KProperty
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*

/**
 * Interface for managing the `yBegin` aesthetic,
 * which represents the starting y-coordinate for elements in a segment plot.
 *
 * The `yBegin` aesthetic is similar to the standard `x` aesthetic
 * but specifies the beginning point of a segment along the y-axis.
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithYBegin : WithAes {

    /**
     * Provides a constant setter for the `yBegin` aesthetic value.
     *
     * @property yBegin a [ConstantSetter] object to directly set the beginning point of a segment.
     */
    public val yBegin: org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.ConstantSetter
        get() = org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.ConstantSetter(
            Y_BEGIN,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `yBegin` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yBegin(column: ColumnReference<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_BEGIN, column.name(), null)
    }

    /**
     * Maps the `yBegin` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yBegin(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_BEGIN, column.name, null)
    }

    /**
     * Maps the `yBegin` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun yBegin(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(Y_BEGIN, column, null)
    }

    /**
     * Maps the `yBegin` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yBegin(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_BEGIN, values.toList(), null, null)
    }

    /**
     * Maps the `yBegin` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yBegin(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_BEGIN, values, null)
    }
}