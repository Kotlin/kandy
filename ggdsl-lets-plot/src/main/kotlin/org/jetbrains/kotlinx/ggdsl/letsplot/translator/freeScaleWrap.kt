package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.scale.FreePositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreeScale
import org.jetbrains.letsPlot.intern.Feature

internal fun FreeScale.wrap(featureBuffer: MutableList<Feature>) {
    when (this) {
        is FreePositionalScale<*> -> featureBuffer.add(scale.wrap(aes, domainType, scaleParameters)!!) // TODO
        else -> TODO()
    }
}
