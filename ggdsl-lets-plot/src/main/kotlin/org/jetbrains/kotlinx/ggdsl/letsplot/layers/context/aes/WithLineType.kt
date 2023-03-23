package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType

public interface WithLineType : BindingContext {
    public var lineType: LineType?
        get() = null
        set(value) {
            addNonPositionalSetting(LINE_TYPE, value)
        }
    public fun <T> lineType(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping<T, LineType>(
            LINE_TYPE,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, LineType>().apply(parameters)
        )
    }

    public fun lineType(
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
    public fun <T> lineType(
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

    public fun <T> lineType(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping(
            LINE_TYPE,
            values,
            LetsPlotNonPositionalMappingParameters<T, LineType>().apply(parameters)
        )
    }
}