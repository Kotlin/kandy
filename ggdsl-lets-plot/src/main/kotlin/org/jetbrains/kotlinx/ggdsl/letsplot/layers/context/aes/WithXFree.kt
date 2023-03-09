package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.X

public interface WithXFree: BindingContext {
    public fun <T> x(
        parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
    ) {
        addPositionalFreeScale<T>(X, LetsPlotPositionalMappingParameters<T>().apply(parameters))
    }
}
