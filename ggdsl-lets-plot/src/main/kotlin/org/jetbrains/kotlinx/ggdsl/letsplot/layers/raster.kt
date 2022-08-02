package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

val RASTER = LetsPlotGeom("raster")



class RasterContext(override var data: MutableNamedData) :
    LayerContext() {

    val color = FillAes(this)
    val alpha = AlphaAes(this)

}

/**
 * Adds a new raster layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * raster {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][RasterContext.x]
 *  - [y][RasterContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][RasterContext.color] - color of the raster filling, of the type [Color], mappable
 *  - [alpha][RasterContext.alpha] - this layer alpha, of the type [Double], mappable
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][RasterContext.data].
 *
 * // TODO move data overriding to args
 *  // TODO refer to bindings?
 */
fun PlotContext.raster(block: RasterContext.() -> Unit) {
    layers.add(RasterContext(data).apply { copyFrom(this@raster) }.apply(block).toLayer(RASTER))
}
