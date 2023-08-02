/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersCategorical
import org.jetbrains.kotlinx.kandy.letsplot.util.linetype.LineType
import kotlin.reflect.KProperty

public interface WithType : BindingContext {
    public var type: LineType?
        get() = null
        set(value) {
            addNonPositionalSetting(LINE_TYPE, value)
        }

    public fun <T> type(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping<T, LineType>(
            LINE_TYPE,
            column.name(),
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }

    public fun <T> type(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping<T, LineType>(
            LINE_TYPE,
            column.name,
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }

    public fun type(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<Any?, LineType>.() -> Unit = {}
    ): NonPositionalMapping<Any?, LineType> {
        return addNonPositionalMapping(
            LINE_TYPE,
            column,
            LetsPlotNonPositionalMappingParametersCategorical<Any?, LineType>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> type(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping(
            LINE_TYPE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }

    public fun <T> type(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return addNonPositionalMapping(
            LINE_TYPE,
            values,
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }
}
