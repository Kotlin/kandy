/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParameters

/**
 * Interface for managing the 'x' axis with more flexible or "free" settings.
 *
 * The `WithXFree` interface provides a way
 * to customize the x-axis settings using a more flexible API than standard positional aesthetics.
 * This is useful for specifying more advanced properties of the x-axis.
 *
 * You can manipulate the `x` axis parameters by passing a lambda to the `x` function,
 * which takes an `AxisParameters` object as its receiver.
 */
public interface WithXFree : BindingContext {

    /**
     * Gets the current `AxisParameters` for the x-axis.
     *
     * This property retrieves an existing `AxisParameters` object or creates a new one if it does not already exist.
     * You can modify this object to adjust the parameters of the x-axis in a more freeform manner.
     *
     * @property x an [AxisParameters] object representing the x-axis parameters.
     */
    @Suppress("UNCHECKED_CAST")
    public val x: AxisParameters
        get() {
            return AxisParameters(bindingCollector.freeScales.getOrPut(X) {
                PositionalFreeScale(X, LetsPlotPositionalMappingParametersContinuous<Any?>())
            }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>)
        }

    public fun x(parameters: AxisParameters.() -> Unit = {}) {
        x.apply(parameters)
    }
}
