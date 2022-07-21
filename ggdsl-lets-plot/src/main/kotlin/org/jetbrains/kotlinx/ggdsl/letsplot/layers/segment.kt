package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonScalablePositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom

val SEGMENT = LetsPlotGeom("segment")


// todo better names?
class SegmentContext(override var data: MutableNamedData) :
    LayerContext() {
    val xEnd = XEndAes(this)
    val yEnd = YEndAes(this)
    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val lineType = LineTypeAes(this)
    val width = SizeAes(this)

    // todo speed and flow
}

/**
 * Adds a new line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * area {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineContext.x]
 *  - [y][LineContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][LineContext.color] - this line color, of the type [Color], mappable TODO.
 *  - [alpha][LineContext.alpha] - this line alpha, of the type [Double], mappable TODO.
 *  - [borderColor][LineContext.type] - this line type, of the type [LineType], non-mappable.
 *  - [borderWidth][LineContext.width] - this line width, of the type [Double], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][AreaContext.data].
 *
 *  @see [BaseBindingContext]
 */
fun PlotContext.segment(block: SegmentContext.() -> Unit) {
    layers.add(SegmentContext(data).apply { copyFrom(this@segment) }.apply(block).toLayer(SEGMENT))
}
