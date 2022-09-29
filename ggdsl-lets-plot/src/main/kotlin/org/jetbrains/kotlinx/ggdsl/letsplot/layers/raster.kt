package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val RASTER = LetsPlotGeom("raster")


@PlotDslMarker
class RasterContext(parent: LayerCollectorContext) :
    LayerContext(parent) {
    val x = XAes(this)
    val y = YAes(this)

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
 */
inline fun LayerCollectorContext.raster(block: RasterContext.() -> Unit) {
    addLayer(RasterContext(this).apply(block), RASTER)
}
