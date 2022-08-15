package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val STEP = LetsPlotGeom("step")


@PlotDslMarker
class StepContext(override var data: MutableNamedData) :
    LayerContext() {
    val x = XAes(this)
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val lineType = LineTypeAes(this)
    val width = SizeAes(this)

}

/**
 * Adds a new step layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * step {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][StepContext.x]
 *  - [y][StepContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][StepContext.color] - this line color, of the type [Color], mappable
 *  - [alpha][StepContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [lineType][StepContext.lineType] - this line type, of the type [LineType], mappable
 *  - [width][StepContext.width] - this line width, of the type [Double], mappable
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][StepContext.data].
 *
 * // TODO move data overriding to args

 *  // TODO refer to bindings?
 */
inline fun PlotContext.step(block: StepContext.() -> Unit) {
    layers.add(StepContext(data).apply { copyFrom(this@step) }.apply(block).toLayer(STEP))
}
