/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import kotlin.reflect.KProperty

public interface WithWidthAsSize : BindingContext {
    public var width: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(SIZE, value)
        }

    public fun <T> width(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            SIZE,
            column.name(),
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    public fun <T> width(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            SIZE,
            column.name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    public fun width(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return addNonPositionalMapping(
            SIZE,
            column,
            LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> width(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            SIZE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    public fun <T> width(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            SIZE,
            values,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}