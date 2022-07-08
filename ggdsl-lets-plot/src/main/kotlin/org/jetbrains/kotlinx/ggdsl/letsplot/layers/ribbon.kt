package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.Y_MAX
import org.jetbrains.kotlinx.ggdsl.letsplot.Y_MIN
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

val RIBBON = LetsPlotGeom("ribbon")

class RibbonContext(override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData) :
    org.jetbrains.kotlinx.ggdsl.dsl.LayerContext() {
    val yMin = Y_MIN
    val yMax = Y_MAX

    val color = FILL
    val alpha = ALPHA

    val borderLine =  BorderLineSubContext()

    inline operator fun BorderLineSubContext.invoke(block: BorderLineSubContext.() -> Unit) {
        apply(block)
        this@RibbonContext.copyFrom(this, false)
    }
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
fun PlotContext.ribbon(block: RibbonContext.() -> Unit) {
    layers.add(RibbonContext(data).apply { copyFrom(this@ribbon) }.apply(block).toLayer(RIBBON))
}
