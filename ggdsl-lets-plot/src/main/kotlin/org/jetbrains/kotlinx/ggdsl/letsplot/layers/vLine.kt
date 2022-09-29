package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val V_LINE = LetsPlotGeom("vLine")


@PlotDslMarker
class VLineContext(parent: LayerCollectorContext) : LayerContext(parent) {
    val x = XInterceptAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val type = LineTypeAes(this)
    val width = SizeAes(this)
}


inline fun LayerCollectorContext.vLine(block: VLineContext.() -> Unit) {
    addLayer(VLineContext(this).apply(block), V_LINE)
}
