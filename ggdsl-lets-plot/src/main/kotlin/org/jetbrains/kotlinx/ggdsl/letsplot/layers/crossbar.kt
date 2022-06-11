package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Geom
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.letsplot.FATTEN
import org.jetbrains.kotlinx.ggdsl.letsplot.MIDDLE
import org.jetbrains.kotlinx.ggdsl.letsplot.Y_MAX
import org.jetbrains.kotlinx.ggdsl.letsplot.Y_MIN
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

val CROSSBAR = Geom("crossbar")

class CrossBarContext(override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData) : org.jetbrains.kotlinx.ggdsl.dsl.LayerContext(){
    val yMin = Y_MIN
    val yMax = Y_MAX
    val middle = MIDDLE

    val fatten = FATTEN

    //val size = SIZE
    val color = COLOR
    val alpha = ALPHA

    val borderColor = BORDER_COLOR

    // TODO LINE WIDTH??? BORDER WIDTH??
    val borderWidth = BORDER_WIDTH
    val lineType = LINE_TYPE
}

/**
 * Adds a new cross bar layer.
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
 *  - [ x][CrossBarContext.x]
 *  - [y][CrossBarContext.y] // TODO - move to another geom???
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [middle][CrossBarContext.middle]
 *  - [yMin][CrossBarContext.yMin]
 *  - [yMax][CrossBarContext.yMax]
 *
 *   Non-positional:
 *  - [color][CrossBarContext.color] - this cross bar fill color, of the type [Color], mappable.
 *  - [alpha][CrossBarContext.alpha] - this cross bar fill alpha, of the type [Double], mappable.
 *  - [borderColor][CrossBarContext.borderColor] - this cross bar border color, of the type [Color], non-mappable.
 *  - [borderWidth][CrossBarContext.borderWidth] - this cross bar border width, of the type [Double], non-mappable.
 *  - [fatten][CrossBarContext.fatten] - this cross bar border width, of the type [Double], non-mappable.
 *  - [lineType][CrossBarContext.lineType] - this cross bar border line type, of the type [LineType], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][CrossBarContext.data].
 *
 *  @see [BaseBindingContext]
 */
fun org.jetbrains.kotlinx.ggdsl.dsl.PlotContext.crossBar(block: CrossBarContext.() -> Unit) {
    layers.add(CrossBarContext(data).apply { copyFrom(this@crossBar) }.apply(block).toLayer(CROSSBAR))
}