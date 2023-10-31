/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersCategorical
import org.jetbrains.kotlinx.kandy.letsplot.internal.SHAPE
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import kotlin.reflect.KProperty

public interface WithSymbol : BindingContext {
    public var symbol: Symbol?
        get() = null
        set(value) {
            addNonPositionalSetting(SHAPE, value)
        }

    public fun <T> symbol(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping<T, Symbol>(
            SHAPE,
            column.name(),
            LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>().apply(parameters)
        )
    }

    public fun <T> symbol(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping<T, Symbol>(
            SHAPE,
            column.name,
            LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>().apply(parameters)
        )
    }

    public fun symbol(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<Any?, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Symbol> {
        return addNonPositionalMapping(
            SHAPE,
            column,
            LetsPlotNonPositionalMappingParametersCategorical<Any?, Symbol>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> symbol(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping(
            SHAPE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>().apply(parameters)
        )
    }

    public fun <T> symbol(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping(
            SHAPE,
            values,
            LetsPlotNonPositionalMappingParametersCategorical<T, Symbol>().apply(parameters)
        )
    }
}