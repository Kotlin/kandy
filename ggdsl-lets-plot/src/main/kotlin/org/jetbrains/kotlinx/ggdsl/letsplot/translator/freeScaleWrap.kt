package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.bindings.FreeScale
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalFreeScale
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.letsPlot.intern.Feature
import kotlin.reflect.typeOf

internal fun FreeScale.wrap(featureBuffer: MutableList<Feature>) {
    when (this) {
        is PositionalFreeScale<*> -> featureBuffer.add(
            parameters.scale.wrap(
                aes, typeOf<Any>(),
                (parameters as? LetsPlotPositionalMappingParameters<*>)?.axis,
                false
            )
        )
        else -> TODO()
    }
}
