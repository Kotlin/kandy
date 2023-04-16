/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.letsplot.util.linetype.LineType
import kotlin.reflect.KProperty

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

    public fun <T> lineType(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping<T, LineType>(
            LINE_TYPE,
            column.name,
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