package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.scales.AxisParameters


public interface WithXFree: BindingContext {
    @Suppress("UNCHECKED_CAST")
    public val x: AxisParameters
        get() {
            return AxisParameters(bindingCollector.freeScales.getOrPut(X) {
                PositionalFreeScale(X, LetsPlotPositionalMappingParameters<Any?>())
            }.parameters as LetsPlotPositionalMappingParameters<Any?>)
        }

    public fun x(
        parameters: AxisParameters.() -> Unit = {}
    ) {
        x.apply(parameters)
    }
}
