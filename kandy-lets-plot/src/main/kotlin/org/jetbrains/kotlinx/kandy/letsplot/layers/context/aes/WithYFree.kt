package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.scales.AxisParameters


public interface WithYFree: BindingContext {
    @Suppress("UNCHECKED_CAST")
    public val y: AxisParameters
        get() {
            return AxisParameters(bindingCollector.freeScales.getOrPut(Y) {
                PositionalFreeScale(Y, LetsPlotPositionalMappingParameters<Any?>())
            }.parameters as LetsPlotPositionalMappingParameters<Any?>)
        }

    public fun y(
        parameters: AxisParameters.() -> Unit = {}
    ) {
        y.apply(parameters)
    }
}
