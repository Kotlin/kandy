package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

// TODO

@PublishedApi
internal val BAR = LetsPlotGeom("bar")



class BarContext(override var data: MutableNamedData) : WithBorderLineContext() {

    val color = FillAes(this)
    val alpha = AlphaAes(this)
    val width = WidthAes(this)

}

/**
 * Adds a new bar layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * bar {
 *    x(source<Double>("time")) // mapping from data source to 'x' coordinate
 *    borderLine.color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BarContext.x]
 *  - [y][BarContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][BarContext.color] - color of the bar filling, of the type [Color], mappable
 *  - [alpha][BarContext.alpha] - layer alpha, of the type [Double], mappable
 *  - [width][BarContext.width] - width of the bar, of the type [Double], mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable
 *
 *  // TODO write about borderLine invocation?
 *  ```
 *  bar {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *
 * // TODO move data overriding to args
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][BarContext.data].
 *
 *  // TODO refer to bindings?
 */
inline fun PlotContext.bar(block: BarContext.() -> Unit) {
    layers.add(BarContext(data).apply { copyFrom(this@bar) }.apply(block).toLayer(BAR))
}
