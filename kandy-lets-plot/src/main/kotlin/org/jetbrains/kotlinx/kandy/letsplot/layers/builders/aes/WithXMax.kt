/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MAX
import kotlin.reflect.KProperty

/**
 * Interface for managing the 'xMax' aesthetic in rectangle plots.
 *
 * The `xMax` aesthetic defines the maximum x-coordinate of each rectangle in a plot.
 * This interface provides various methods to set this value, either as a constant
 * or by mapping it to different types of data columns.
 *
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithXMax : WithAes {

    /**
     * Provides a constant setter for the `xMax` value.
     *
     * @property xMax a [ConstantSetter] object to directly set the maximum x-coordinate.
     */
    public val xMax: ConstantSetter
        get() = ConstantSetter(
            X_MAX,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `xMax` aesthetic to a data column specified by a [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMax(column: ColumnReference<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_MAX, column.name(), null)
    }

    /**
     * Maps the `xMax` aesthetic to a data column specified by a [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMax(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_MAX, column.name, null)
    }

    /**
     * Maps the `xMax` aesthetic to a data column specified by a [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun xMax(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(X_MAX, column, null)
    }


    /**
     * Maps the `xMax` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMax(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_MAX, values.toList(), null, null)
    }

    /**
     * Maps the `xMax` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xMax(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_MAX, values, null)
    }
}