package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

val BOXPLOT = LetsPlotGeom("boxplot")

class BoxplotContext(override var data: MutableNamedData) : WithBorderLineContext() {

    val lower = LowerAes(this)
    val upper = UpperAes(this)
    val middle = MiddleAes(this)
    val yMin = YMinAes(this)
    val yMax = YMaxAes(this)

    val fatten = FattenAes(this)

    val color = FillAes(this)
    val alpha = AlphaAes(this)
}

/**
 * Adds a new boxplot layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * boxplot {
 *    x(source<Double>("time")) // mapping from data source to 'x' coordinate
 *    borderLine.color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BoxplotContext.x]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [lower][BoxplotContext.lower] - lower hinge of the boxplot
 *  - [upper][BoxplotContext.upper] - upper hinge of the boxplot
 *  - [middle][BoxplotContext.middle] - median of the boxplot
 *  - [yMin][BoxplotContext.yMin] - lower whisker of the boxplot
 *  - [yMax][BoxplotContext.yMax] - upper whisker of the boxplot
 *
 *   Non-positional:
 *  - [color][BoxplotContext.color] - color of the boxplot filling, of the type [Color], mappable
 *  - [alpha][BoxplotContext.alpha] - layer alpha, of the type [Double], mappable
 *  - [fatten][BoxplotContext.fatten] - a multiplicative factor applied to size of the middle bar, of the type [Double], non-mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.
 *
 *  // TODO write about borderLine invocation?
 *  ```
 *  boxplot {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *
 * // TODO move data overriding to args
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][BoxplotContext.data].
 *
 *  // TODO refer to bindings?
 */
fun PlotContext.boxplot(block: BoxplotContext.() -> Unit) {
    layers.add(BoxplotContext(data).apply { copyFrom(this@boxplot) }.apply(block).toLayer(BOXPLOT))
}
