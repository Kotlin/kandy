package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color

val AREA = LetsPlotGeom("area")


class AreaContext(override var data: MutableNamedData) :
    WithBorderLineContext() {
    val color = FillAes(this)
    val alpha = AlphaAes(this)
}

/**
 * Adds a new area layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * area {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    borderLine.color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][AreaContext.x]
 *  - [y][AreaContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][AreaContext.color] - color of the area filling, of the type [Color], mappable.
 *  - [alpha][AreaContext.alpha] - layer alpha, of the type [Double], mappable.
 *  - [border.color][BorderLineSubContext.color] - color of , of the type [Color], non-mappable.
 *  - [border.width][BorderLineSubContext.width] - this area border width, of the type [Double], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][AreaContext.data].
 *
 *  @see [BaseBindingContext]
 */
fun PlotContext.area(block: AreaContext.() -> Unit) {
    layers.add(AreaContext(data).apply { copyFrom(this@area) }.apply(block).toLayer(AREA))
}
