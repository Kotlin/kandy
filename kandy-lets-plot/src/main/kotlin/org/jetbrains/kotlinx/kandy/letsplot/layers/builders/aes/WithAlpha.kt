/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.checkInRange
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.*
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import kotlin.reflect.KProperty

/**
 * Interface for configuring the `alpha` (transparency) aesthetic in a plot layer.
 *
 * The interface includes methods to set the alpha aesthetic to a constant value
 * or to map it to a data column.
 * The alpha value should range from 0.0 (fully transparent) to 1.0 (fully opaque).
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithAlpha : WithAes {
    private fun checkInRange(value: Double) {
        ALPHA.checkInRange(value, 0.0..1.0)
    }

    private fun validateScale(scale: NonPositionalScale<*, out Double>) {
        when (scale) {
            is NonPositionalDefaultCategoricalScale, is NonPositionalDefaultScale -> return
            is CustomNonPositionalScale -> return // TODO
            is NonPositionalCategoricalScale -> scale.rangeValues?.forEach { checkInRange(it) }
            is NonPositionalContinuousScale -> {
                scale.rangeMin?.let { checkInRange(it) }
                scale.rangeMax?.let { checkInRange(it) }
            }
        }
    }

    private fun validateParameters(parameters: LetsPlotNonPositionalMappingParametersContinuous<*, Double>) =
        validateScale(parameters.scale)

    /**
     * Sets a constant alpha (transparency) value for the layer.
     *
     * @property alpha the value is ranging from 0.0 (fully transparent) to 1.0 (fully opaque).
     * @throws IllegalArgumentException if value is not in the range [0.0, 1.0].
     */
    public var alpha: Double?
        get() = null
        set(value) {
            value?.let { checkInRange(it) }
            bindingHandler.addNonPositionalSetting(ALPHA, value)
        }

    /**
     * Maps the alpha aesthetic to a data column by [ColumnReference].
     *
     * @param column the data column to map to alpha.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return the created [NonPositionalMapping].
     * @throws IllegalArgumentException if any mapped alpha value is not in the range [0.0, 1.0].
     */
    public fun <T> alpha(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping<T, Double>(
            ALPHA,
            column.name(),
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters).also {
                validateParameters(it)
            }
        )
    }

    /**
     * Maps the alpha aesthetic to a data column by [KProperty].
     *
     * @param column the data column to map to alpha.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return the created [NonPositionalMapping].
     * @throws IllegalArgumentException if any mapped alpha value is not in the range [0.0, 1.0].
     */
    public fun <T> alpha(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping<T, Double>(
            ALPHA,
            column.name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters).also {
                validateParameters(it)
            }
        )
    }

    /**
     * Maps the alpha aesthetic to a data column by [String].
     *
     * @param column the data column to map to alpha.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return the created [NonPositionalMapping].
     * @throws IllegalArgumentException if any mapped alpha value is not in the range [0.0, 1.0].
     */
    public fun alpha(
        column: String,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>.() -> Unit = {}
    ): NonPositionalMapping<Any?, Double> {
        return bindingHandler.addNonPositionalMapping<Any?, Double>(
            ALPHA,
            column,
            LetsPlotNonPositionalMappingParametersContinuous<Any?, Double>().apply(parameters).also {
                validateParameters(it)
            }
        )
    }

    /**
     * Maps the alpha aesthetic to an iterable collection of discrete values.
     *
     * @param values an iterable collection containing the discrete values.
     * @param name optional name for this aesthetic mapping.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return the created [NonPositionalMapping].
     * @throws IllegalArgumentException if any mapped alpha value is not in the range [0.0, 1.0].
     */
    public fun <T> alpha(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping<T, Double>(
            ALPHA,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters).also {
                validateParameters(it)
            }
        )
    }

    /**
     * Maps the alpha aesthetic to a data column.
     *
     * @param values the data column to map to alpha.
     * @param parameters optional lambda to configure additional scale parameters.
     * @return the created [NonPositionalMapping].
     * @throws IllegalArgumentException if any mapped alpha value is not in the range [0.0, 1.0].
     */
    public fun <T> alpha(
        values: DataColumn<T>,
        parameters: LetsPlotNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping<T, Double>(
            ALPHA,
            values,
            LetsPlotNonPositionalMappingParametersContinuous<T, Double>().apply(parameters).also {
                validateParameters(it)
            }
        )
    }
}