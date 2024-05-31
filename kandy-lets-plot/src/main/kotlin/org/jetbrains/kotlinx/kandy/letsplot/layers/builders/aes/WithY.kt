/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParametersWithSetter
import kotlin.reflect.KProperty
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*

/**
 * Interface for managing the `y` aesthetic, representing the y-coordinate of elements in the plot.
 *
 * This interface allows you to map the y-coordinate to a data column, provide iterable of y-values,
 * or configure axis parameters for the y-coordinate.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithY : WithAes {

    /**
     * Maps the `y` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the y-coordinate.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> y(
        column: ColumnReference<T>,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(
            Y,
            column.name(),
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    /**
     * Maps the `y` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the y-coordinate.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> y(
        column: KProperty<T>,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(
            Y,
            column.name,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    /**
     * Maps the `y` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the y-coordinate.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun y(
        column: String,
        parameters: LetsPlotPositionalMappingParametersContinuous<Any?>.() -> Unit = {}
    ): PositionalMapping<Any?> {
        return bindingHandler.addPositionalMapping<Any?>(
            Y,
            column,
            LetsPlotPositionalMappingParametersContinuous<Any?>().apply(parameters)
        )
    }

    /**
     * Maps the `y` aesthetic to iterable of values.
     *
     * @param values the iterable containing the y-coordinate values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> y(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(
            Y,
            values.toList(),
            name,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    /**
     * Maps the `y` aesthetic to a data column.
     *
     * @param values the data column to map to the y-coordinate.
     * @param parameters additional mapping parameters.
     * @return a [PositionalMapping] object representing the mapping.
     */
    public fun <T> y(
        values: DataColumn<T>,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping<T>(
            Y,
            values,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    /**
     * Provides a mechanism for setting or retrieving y-axis parameters.
     *
     * @property y an [AxisParametersWithSetter] object for y-axis configuration.
     */
    @Suppress("UNCHECKED_CAST")
    public val y: AxisParametersWithSetter
        get() {
            return AxisParametersWithSetter(bindingHandler.bindingCollector.freeScales.getOrPut(Y) {
                PositionalFreeScale(Y, LetsPlotPositionalMappingParametersContinuous<Any?>())
            }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>, Y, bindingHandler)
        }

    /**
     * Applies configurations to y-axis parameters.
     *
     * @param parameters the configurations to apply to the y-axis parameters.
     */
    public fun y(parameters: AxisParametersWithSetter.() -> Unit = {}) {
        y.apply(parameters)
    }
}