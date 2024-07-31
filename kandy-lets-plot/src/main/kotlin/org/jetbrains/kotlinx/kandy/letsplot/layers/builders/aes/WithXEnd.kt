/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_END
import kotlin.reflect.KProperty

/**
 * Interface for managing the `xEnd` aesthetic,
 * which represents the ending x-coordinate for elements in a segment plot.
 *
 * The `xEnd` aesthetic is used to specify the ending point of a segment along the x-axis.
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithXEnd : WithAes {

    /**
     * Provides a constant setter for the `xEnd` aesthetic value.
     *
     * @property xEnd a [ConstantSetter] object to directly set the ending point of a segment.
     */
    public val xEnd: ConstantSetter
        get() = ConstantSetter(
            X_END,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `xEnd` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xEnd(column: ColumnReference<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_END, column.name(), null)
    }


    /**
     * Maps the `xEnd` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xEnd(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_END, column.name, null)
    }

    /**
     * Maps the `xEnd` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun xEnd(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(X_END, column, null)
    }

    /**
     * Maps the `xEnd` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xEnd(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_END, values.toList(), null, null)
    }

    /**
     * Maps the `xEnd` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> xEnd(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(X_END, values, null)
    }
}