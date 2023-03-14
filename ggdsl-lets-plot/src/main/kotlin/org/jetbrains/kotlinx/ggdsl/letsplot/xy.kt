package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalFreeScale
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.X
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.Y
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Axis

/*
public fun <T> PlotContext.x(value: T): PositionalSetting<T> {
    return addPositionalSetting(X, value)
}

 */

public fun <T> PlotContext.x(
    column: ColumnReference<T>,
    parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(X, column.name(), LetsPlotPositionalMappingParameters<T>().apply(parameters))
}

public fun <T> PlotContext.x(
    values: Iterable<T>,
    name: String? = null,
    parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        X,
        values.toList(),
        name,
        LetsPlotPositionalMappingParameters<T>().apply(parameters)
    )
}

public fun <T> PlotContext.x(
    values: DataColumn<T>,
    //name: String? = null,
    parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        X,
        values.toList(),
        values.name(),
        LetsPlotPositionalMappingParameters<T,>().apply(parameters)
    )
}

@Suppress("UNCHECKED_CAST")
public val PlotContext.x: AxisParameters
    get() {
        return AxisParameters(bindingCollector.freeScales.getOrPut(X) {
            PositionalFreeScale(X, LetsPlotPositionalMappingParameters<Any?>())
        }.parameters as LetsPlotPositionalMappingParameters<Any?>)
    }

public fun PlotContext.x(
    parameters: AxisParameters.() -> Unit = {}
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
    parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(Y, column.name(), LetsPlotPositionalMappingParameters<T>().apply(parameters))
}

public fun <T> PlotContext.y(
    values: Iterable<T>,
    name: String? = null,
    parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        Y,
        values.toList(),
        name,
        LetsPlotPositionalMappingParameters<T>().apply(parameters)
    )
}

public fun <T> PlotContext.y(
    values: DataColumn<T>,
    //name: String? = null,
    parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(
        Y,
        values.toList(),
        values.name(),
        LetsPlotPositionalMappingParameters<T,>().apply(parameters)
    )
}

@Suppress("UNCHECKED_CAST")
public val PlotContext.y: AxisParameters
    get() {
        return AxisParameters(bindingCollector.freeScales.getOrPut(Y) {
            PositionalFreeScale(Y, LetsPlotPositionalMappingParameters<Any?>())
        }.parameters as LetsPlotPositionalMappingParameters<Any?>)
    }

public fun PlotContext.y(
    parameters: AxisParameters.() -> Unit = {}
) {
    y.apply(parameters)
}

public class AxisParameters(private val mappingParameters: LetsPlotPositionalMappingParameters<Any?>) {
    public var limits: ClosedRange<*>? = null
        set(value) {
            mappingParameters.scale = PositionalContinuousScale(value, null, null)
        }
    public val axis: Axis<Any?>
        get() = mappingParameters.axis
}