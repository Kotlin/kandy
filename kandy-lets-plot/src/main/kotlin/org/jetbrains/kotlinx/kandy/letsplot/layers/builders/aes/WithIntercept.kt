/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.INTERCEPT
import kotlin.reflect.KProperty

/**
 * Interface for managing the `intercept` aesthetic, primarily used in abline plots.
 * This interface provides several ways to set the intercept value for the line.
 *
 * Implementing this interface allows you to specify the intercept as a constant,
 * map it to a column, or provide iterable of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithIntercept : WithAes {

    /**
     * Provides a constant setter for the `intercept` value.
     *
     * @property intercept A [ConstantSetter] object to directly set the intercept.
     */
    public val intercept: ConstantSetter
        get() = ConstantSetter(
            INTERCEPT,
            bindingHandler.bindingCollector
        )

    /**
     * Maps the `intercept` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> intercept(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(INTERCEPT, column.name(), null)
    }

    /**
     * Maps the `intercept` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> intercept(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(INTERCEPT, column.name, null)
    }

    /**
     * Maps the `intercept` aesthetic to a data column by [String].
     *
     * @param column the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun intercept(
        column: String,
    ): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping<Any?>(INTERCEPT, column, null)
    }

    /**
     * Maps the `intercept` aesthetic to iterable of values.
     *
     * @param values The iterable of values to be mapped.
     * @return A [PositionalMapping] object representing the mapping.
     */
    public fun <T> intercept(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(INTERCEPT, values.toList(), null, null)
    }

    /**
     * Maps the `intercept` aesthetic to a data column.
     *
     * @param values the data column to be mapped.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> intercept(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(INTERCEPT, values, null)
    }
}