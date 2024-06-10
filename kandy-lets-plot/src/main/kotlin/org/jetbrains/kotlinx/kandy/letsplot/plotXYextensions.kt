/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.bindingHandler
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParametersWithSetter

/**
 * Maps the `x` aesthetic to a data column by [ColumnReference].
 *
 * @param column the data column to map to the x-coordinate.
 * @param parameters additional mapping parameters.
 * @return a [PositionalMapping] object representing the mapping.
 */
public fun <T> PlotBuilder.x(
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
 * Maps the `x` aesthetic to iterable of values.
 *
 * @param values the iterable containing the x-coordinate values.
 * @param name optional name for this aesthetic mapping.
 * @param parameters additional mapping parameters.
 * @return a [PositionalMapping] object representing the mapping.
 */
public fun <T> PlotBuilder.x(
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
 * Maps the `x` aesthetic to a data column by [String].
 *
 * @param column the data column to map to the x-coordinate.
 * @param parameters additional mapping parameters.
 * @return a [PositionalMapping] object representing the mapping.
 */
public fun PlotBuilder.x(
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
 * Maps the `x` aesthetic to a data column.
 *
 * @param values the data column to map to the x-coordinate.
 * @param parameters additional mapping parameters.
 * @return a [PositionalMapping] object representing the mapping.
 */
public fun <T> PlotBuilder.x(
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
public val PlotBuilder.x: AxisParametersWithSetter
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
public fun PlotBuilder.x(parameters: AxisParametersWithSetter.() -> Unit = {}) {
    x.apply(parameters)
}

/**
 * Maps the `y` aesthetic to a data column by [ColumnReference].
 *
 * @param column the data column to map to the y-coordinate.
 * @param parameters additional mapping parameters.
 * @return a [PositionalMapping] object representing the mapping.
 */
public fun <T> PlotBuilder.y(
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
 * Maps the `y` aesthetic to iterable of values.
 *
 * @param values the iterable containing the y-coordinate values.
 * @param name optional name for this aesthetic mapping.
 * @param parameters additional mapping parameters.
 * @return a [PositionalMapping] object representing the mapping.
 */
public fun <T> PlotBuilder.y(
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
 * Maps the `y` aesthetic to a data column by [String].
 *
 * @param column the data column to map to the y-coordinate.
 * @param parameters additional mapping parameters.
 * @return a [PositionalMapping] object representing the mapping.
 */
public fun PlotBuilder.y(
    column: String,
    parameters: LetsPlotPositionalMappingParametersContinuous<Any?>.() -> Unit = {}
): PositionalMapping<Any?> {
    @Suppress("INVISIBLE_MEMBER")
    return bindingHandler.addPositionalMapping<Any?>(
        Y,
        column,
        LetsPlotPositionalMappingParametersContinuous<Any?>().apply(parameters)
    )
}

/**
 * Maps the `y` aesthetic to a data column.
 *
 * @param values the data column to map to the y-coordinate.
 * @param parameters additional mapping parameters.
 * @return a [PositionalMapping] object representing the mapping.
 */
public fun <T> PlotBuilder.y(
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
public val PlotBuilder.y: AxisParametersWithSetter
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
public fun PlotBuilder.y(parameters: AxisParametersWithSetter.() -> Unit = {}) {
    y.apply(parameters)
}
