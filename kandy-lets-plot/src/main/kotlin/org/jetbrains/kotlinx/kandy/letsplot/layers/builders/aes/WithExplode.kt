/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.EXPLODE
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `explode` aesthetic in a plot layer.
 *
 * It provides methods for mapping a data column to the `explode` aesthetic,
 * enabling the expansion of data points in a certain visual manner.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithExplode : WithAes {

    /**
     * Maps the `explode` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> explode(column: ColumnReference<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(EXPLODE, column.name(), null)
    }

    /**
     * Maps the `explode` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> explode(column: KProperty<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(EXPLODE, column.name, null)
    }

    /**
     * Maps the `explode` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun explode(column: String): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping<Any?>(EXPLODE, column, null)
    }

    /**
     * Maps the `explode` aesthetic to iterable of values.
     *
     * @param values The iterable of values to be mapped.
     * @return A [PositionalMapping] object representing the mapping.
     */
    public fun <T> explode(values: Iterable<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(EXPLODE, values.toList(), null, null)
    }

    /**
     * Maps the `explode` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> explode(values: DataColumn<T>): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(EXPLODE, values, null)
    }
}