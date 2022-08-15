package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val RECT = LetsPlotGeom("rect")

@PlotDslMarker
class RectContext(override var data: MutableNamedData) :
    WithBorderLineContext() {
    val xMin = XMinAes(this)
    val xMax = XMaxAes(this)
    val yMin = YMinAes(this)
    val yMax = YMaxAes(this)

    val color = FillAes(this)
    val alpha = AlphaAes(this)
}

/**
 * Adds a new rect layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * rect {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Sub-positional:
 *
 *  - [xMin][RectContext.xMin]
 *  - [yMin][RectContext.yMin]
 *  - [xMax][RectContext.xMax]
 *  - [yMax][RectContext.yMax]
 *
 *   Non-positional:
 *  - [color][RectContext.color] - color of the raster filling, of the type [Color], mappable
 *  - [alpha][RectContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.

 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][RasterContext.data].
 *
 * // TODO move data overriding to args
 *  ```
 *  rect {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *  // TODO refer to bindings?
 */
inline fun PlotContext.rect(block: RectContext.() -> Unit) {
    layers.add(RectContext(data).apply { copyFrom(this@rect) }.apply(block).toLayer(RECT))
}
