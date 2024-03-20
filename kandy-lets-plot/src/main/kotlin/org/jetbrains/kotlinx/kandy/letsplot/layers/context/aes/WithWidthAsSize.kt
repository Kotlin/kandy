/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import kotlin.reflect.KProperty

/**
 * Interface for managing the `width` (size) aesthetic.
 *
 * The `width` aesthetic controls the size of individual elements within the plot, such as lines in a line plot.
 * Depending on the plot type, the interpretation of `size` might differ,
 * but generally, it is used to encode numerical values through visual size variations.
 *
 * This interface allows you to specify the label as a constant, map it to a column, or provide iterable of values.
 */
public interface WithWidthAsSize : BindingContext {

    /**
     * Sets a constant `width` for the layer.
     *
     * @property width the value to be set.
     */
    public var width: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(SIZE, value)
        }

    /**
     * Maps the `width` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> width(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            SIZE,
            column.name(),
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `width` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> width(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            SIZE,
            column.name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `width` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun width(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return addNonPositionalMapping(
            SIZE,
            column,
            LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `width` aesthetic to iterable of discrete values.
     *
     * @param values the iterable containing the discrete values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> width(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            SIZE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `width` aesthetic to a data column.
     *
     * @param values the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> width(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            SIZE,
            values,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}