package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

// TODO

@PublishedApi
internal val LINE = LetsPlotGeom("line")
@PublishedApi
internal val PATH = LetsPlotGeom("path")


@PlotDslMarker
class LineContext(parent: LayerCollectorContext) : LayerContext(parent) {
    val x = XAes(this)
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val type = LineTypeAes(this)
    val width = SizeAes(this)
}

/**
 * Adds a new line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * line {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineContext.x]
 *  - [y][LineContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][LineContext.color] - this line color, of the type [Color], mappable
 *  - [alpha][LineContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [type][LineContext.type] - this line type, of the type [LineType], mappable
 *  - [width][LineContext.width] - this line width, of the type [Double], mappable
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][LineContext.data].
 *
 * // TODO move data overriding to args

 *  // TODO refer to bindings?
 */
inline fun LayerCollectorContext.line(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), LINE)
}

inline fun LayerCollectorContext.path(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), PATH)
}
