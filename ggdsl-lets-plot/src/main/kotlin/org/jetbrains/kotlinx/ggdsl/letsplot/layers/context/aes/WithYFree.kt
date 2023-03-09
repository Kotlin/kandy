package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.Y

public interface WithYFree: BindingContext {
    public fun <T> y(
        parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
    ) {
        addPositionalFreeScale<T>(Y, LetsPlotPositionalMappingParameters<T>().apply(parameters))
    }
}
