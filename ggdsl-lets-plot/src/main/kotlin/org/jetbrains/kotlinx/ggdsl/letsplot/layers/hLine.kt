package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val H_LINE = LetsPlotGeom("hLine")



@PlotDslMarker
class HLineContext(parent: LayerCollectorContext) : LayerContext(parent) {
    val y = YInterceptAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val type = LineTypeAes(this)
    val width = SizeAes(this)
}


inline fun LayerCollectorContext.hLine(block: HLineContext.() -> Unit) {
    addLayer(HLineContext(this).apply(block), H_LINE)
}
