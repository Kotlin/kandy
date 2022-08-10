package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonScalablePositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val SEGMENT = LetsPlotGeom("segment")

/* TODO
// todo better names?
class SegmentContext(override var data: MutableNamedData) :
    LayerContext() {
    // todo replace x and y with xBegin and yBegin?
    val xEnd = XEndAes(this)
    val yEnd = YEndAes(this)
    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val lineType = LineTypeAes(this)
    val width = SizeAes(this)

    // todo speed and flow
}

/**
 * Adds a new ribbon layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * ribbon {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointRangeContext.x]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).

 *  Sub-positional:
 *
 *  - [yMin][RibbonContext.yMin]
 *  - [yMax][RibbonContext.yMax]
 *
 *   Non-positional:
 *  - [color][RibbonContext.color] - color of the raster filling, of the type [Color], mappable
 *  - [alpha][RibbonContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.

 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][RibbonContext.data].
 *
 * // TODO move data overriding to args
 *  ```
 *  ribbon {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *  // TODO refer to bindings?
 */
fun PlotContext.segment(block: SegmentContext.() -> Unit) {
    layers.add(SegmentContext(data).apply { copyFrom(this@segment) }.apply(block).toLayer(SEGMENT))
}

 */
