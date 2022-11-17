package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledMapping
import org.jetbrains.letsPlot.intern.Feature

internal fun Layer.wrap(featureBuffer: MutableList<Feature>) {
    featureBuffer.add(LayerWrapper(this))
    freeScales.forEach { (_, freeScale) -> freeScale.wrap(featureBuffer) }
    mappings.forEach { (aes, mapping) ->
        if (mapping is ScaledMapping<*>) {
            mapping.columnScaled.scale.wrap(aes, mapping.domainType, mapping.scaleParameters)?.let {
                featureBuffer.add(it)
            }
        }
    }
}
