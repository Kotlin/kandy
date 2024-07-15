/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addNonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import kotlin.reflect.KProperty

/**
 * Interface for managing the `size` aesthetic.
 *
 * The `size` aesthetic controls the size of individual elements within the plot, such as points in a scatter plot.
 * Depending on the plot type, the interpretation of `size` might differ,
 * but generally, it is used to encode numerical values through visual size variations.
 *
 * This interface allows you to specify the label as a constant, map it to a column, or provide iterable of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithSize : WithAes {

    /**
     * Sets a constant `size` for the layer.
     *
     * @property size the value to be set.
     */
    public var size: Double?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(SIZE, value)
        }

    /**
     * Maps the `size` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> size(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping<T, Double>(
            SIZE,
            column.name(),
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `size` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> size(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping<T, Double>(
            SIZE,
            column.name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `size` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun size(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return bindingHandler.addNonPositionalMapping(
            SIZE,
            column,
            LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `size` aesthetic to iterable of discrete values.
     *
     * @param values the iterable containing the discrete values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> size(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            SIZE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `size` aesthetic to a data column.
     *
     * @param values the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> size(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            SIZE,
            values,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}