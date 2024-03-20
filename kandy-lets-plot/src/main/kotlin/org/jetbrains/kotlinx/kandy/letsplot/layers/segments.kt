/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.SegmentsContext


/**
 * Adds a new `segments` layer to the plot.
 *
 * The `segments` layer is used to draw line segments between two points,
 * and is useful for visualizing connections, relationships, or flow between elements in a plot.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Segments Aesthetics
 * * **`xBegin`** - The X-coordinate specifying the beginning point of the segment.
 * * **`xEnd`** - The X-coordinate specifying the ending point of the segment.
 * * **`yBegin`** - The Y-coordinate specifying the beginning point of the segment.
 * * **`yEnd`** - The Y-coordinate specifying the ending point of the segment.
 * * **`color`** - The color of the segment line.
 * * **`alpha`** - The transparency of the segment line.
 * * **`width`** - The width of the segment line.
 * * **`lineType`** - The type of the segment line, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 * val xS by columnOf(1, 2, 3, 4, 5)
 * val xE by columnOf(4, 5, 6, 7, 8)
 * val yS by columnOf(10, 20, 30, 40, 50)
 * val yE by columnOf(50, 40, 30, 20, 10)
 * val city by columnOf("CityA", "CityB", "CityC", "CityD", "CityE")
 *
 * plot {
 *     segments {
 *         // Positional mapping
 *         xBegin(xS)
 *         xEnd(xE)
 *         yBegin(yS)
 *         yEnd(yE)
 *
 *         // Non-positional mapping
 *         color(city)
 *
 *         // Non-positional settings
 *         width = 2.5
 *         alpha = 0.8
 *         lineType = LineType.DASHED
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.segments(block: SegmentsContext.() -> Unit) {
    addLayer(SegmentsContext(this).apply(block))
}
