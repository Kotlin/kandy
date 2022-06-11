package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Geom
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color

val BOXPLOT = Geom("boxplot")

class BoxplotContext(override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData) : org.jetbrains.kotlinx.ggdsl.dsl.LayerContext() {

    val lower = LOWER
    val upper = UPPER
    val middle = MIDDLE
    val yMin = Y_MIN
    val yMax = Y_MAX

    val color = COLOR
    val alpha = ALPHA

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR
}

/**
 * Adds a new boxplot layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * boxplot {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    borderColor(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BoxplotContext.x]
 *  - [y][BoxplotContext.y] // TODO - move to another geom???
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [lower][BoxplotContext.lower]
 *  - [upper][BoxplotContext.upper]
 *  - [middle][BoxplotContext.middle]
 *  - [yMin][BoxplotContext.yMin]
 *  - [yMax][BoxplotContext.yMax]
 *
 *   Non-positional:
 *  - [color][BoxplotContext.color] - this boxplot fill color, of the type [Color], mappable.
 *  - [alpha][BoxplotContext.alpha] - this boxplot fill alpha, of the type [Double], mappable.
 *  - [borderColor][BoxplotContext.borderColor] - this boxplot border color, of the type [Color], non-mappable.
 *  - [borderWidth][BoxplotContext.borderWidth] - this boxplot border width, of the type [Double], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][BoxplotContext.data].
 *
 *  @see [BaseBindingContext]
 */
fun org.jetbrains.kotlinx.ggdsl.dsl.PlotContext.boxplot(block: BoxplotContext.() -> Unit) {
    layers.add(BoxplotContext(data).apply { copyFrom(this@boxplot) }.apply(block).toLayer(BOXPLOT))
}
