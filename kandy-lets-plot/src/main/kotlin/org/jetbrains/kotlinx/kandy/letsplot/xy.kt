/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParametersWithSetter

public fun <T> LayerPlotContext.x(
    column: ColumnReference<T>,
    parameters:LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(X, column.name(), LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters))
}

public fun <T> LayerPlotContext.x(
    values: Iterable<T>,
    name: String? = null,
    parameters:LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        X,
        values.toList(),
        name,
        LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

public fun LayerPlotContext.x(
    column: String,
    parameters:LetsPlotPositionalMappingParametersContinuous<Any?>.() -> Unit = {}
): PositionalMapping<Any?> {
    return addPositionalMapping<Any?>(X, column, LetsPlotPositionalMappingParametersContinuous<Any?>().apply(parameters))
}

public fun <T> LayerPlotContext.x(
    values: DataColumn<T>,
    //name: String? = null,
    parameters:LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        X,
        values,
       LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

@Suppress("UNCHECKED_CAST")
public val LayerPlotContext.x: AxisParametersWithSetter
    get() {
        return AxisParametersWithSetter(bindingCollector.freeScales.getOrPut(X) {
            PositionalFreeScale(X,LetsPlotPositionalMappingParametersContinuous<Any?>())
        }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>, X, this)
    }

public fun LayerPlotContext.x(
    parameters: AxisParametersWithSetter.() -> Unit = {}
) {
    x.apply(parameters)
}

/*
public fun <T> LayerPlotContext.y(value: T): PositionalSetting<T> {
    return addPositionalSetting(Y, value)
}

 */

public fun <T> LayerPlotContext.y(
    column: ColumnReference<T>,
    parameters:LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(Y, column.name(),LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters))
}

public fun <T> LayerPlotContext.y(
    values: Iterable<T>,
    name: String? = null,
    parameters:LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        Y,
        values.toList(),
        name,
       LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

public fun LayerPlotContext.y(
    column: String,
    parameters:LetsPlotPositionalMappingParametersContinuous<Any?>.() -> Unit = {}
): PositionalMapping<Any?> {
    return addPositionalMapping<Any?>(Y, column,LetsPlotPositionalMappingParametersContinuous<Any?>().apply(parameters))
}

public fun <T> LayerPlotContext.y(
    values: DataColumn<T>,
    //name: String? = null,
    parameters:LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        Y,
        values,
       LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
    )
}

@Suppress("UNCHECKED_CAST")
public val LayerPlotContext.y: AxisParametersWithSetter
    get() {
        return AxisParametersWithSetter(bindingCollector.freeScales.getOrPut(Y) {
            PositionalFreeScale(Y,LetsPlotPositionalMappingParametersContinuous<Any?>())
        }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>, Y, this)
    }

public fun LayerPlotContext.y(
    parameters: AxisParametersWithSetter.() -> Unit = {}
) {
    y.apply(parameters)
}
