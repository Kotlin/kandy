package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledMapping
import org.jetbrains.letsPlot.intern.Feature
import kotlin.reflect.KType

internal fun Layer.wrap(featureBuffer: MutableList<Feature>, groupedDataPlot: Boolean) {
    featureBuffer.add(LayerWrapper(this, groupedDataPlot))
    freeScales.forEach { (_, freeScale) -> freeScale.wrap(featureBuffer) }
    mappings.forEach { (aes, mapping) ->
        if (mapping is ScaledMapping<*>) {
            // TODO!!!
            val type = mapping.domainType
            mapping.columnScaled.scale.wrap(aes, type/* mapping.domainType*/, mapping.scaleParameters)?.let {
                featureBuffer.add(it)
            }
        }
    }
}
