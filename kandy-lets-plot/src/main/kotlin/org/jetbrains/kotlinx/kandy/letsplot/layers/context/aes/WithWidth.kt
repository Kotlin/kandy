/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.WIDTH
import kotlin.reflect.KProperty

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

    public fun <T> width(
        column: KProperty<T>,
        //parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            WIDTH,
            column.name,
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