package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color


@PublishedApi
internal val RIBBON = LetsPlotGeom("ribbon")
@PlotDslMarker
class RibbonContext(parent: LayerCollectorContext) :
    WithBorderLineContext(parent) {
    val x = XAes(this)

    val yMin = YMinAes(this)
    val yMax = YMaxAes(this)

    val color = FillAes(this)
    val alpha = AlphaAes(this)

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
inline fun LayerCollectorContext.ribbon(block: RibbonContext.() -> Unit) {
    addLayer(RibbonContext(this).apply(block), RIBBON)
}
