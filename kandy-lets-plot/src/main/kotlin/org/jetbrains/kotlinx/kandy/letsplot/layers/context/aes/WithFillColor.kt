/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.FILL
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.reflect.KProperty

public interface WithFillColor : BindingContext {
    public var fillColor: Color?
        get() = null
        set(value) {
            addNonPositionalSetting(FILL, value)
        }

    public fun <T> fillColor(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            FILL,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }

    public fun <T> fillColor(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            FILL,
            column.name,
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }

    public fun fillColor(
        column: String,
        parameters: LetsPlotNonPositionalMappingParameters<Any?, Color>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Color> {
        return addNonPositionalMapping<Any?, Color>(
            FILL,
            column,
            LetsPlotNonPositionalMappingParameters<Any?, Color>().apply(parameters)
        )
    }

    public fun <T> fillColor(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            FILL,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }

    public fun <T> fillColor(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            FILL,
            values,
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }
}