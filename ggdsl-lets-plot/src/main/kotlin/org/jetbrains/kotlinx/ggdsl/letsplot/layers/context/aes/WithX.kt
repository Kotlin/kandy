package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.X

public interface WithX : BindingContext {
    public fun <T> x(value: T): PositionalSetting<T> {
        return addPositionalSetting(X, value)
    }

    public fun <T> x(
        column: ColumnReference<T>,
        parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X, column.name(), LetsPlotPositionalMappingParameters<T>().apply(parameters))
    }

    public fun <T> x(
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

    public fun <T> x(
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
}