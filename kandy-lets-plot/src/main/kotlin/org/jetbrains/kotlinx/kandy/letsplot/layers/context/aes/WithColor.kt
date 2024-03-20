/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `color` aesthetic in a plot layer.
 *
 * It defines methods and properties that allow you to set the color either as a constant
 * or by mapping it to a column in your data.
 * The mapped colors can also be further customized.
 */
public interface WithColor : BindingContext {

    /**
     * Sets a constant `color` for the layer.
     *
     * @property color the value to be set.
     */
    public var color: Color?
        get() = null
        set(value) {
            addNonPositionalSetting(COLOR, value)
        }

    /**
     * Maps the `color` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> color(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            column.name(),
            LetsPlotNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }

    /**
     * Maps the `color` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> color(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            column.name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }

    /**
     * Maps the `color` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun color(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<Any?, Color>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Color> {
        return addNonPositionalMapping<Any?, Color>(
            COLOR,
            column,
            LetsPlotNonPositionalMappingParametersContinuous<Any?, Color>().apply(parameters)
        )
    }

    /**
     * Maps the `color` aesthetic to iterable of discrete values.
     *
     * @param values the iterable containing the discrete values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> color(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }

    /**
     * Maps the `color` aesthetic to a data column.
     *
     * @param values the data column to map to the color.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> color(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            values,
            LetsPlotNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }
}