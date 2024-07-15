/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_END
import kotlin.reflect.KProperty

/**
 * Interface for managing the `yEnd` aesthetic,
 * which represents the ending y-coordinate for elements in a segment plot.
 *
 * The `yEnd` aesthetic is used to specify the ending point of a segment along the y-axis.
 * This interface allows you to set a constant value,
 * map it to a column in your data, or provide an iterable list of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithYEnd : WithAes {

    /**
     * Provides a constant setter for the `yEnd` aesthetic value.
     *
     * @property yEnd a [ConstantSetter] object to directly set the ending point of a segment.
     */
    public val yEnd: ConstantSetter
        get() = ConstantSetter(
            Y_END,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `yEnd` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yEnd(column: ColumnReference<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_END, column.name(), null)
    }


    /**
     * Maps the `yEnd` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yEnd(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_END, column.name, null)
    }

    /**
     * Maps the `yEnd` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun yEnd(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping(Y_END, column, null)
    }

    /**
     * Maps the `yEnd` aesthetic to iterable of values.
     *
     * @param values the iterable of values to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yEnd(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_END, values.toList(), null, null)
    }

    /**
     * Maps the `yEnd` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> yEnd(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(Y_END, values, null)
    }
}