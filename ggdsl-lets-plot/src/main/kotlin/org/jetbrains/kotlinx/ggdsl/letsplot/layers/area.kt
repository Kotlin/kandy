package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color

val AREA = LetsPlotGeom("area")



class AreaContext(override var data: MutableNamedData) :
    LayerContext() {
    val color = FILL
    val alpha = ALPHA

    val borderLine = BorderLineSubContext()

    inline operator fun BorderLineSubContext.invoke(block: BorderLineSubContext.() -> Unit) {
        apply(block)
        this@AreaContext.copyFrom(this, false)
    }
}

/**
 * Adds a new area layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * area {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    borderColor(Color.BLUE) // setting of constant color value
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
 *  - [color][AreaContext.color] - this area fill color, of the type [Color], mappable TODO.
 *  - [alpha][AreaContext.alpha] - this area fill alpha, of the type [Double], mappable TODO.
 *  - [borderColor][AreaContext.borderColor] - this area border color, of the type [Color], non-mappable.
 *  - [borderWidth][AreaContext.borderWidth] - this area border width, of the type [Double], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][AreaContext.data].
 *
 *  @see [BaseBindingContext]
 */
fun PlotContext.area(block: AreaContext.() -> Unit) {
    layers.add(AreaContext(data).apply { copyFrom(this@area) }.apply(block).toLayer(AREA))
}
