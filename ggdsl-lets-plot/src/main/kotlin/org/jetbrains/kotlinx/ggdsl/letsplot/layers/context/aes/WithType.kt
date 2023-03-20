package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType

public interface WithType : BindingContext {
    public var type: LineType?
        get() = null
        set(value) {
            addNonPositionalSetting(LINE_TYPE, value)
        }
    public fun <T> type(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping<T, LineType>(
            LINE_TYPE,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, LineType>().apply(parameters)
        )
    }

    public fun type(
        column: String,
        parameters: LetsPlotNonPositionalMappingParameters<Any?, LineType>.() -> Unit = {}
    ): NonPositionalMapping<Any?, LineType> {
        return addNonPositionalMapping(
            LINE_TYPE,
            column,
            LetsPlotNonPositionalMappingParameters<Any?, LineType>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> type(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping(
            LINE_TYPE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParameters<T, LineType>().apply(parameters)
        )
    }

    public fun <T> type(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping(
            LINE_TYPE,
            values.toList(),
            values.name(),
            LetsPlotNonPositionalMappingParameters<T, LineType>().apply(parameters)
        )
    }
}