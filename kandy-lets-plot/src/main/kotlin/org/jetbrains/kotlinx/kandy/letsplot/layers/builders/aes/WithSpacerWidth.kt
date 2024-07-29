@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addNonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.SPACER_WIDTH
import kotlin.reflect.KProperty

/**
 * Interface for managing the `spacerWidth` aesthetic.
 *
 * Affects the line width between sectors. Spacers are not applied
 * to exploded sectors and to sides of adjacent sectors.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithSpacerWidth : WithAes {

    /**
     *
     * @property stroke a numeric value that represents the width of the stroke.
     */
    public var spacerWidth: Number?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(SPACER_WIDTH, value?.toDouble())
        }

    /**
     * Maps the `spacerWidth` aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> spacerWidth(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            SPACER_WIDTH,
            column.name(),
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `spacerWidth` aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> spacerWidth(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            SPACER_WIDTH,
            column.name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `spacerWidth` aesthetic to a data column by [String].
     *
     * @param column the data column to map to the size.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun spacerWidth(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return bindingHandler.addNonPositionalMapping(
            SPACER_WIDTH,
            column,
            LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `spacerWidth` aesthetic to iterable of discrete values.
     *
     * @param values the iterable containing the discrete values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return a [NonPositionalMapping] object representing the mapping.
     */
    public fun <T> spacerWidth(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            SPACER_WIDTH,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }

    /**
     * Maps the `spacerWidth` aesthetic to a data column.
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
            SPACER_WIDTH,
            values,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}