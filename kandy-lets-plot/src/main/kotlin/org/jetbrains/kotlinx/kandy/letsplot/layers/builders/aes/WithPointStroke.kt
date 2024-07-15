@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addNonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.STROKE
import kotlin.reflect.KProperty

/**
 * Interface for managing the `stroke` aesthetic.
 *
 * Affects the thickness of the point boundaries (in case the given shape has a boundary).
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithPointStroke : WithAes {

    /**
     *
     * @property stroke a numeric value that represents the width of the stroke.
     */
    public var stroke: Number?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(STROKE, value?.toDouble())
        }

    /**
     * Maps the `stroke` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> stroke(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            STROKE,
            column.name(),
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `stroke` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> stroke(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            STROKE,
            column.name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `stroke` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun stroke(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return bindingHandler.addNonPositionalMapping(
            STROKE,
            column,
            LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `stroke` aesthetic to iterable of discrete values.
     *
     * @param values the iterable containing the discrete values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> stroke(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            STROKE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `stroke` aesthetic to a data column.
     *
     * @param values the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> stroke(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            STROKE,
            values,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}