/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_BEGIN
import kotlin.reflect.KProperty
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*

/**
 * Interface for managing the `xBegin` aesthetic,
 * which represents the starting x-coordinate for elements in a segment plot.
 *
 * The `xBegin` aesthetic is similar to the standard `x` aesthetic
 * but specifies the beginning point of a segment along the x-axis.
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithXBegin : WithAes {

    /**
     * Provides a constant setter for the `xBegin` aesthetic value.
     *
     * @property xBegin a [ConstantSetter] object to directly set the beginning point of a segment.
     */
    public val xBegin: org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.ConstantSetter
        get() = org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.ConstantSetter(
            X_BEGIN,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `xBegin` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xBegin(column: ColumnReference<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_BEGIN, column.name(), null)
    }

    /**
     * Maps the `xBegin` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xBegin(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_BEGIN, column.name, null)
    }

    /**
     * Maps the `xBegin` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun xBegin(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(X_BEGIN, column, null)
    }

    /**
     * Maps the `xBegin` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xBegin(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_BEGIN, values.toList(), null, null)
    }

    /**
     * Maps the `xBegin` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xBegin(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_BEGIN, values, null)
    }
}