package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

val POINT_RANGE = LetsPlotGeom("pointrange")

class PointRangeContext(override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData) : org.jetbrains.kotlinx.ggdsl.dsl.LayerContext(){
    val yMin = Y_MIN
    val yMax = Y_MAX

    // TODO pointSize
    val symbol = SYMBOL
    val size = SIZE
    val pointColor = COLOR

    val alpha = ALPHA

    val fatten = FATTEN

    // todo
    val lineColor = MAPPABLE_BORDER_COLOR
   // val width = WIDTH
    val lineType = LINE_TYPE
}

/**
 * Adds a new line range layer.
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
 *  - [ x][PointRangeContext.x]
 *  - [y][PointRangeContext.y] // TODO - move to another geom???
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [yMin][PointRangeContext.yMin]
 *  - [yMax][PointRangeContext.yMax]
 *
 *   Non-positional:
 *  - [pointColor][PointRangeContext.pointColor] - this point color, of the type [Color], mappable.
 *  - [symbol][PointRangeContext.symbol] - this point symbol, of the type [Color], mappable.
 *  - [alpha][PointRangeContext.alpha] - this point range alpha, of the type [Double], mappable.
 *  - [size][PointRangeContext.size] - this point size, of the type [Double], mappable.
 *  - [fatten][PointRangeContext.fatten] - this line fatten, of the type [Double], non-mappable.
 *  - [lineType][PointRangeContext.lineType] - this line type, of the type [LineType], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][PointRangeContext.data].
 *
 *  @see [BaseBindingContext]
 */
fun org.jetbrains.kotlinx.ggdsl.dsl.PlotContext.pointRange(block: PointRangeContext.() -> Unit) {
    layers.add(PointRangeContext(data).apply { copyFrom(this@pointRange) }.apply(block).toLayer(POINT_RANGE))
}
