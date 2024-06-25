/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParametersWithSetter
import kotlin.reflect.KProperty
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*


/**
 * Interface for managing the `x` aesthetic, representing the x-coordinate of elements in the plot.
 *
 * This interface allows you to map the x-coordinate to a data column, provide iterable of x-values,
 * or configure axis parameters for the x-coordinate.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithX : WithAes {

    /**
     * Maps the `x` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the x-coordinate.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> x(
        column: ColumnReference<T>,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(
            X,
            column.name(),
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    /**
     * Maps the `x` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the x-coordinate.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> x(
        column: KProperty<T>,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(
            X,
            column.name,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    /**
     * Maps the `x` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the x-coordinate.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun x(
        column: String,
        parameters: LetsPlotPositionalMappingParametersContinuous<Any?>.() -> Unit = {}
    ): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping<Any?>(
            X,
            column,
            LetsPlotPositionalMappingParametersContinuous<Any?>().apply(parameters)
        )
    }

    /**
     * Maps the `x` aesthetic to iterable of values.
     *
     * @param values the iterable containing the x-coordinate values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> x(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(
            X,
            values.toList(),
            name,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    /**
     * Maps the `x` aesthetic to a data column.
     *
     * @param values the data column to map to the x-coordinate.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> x(
        values: DataColumn<T>,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(
            X,
            values,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    /**
     * Provides a mechanism for setting or retrieving x-axis parameters.
     *
     * @property x an [AxisParametersWithSetter] object for x-axis configuration.
     */
    @Suppress("UNCHECKED_CAST")
    public val x: AxisParametersWithSetter
        get() {
            return AxisParametersWithSetter(bindingHandler.bindingCollector.freeScales.getOrPut(X) {
                PositionalFreeScale(X, LetsPlotPositionalMappingParametersContinuous<Any?>())
            }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>, X, bindingHandler)
        }

    /**
     * Applies configurations to x-axis parameters.
     *
     * @param parameters the configurations to apply to the x-axis parameters.
     */
    public fun x(parameters: AxisParametersWithSetter.() -> Unit = {}) {
        x.apply(parameters)
    }
}