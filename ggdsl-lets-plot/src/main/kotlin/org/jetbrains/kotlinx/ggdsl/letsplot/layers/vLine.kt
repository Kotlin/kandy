package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val V_LINE = LetsPlotGeom("vLine")


@PlotDslMarker
class VLineContext(override var data: MutableNamedData) : LayerContext() {
    val x = XAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val type = LineTypeAes(this)
    val width = SizeAes(this)
}


inline fun PlotContext.vLine(block: HLineContext.() -> Unit) {
    layers.add(HLineContext(data).apply { copyFrom(this@vLine) }.apply(block).toLayer(H_LINE))
}
