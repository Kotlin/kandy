package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.letsplot.*

val AB_LINE = LetsPlotGeom("abline")


class ABLineContext @PublishedApi internal constructor(parent: LayerCollectorContext) :
    LayerContext(parent) {
    val slope = SlopeAes(this)
    val intercept = InterceptAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val type = LineTypeAes(this)
    val width = WidthAes(this)
}

inline fun LayerCollectorContext.abLine(block: ABLineContext.() -> Unit) {
    addLayer(ABLineContext(this).apply(block), AB_LINE)
}


