package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val V_LINE: LetsPlotGeom = LetsPlotGeom("vLine")


@PlotDslMarker
public class VLineContext(override var data: MutableNamedData) : LayerContext() {
    public val x: XInterceptAes = XInterceptAes(this)

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
    public val type: LineTypeAes = LineTypeAes(this)
    public val width: SizeAes = SizeAes(this)
}


public inline fun PlotContext.vLine(block: VLineContext.() -> Unit) {
    layers.add(VLineContext(data).apply { copyFrom(this@vLine) }.apply(block).toLayer(V_LINE))
}
