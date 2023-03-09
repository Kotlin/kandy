package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.COLOR
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.util.color.Color

public interface WithColor : BindingContext {
    public var color: Color?
        get() = null
        set(value) {
            addNonPositionalSetting(COLOR, value)
        }

    public fun <T> color(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }

    public fun color(
        column: String,
        parameters: LetsPlotNonPositionalMappingParameters<Any?, Color>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Color> {
        return addNonPositionalMapping<Any?, Color>(
            COLOR,
            column,
            LetsPlotNonPositionalMappingParameters<Any?, Color>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> color(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }

    public fun <T> color(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            values.toList(),
            values.name(),
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }
}