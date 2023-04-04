package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import kotlin.reflect.KProperty

public interface WithSize : BindingContext {
    public var size: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(SIZE, value)
        }
    public fun <T> size(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            SIZE,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }

    public fun <T> size(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            SIZE,
            column.name,
            LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }

    public fun size(
        column: String,
        parameters: LetsPlotNonPositionalMappingParameters<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return addNonPositionalMapping(
            SIZE,
            column,
            LetsPlotNonPositionalMappingParameters<Any?, Double>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> size(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            SIZE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }

    public fun <T> size(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            SIZE,
            values,
            LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }
}