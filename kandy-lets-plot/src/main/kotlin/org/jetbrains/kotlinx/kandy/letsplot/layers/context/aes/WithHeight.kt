/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.HEIGHT
import kotlin.reflect.KProperty

public interface WithHeight : BindingContext {
    public var height: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(HEIGHT, value)
        }
    public fun <T> height(
        column: ColumnReference<T>,
        //parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            HEIGHT,
            column.name(),
            null//LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }

    public fun <T> height(
        column: KProperty<T>,
        //parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            HEIGHT,
            column.name,
            null//LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }

    public fun height(
        column: String,
       // parameters: LetsPlotNonPositionalMappingParameters<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return addNonPositionalMapping(
            HEIGHT,
            column,
            null
            //LetsPlotNonPositionalMappingParameters<Any?, Double>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> height(
        values: Iterable<T>,
        name: String? = null,
        //parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            HEIGHT,
            values.toList(),
            name,
            null
            //LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }

    public fun <T> height(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping(
            HEIGHT,
            values,
            null
           // LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }
}