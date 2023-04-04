package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LABEL
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParameters
import kotlin.reflect.KProperty

public interface WithLabel : BindingContext {
    public var label: String?
        get() = null
        set(value) {
            addNonPositionalSetting(LABEL, value)
        }

    public fun <T> label(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, String>.() -> Unit = {}
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(
            LABEL,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, String>().apply(parameters)
        )
    }

    public fun <T> label(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, String>.() -> Unit = {}
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(
            LABEL,
            column.name,
            LetsPlotNonPositionalMappingParameters<T, String>().apply(parameters)
        )
    }

    public fun label(
        column: String,
        parameters: LetsPlotNonPositionalMappingParameters<Any?, String>.() -> Unit = {}
    ): NonPositionalMapping<Any?, String> {
        return addNonPositionalMapping<Any?, String>(
            LABEL,
            column,
            LetsPlotNonPositionalMappingParameters<Any?, String>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> label(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, String>.() -> Unit = {}
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(
            LABEL,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParameters<T, String>().apply(parameters)
        )
    }

    public fun <T> label(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, String>.() -> Unit = {}
    ): NonPositionalMapping<T, String> {
        return addNonPositionalMapping<T, String>(
            LABEL,
            values,
            LetsPlotNonPositionalMappingParameters<T, String>().apply(parameters)
        )
    }
}