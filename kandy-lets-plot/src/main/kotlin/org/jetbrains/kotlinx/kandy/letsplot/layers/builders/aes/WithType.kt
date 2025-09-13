/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addNonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersCategorical
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `type` aesthetic, which specifies the type of lines in the plot.
 *
 * This interface allows you to specify the label as a constant, map it to a column, or provide iterable of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithType : WithAes {

    /**
     * Sets a constant `type` for line in the layer.
     *
     * @property type the value to be set.
     */
    public var type: LineType?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(LINE_TYPE, value)
        }

    /**
     * Maps the `type` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the type.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> type(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return bindingHandler.addNonPositionalMapping<T, LineType>(
            LINE_TYPE,
            column.name(),
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }

    /**
     * Maps the `type` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the type.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> type(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return bindingHandler.addNonPositionalMapping<T, LineType>(
            LINE_TYPE,
            column.name,
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }

    /**
     * Maps the `type` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the type.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun type(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<Any?, LineType>.() -> Unit = {}
    ): NonPositionalMapping<Any?, LineType> {
        return bindingHandler.addNonPositionalMapping(
            LINE_TYPE,
            column,
            LetsPlotNonPositionalMappingParametersCategorical<Any?, LineType>().apply(parameters)
        )
    }

    /**
     * Maps the `type` aesthetic to iterable of values.
     *
     * @param values the iterable containing the categorical values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> type(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return bindingHandler.addNonPositionalMapping(
            LINE_TYPE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }

    /**
     * Maps the `type` aesthetic to a data column.
     *
     * @param values the data column to map to the type.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> type(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return bindingHandler.addNonPositionalMapping(
            LINE_TYPE,
            values,
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }
}
