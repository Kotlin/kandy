/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.FONT_FAMILY
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.letsplot.util.font.FontFamily
import kotlin.reflect.KProperty

public interface WithFamily : BindingContext {
    public var family: FontFamily?
        get() = null
        set(value) {
            addNonPositionalSetting(FONT_FAMILY, value)
        }

    public fun <T> family(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, FontFamily>.() -> Unit = {}
    ): NonPositionalMapping<T, FontFamily> {
        return addNonPositionalMapping<T, FontFamily>(
            FONT_FAMILY,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, FontFamily>().apply(parameters)
        )
    }

    public fun <T> family(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, FontFamily>.() -> Unit = {}
    ): NonPositionalMapping<T, FontFamily> {
        return addNonPositionalMapping<T, FontFamily>(
            FONT_FAMILY,
            column.name,
            LetsPlotNonPositionalMappingParameters<T, FontFamily>().apply(parameters)
        )
    }

    public fun family(
        column: String,
        parameters: LetsPlotNonPositionalMappingParameters<Any?, FontFamily>.() -> Unit = {}
    ): NonPositionalMapping<Any?, FontFamily> {
        return addNonPositionalMapping<Any?, FontFamily>(
            FONT_FAMILY,
            column,
            LetsPlotNonPositionalMappingParameters<Any?, FontFamily>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> family(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, FontFamily>.() -> Unit = {}
    ): NonPositionalMapping<T, FontFamily> {
        return addNonPositionalMapping<T, FontFamily>(
            FONT_FAMILY,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParameters<T, FontFamily>().apply(parameters)
        )
    }

    public fun <T> family(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, FontFamily>.() -> Unit = {}
    ): NonPositionalMapping<T, FontFamily> {
        return addNonPositionalMapping<T, FontFamily>(
            FONT_FAMILY,
            values,
            LetsPlotNonPositionalMappingParameters<T, FontFamily>().apply(parameters)
        )
    }
}