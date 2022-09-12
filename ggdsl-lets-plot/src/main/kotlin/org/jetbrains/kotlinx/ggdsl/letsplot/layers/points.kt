/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val POINT = LetsPlotGeom("point")

// TODO add size unit???
@PlotDslMarker
class PointsContext(override var data: MutableNamedData) : LayerContext() {
    val x = XAes(this)
    val y = YAes(this)

    val symbol = ShapeAes(this)

    val size = SizeAes(this)
    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    // FILL SHAPES only
    val borderWidth = StrokeAes(this) // TODO doesnt work lol
    val fillColor = FillAes(this)

}

/**
 * Adds a new point layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * point {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointsContext.x]
 *  - [y][PointsContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][PointsContext.color] - color of the point (color of the point border for "FILLED" symbols), of the type [Color], mappable
 *  - [alpha][PointsContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [symbol][PointsContext.symbol] - symbol of the point, of the type [Symbol], mappable
 *  - [size][PointsContext.size] - this point size, of the type [Double], mappable
 *  - [fillColor][PointsContext.fillColor] - color of the point filling (for "FILLED" symbols), of the type [Color], mappable
 *  - [borderWidth][PointsContext.borderWidth] - width of the point border (for "FILLED" symbols), of the type [Double], mappable
 *
 * // TODO move data overriding to args
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][PointsContext.data].
 *
 *  // TODO refer to bindings?
 */
// todo rename to point/scatter?
inline fun PlotContext.points(block: PointsContext.() -> Unit) {
    layers.add(PointsContext(data).apply { copyFrom(this@points) }
        .apply(block).toLayer(POINT))
}

