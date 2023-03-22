package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.WIDTH

public interface WithWidth : BindingContext {
    public var width: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(WIDTH, value)
        }
    public fun <T> width(
        column: ColumnReference<T>,
        //parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            WIDTH,
            column.name(),
            null//LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }

    public fun width(
        column: String,
       // parameters: LetsPlotNonPositionalMappingParameters<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return addNonPositionalMapping(
            WIDTH,
            column,
            null
            //LetsPlotNonPositionalMappingParameters<Any?, Double>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> width(
        values: Iterable<T>,
        name: String? = null,
        //parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            WIDTH,
            values.toList(),
            name,
            null
            //LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }

    public fun <T> width(
        values: DataColumn<T>,
        //name: String? = null,
        //parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            WIDTH,
            values,
            null
           // LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }
}