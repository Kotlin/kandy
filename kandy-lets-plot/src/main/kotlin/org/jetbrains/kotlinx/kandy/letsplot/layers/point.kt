/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.PointsContext
import org.jetbrains.kotlinx.kandy.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.kandy.util.color.Color

@PublishedApi
internal val POINT: LetsPlotGeom = LetsPlotGeom("point")

/**
 * Adds a new point layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointsContext.x]
 *  - [y][PointsContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [LayerPlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][PointsContext.color] - color of the point (color of the point border for "FILLED" symbols),
 *  of the type [Color], mappable
 *  - [alpha][PointsContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [symbol][PointsContext.symbol] - symbol of the point, of the type [Symbol], mappable
 *  - [size][PointsContext.size] - this point size, of the type [Double], mappable
 *  - [fillColor][PointsContext.fillColor] - color of the point filling (for "FILLED" symbols),
 *  of the type [Color], mappable
 *  - [borderWidth][PointsContext.borderWidth] - width of the point border (for "FILLED" symbols),
 *  of the type [Double], mappable without an explicit scale.
 *
 * ```
 * point {
 *    x(time) // mapping from `time` column to `X` with default scale.
 *    color(Color.BLUE) // setting of constant `color` value
 * }
 * ```
 */
public inline fun LayerCollectorContext.points(block: PointsContext.() -> Unit) {
    addLayer(PointsContext(this).apply(block), POINT)
}

