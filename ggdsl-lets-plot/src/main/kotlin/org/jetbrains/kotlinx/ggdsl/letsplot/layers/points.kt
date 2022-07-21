package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

val POINT = LetsPlotGeom("point")

// TODO add size unit???

class PointsContext(override var data: MutableNamedData) : LayerContext() {
    val symbol = ShapeAes(this)

    val size = SizeAes(this)
    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    // FILL SHAPES only
    val borderWidth = StrokeAes(this) // TODO doesnt work lol
    val fillColor = FillAes(this)

}

fun PlotContext.points(block: PointsContext.() -> Unit) {
    layers.add(PointsContext(data).apply { copyFrom(this@points) }
        .apply(block).toLayer(POINT))
}

/*
class BorderSubContext : BindingContext() {
    override var data: MutableNamedData = mutableMapOf()
    val width = STROKE // TODO doesnt work lol
    val color = COLOR
}

class PointsFilledContext(override var data: MutableNamedData) : LayerContext() {
    val size = SIZE
    val color = FILL
    val alpha = ALPHA

    val border = BorderSubContext()

    inline operator fun BorderSubContext.invoke(block: BorderSubContext.() -> Unit) {
        apply(block)
        this@PointsFilledContext.copyFrom(this, false)
    }

    val symbol = FILLED_SYMBOL
}

class PointsUnfilledContext(override var data: MutableNamedData) : LayerContext() {
    val size = SIZE
    val color = COLOR
    val alpha = ALPHA

    val symbol = UNFILLED_SYMBOL
}

fun PlotContext.pointsFilled(block: PointsFilledContext.() -> Unit) {
    layers.add(PointsFilledContext(data).apply { copyFrom(this@pointsFilled) }
        .apply(block).toLayer(POINT))
}

fun PlotContext.pointsUnfilled(block: PointsUnfilledContext.() -> Unit) {
    layers.add(PointsUnfilledContext(data).apply { copyFrom(this@pointsUnfilled) }
        .apply(block).toLayer(POINT))
}

 */
