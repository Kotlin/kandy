package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.Y

public interface WithY : BindingContext {
    public fun <T> y(value: T): PositionalSetting<T> {
        return addPositionalSetting(Y, value)
    }

    public fun <T> y(
        column: ColumnReference<T>,
        parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y, column.name(), LetsPlotPositionalMappingParameters<T>().apply(parameters))
    }

    public fun <T> y(
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

    public fun <T> y(
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
}