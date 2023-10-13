/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParametersWithSetter

public fun <T> PlotContext.x(
    column: ColumnReference<T>,
    parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        X,
        column.name(),
        LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

public fun <T> PlotContext.x(
    values: Iterable<T>,
    name: String? = null,
    parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        X,
        values.toList(),
        name,
        LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

public fun PlotContext.x(
    column: String,
    parameters: LetsPlotPositionalMappingParametersContinuous<Any?>.() -> Unit = {}
): PositionalMapping<Any?> {
    return addPositionalMapping<Any?>(
        X,
        column,
        LetsPlotPositionalMappingParametersContinuous<Any?>().apply(parameters)
    )
}

public fun <T> PlotContext.x(
    values: DataColumn<T>,
    //name: String? = null,
    parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        X,
        values,
        LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

@Suppress("UNCHECKED_CAST")
public val PlotContext.x: AxisParametersWithSetter
    get() {
        return AxisParametersWithSetter(bindingCollector.freeScales.getOrPut(X) {
            PositionalFreeScale(X, LetsPlotPositionalMappingParametersContinuous<Any?>())
        }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>, X, this)
    }

public fun PlotContext.x(
    parameters: AxisParametersWithSetter.() -> Unit = {}
) {
    x.apply(parameters)
}

/*
public fun <T> PlotContext.y(value: T): PositionalSetting<T> {
    return addPositionalSetting(Y, value)
}

 */

public fun <T> PlotContext.y(
    column: ColumnReference<T>,
    parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        Y,
        column.name(),
        LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

public fun <T> PlotContext.y(
    values: Iterable<T>,
    name: String? = null,
    parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        Y,
        values.toList(),
        name,
        LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

public fun PlotContext.y(
    column: String,
    parameters: LetsPlotPositionalMappingParametersContinuous<Any?>.() -> Unit = {}
): PositionalMapping<Any?> {
    return addPositionalMapping<Any?>(
        Y,
        column,
        LetsPlotPositionalMappingParametersContinuous<Any?>().apply(parameters)
    )
}

public fun <T> PlotContext.y(
    values: DataColumn<T>,
    //name: String? = null,
    parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        Y,
        values,
        LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

@Suppress("UNCHECKED_CAST")
public val PlotContext.y: AxisParametersWithSetter
    get() {
        return AxisParametersWithSetter(bindingCollector.freeScales.getOrPut(Y) {
            PositionalFreeScale(Y, LetsPlotPositionalMappingParametersContinuous<Any?>())
        }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>, Y, this)
    }

public fun PlotContext.y(
    parameters: AxisParametersWithSetter.() -> Unit = {}
) {
    y.apply(parameters)
}
