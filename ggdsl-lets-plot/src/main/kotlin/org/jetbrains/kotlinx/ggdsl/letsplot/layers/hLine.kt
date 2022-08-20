package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val H_LINE = LetsPlotGeom("hLine")



@PlotDslMarker
class HLineContext(override var data: MutableNamedData) : LayerContext() {
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val type = LineTypeAes(this)
    val width = SizeAes(this)
}


inline fun PlotContext.hLine(block: HLineContext.() -> Unit) {
    layers.add(HLineContext(data).apply { copyFrom(this@hLine) }.apply(block).toLayer(H_LINE))
}
