/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.PointContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.PointContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.PointMutableMutableContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color

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
 *  - [ x][PointContextInterface.x]
 *  - [y][PointContextInterface.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [LayerPlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][PointContextInterface.color] - color of the point (color of the point border for "FILLED" symbols),
 *  of the type [Color], mappable
 *  - [alpha][PointContextInterface.alpha] - this layer alpha, of the type [Double], mappable
 *  - [symbol][PointContextInterface.symbol] - symbol of the point, of the type [Symbol], mappable
 *  - [size][PointContextInterface.size] - this point size, of the type [Double], mappable
 *  - [fillColor][PointContextInterface.fillColor] - color of the point filling (for "FILLED" symbols),
 *  of the type [Color], mappable
 *  - [borderWidth][PointContextInterface.borderWidth] - width of the point border (for "FILLED" symbols),
 *  of the type [Double], mappable without an explicit scale.
 *
 * ```
 * point {
 *    x(time) // mapping from `time` column to `X` with default scale.
 *    color(Color.BLUE) // setting of constant `color` value
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.point(block: PointContextImmutable.() -> Unit) {
    addLayer(PointContextImmutable(this).apply(block), POINT)
}

/**
 * Adds a new point layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointContextInterface.x]
 *  - [y][PointContextInterface.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [LayerPlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][PointContextInterface.color] - color of the point (color of the point border for "FILLED" symbols),
 *  of the type [Color], mappable
 *  - [alpha][PointContextInterface.alpha] - this layer alpha, of the type [Double], mappable
 *  - [symbol][PointContextInterface.symbol] - symbol of the point, of the type [Symbol], mappable
 *  - [size][PointContextInterface.size] - this point size, of the type [Double], mappable
 *  - [fillColor][PointContextInterface.fillColor] - color of the point filling (for "FILLED" symbols),
 *  of the type [Color], mappable
 *  - [borderWidth][PointContextInterface.borderWidth] - width of the point border (for "FILLED" symbols),
 *  of the type [Double], mappable without an explicit scale.
 *
 * ```
 * point {
 *    x(listOf(3.4, 2.2, 10.0)) // mapping from list to `X` with default scale.
 *    color(Color.BLUE) // setting of constant `color` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.point(block: PointMutableMutableContextMutable.() -> Unit) {
    addLayer(PointMutableMutableContextMutable(this).apply(block), POINT)
}
