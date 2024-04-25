/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersCategorical
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `lineType` aesthetic, which specifies the type of lines in the plot.
 *
 * This interface allows you to specify the lineType as a constant, map it to a column, or provide iterable of values.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithLineType : WithAes {

    /**
     * Sets a constant `lineType` for the layer.
     *
     * @property lineType the value to be set.
     */
    public var lineType: LineType?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(LINE_TYPE, value)
        }

    /**
     * Maps the `lineType` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> lineType(
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
     * Maps the `lineType` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> lineType(
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
     * Maps the `lineType` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun lineType(
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
     * Maps the `lineType` aesthetic to iterable of values.
     *
     * @param values the iterable containing the values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> lineType(
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
     * Maps the `lineType` aesthetic to a data column.
     *
     * @param values the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> lineType(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersCategorical<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> {
        return bindingHandler.addNonPositionalMapping(
            LINE_TYPE,
            values,
            LetsPlotNonPositionalMappingParametersCategorical<T, LineType>().apply(parameters)
        )
    }
}
