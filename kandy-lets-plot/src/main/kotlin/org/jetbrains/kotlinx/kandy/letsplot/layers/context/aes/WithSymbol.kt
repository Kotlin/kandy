/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersCategorical
import org.jetbrains.kotlinx.kandy.letsplot.internal.SHAPE
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import kotlin.reflect.KProperty

/**
 * Interface for managing the `symbol` aesthetic.
 *
 * The `symbol` aesthetic is used to determine the shape or type of marker in a plot, typically in scatter plots.
 *
 * This interface allows you to specify the label as a constant, map it to a column, or provide iterable of values.
 */
public interface WithSymbol : BindingContext {

    /**
     * Sets a constant symbol for the markers in the plot.
     *
     * @property symbol the [Symbol] value representing the shape or type of marker.
     */
    public var symbol: Symbol?
        get() = null
        set(value) {
            addNonPositionalSetting(SHAPE, value)
        }

    /**
     * Maps the symbol aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the symbol.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> symbol(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping<T, Symbol>(
            SHAPE,
            column.name(),
            LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>().apply(parameters)
        )
    }

    /**
     * Maps the symbol aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the symbol.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> symbol(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping<T, Symbol>(
            SHAPE,
            column.name,
            LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>().apply(parameters)
        )
    }

    /**
     * Maps the symbol aesthetic to a data column by [String].
     *
     * @param column the data column to map to the symbol.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun symbol(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<Any?, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Symbol> {
        return addNonPositionalMapping(
            SHAPE,
            column,
            LetsPlotNonPositionalMappingParametersCategorical<Any?, Symbol>().apply(parameters)
        )
    }

    /**
     * Maps the symbol aesthetic to iterable of values.
     *
     * @param values the iterable containing the categorical values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> symbol(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping(
            SHAPE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>().apply(parameters)
        )
    }

    /**
     * Maps the symbol aesthetic to a data column.
     *
     * @param values the data column to map to the symbol.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> symbol(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping(
            SHAPE,
            values,
            LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>().apply(parameters)
        )
    }
}