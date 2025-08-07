/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addNonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.FILL
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `fillColor` aesthetic in a plot layer.
 *
 * It defines methods and properties that allow you to set the filling color either as a constant
 * or by mapping it to a column in your data.
 * The mapped colors can also be further customized.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithFillColor : WithAes {

    /**
     * Sets a constant color for the layer.
     *
     * @property fillColor the value to be set.
     */
    public var fillColor: Color?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(FILL, value)
        }

    /**
     * Maps the `fillColor` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> fillColor(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return bindingHandler.addNonPositionalMapping<T, Color>(
            FILL,
            column.name(),
            LetsPlotNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }

    /**
     * Maps the `fillColor` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> fillColor(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return bindingHandler.addNonPositionalMapping<T, Color>(
            FILL,
            column.name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }

    /**
     * Maps the `fillColor` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun fillColor(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<Any?, Color>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Color> {
        return bindingHandler.addNonPositionalMapping<Any?, Color>(
            FILL,
            column,
            LetsPlotNonPositionalMappingParametersContinuous<Any?, Color>().apply(parameters)
        )
    }

    /**
     * Maps the `fillColor` aesthetic to an iterable collection of discrete values.
     *
     * @param values an iterable collection containing the discrete values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> fillColor(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return bindingHandler.addNonPositionalMapping<T, Color>(
            FILL,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }

    /**
     * Maps the `fillColor` aesthetic to a data column.
     *
     * @param values the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> fillColor(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return bindingHandler.addNonPositionalMapping<T, Color>(
            FILL,
            values,
            LetsPlotNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }
}