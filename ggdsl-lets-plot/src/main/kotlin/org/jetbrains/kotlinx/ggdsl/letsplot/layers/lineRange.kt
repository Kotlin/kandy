package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.util.color.Color

val LINE_RANGE = LetsPlotGeom("linerange")

class LineRangeContext(override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData) :
    WithBorderLineContext() {
    val yMin = YMinAes(this)
    val yMax = YMaxAes(this)

    val alpha = AlphaAes(this)

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
 *  - [ x][LineRangeContext.x]
 *  - [y][LineRangeContext.y] // TODO - move to another geom???
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [yMin][LineRangeContext.yMin]
 *  - [yMax][LineRangeContext.yMax]
 *
 *   Non-positional:
 *  - [color][LineRangeContext.color] - this line range color, of the type [Color], mappable.
 *  - [alpha][LineRangeContext.alpha] - this line range alpha, of the type [Double], mappable.
 *  - [size][LineRangeContext.size] - this line range  size, of the type [Double], mappable.
 *  - [width][LineRangeContext.width] - this line range  width, of the type [Double], mappable.
 *  - [lineType][LineRangeContext.lineType] - this error bar border line type, of the type [LineType], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][LineRangeContext.data].
 *
 *  @see [BaseBindingContext]
 */
fun PlotContext.lineRange(block: LineRangeContext.() -> Unit) {
    layers.add(LineRangeContext(data).apply { copyFrom(this@lineRange) }.apply(block).toLayer(LINE_RANGE))
}
