/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParameters

/**
 * Interface for managing the 'y' axis with more flexible or "free" settings.
 *
 * The `WithYFree` interface provides a way
 * to customize the y-axis settings using a more flexible API than standard positional aesthetics.
 * This is useful for specifying more advanced properties of the y-axis.
 *
 * You can manipulate the `y` axis parameters by passing a lambda to the `y` function,
 * which takes an `AxisParameters` object as its receiver.
 */
public interface WithYFree : BindingContext {

    /**
     * Gets the current `AxisParameters` for the y-axis.
     *
     * This property retrieves an existing `AxisParameters` object or creates a new one if it does not already exist.
     * You can modify this object to adjust the parameters of the y-axis in a more freeform manner.
     *
     * @property y an [AxisParameters] object representing the y-axis parameters.
     */
    @Suppress("UNCHECKED_CAST")
    public val y: AxisParameters
        get() {
            return AxisParameters(bindingCollector.freeScales.getOrPut(Y) {
                PositionalFreeScale(Y, LetsPlotPositionalMappingParametersContinuous<Any?>())
            }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>)
        }

    public fun y(parameters: AxisParameters.() -> Unit = {}) {
        y.apply(parameters)
    }
}
