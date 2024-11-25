/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PolygonBuilder

/**
 * Adds a new `polygon` layer to the plot.
 *
 * The `polygon` layer is responsible for constructing a polygon by points.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Polygon Aesthetics
 * * **`x`** - The X-coordinate of polygon points.
 * * **`y`** - The Y-coordinate of polygon points.
 * * **`fillColor`** - The fill color of the polygon.
 * * **`alpha`** - The transparency of the polygon.
 * * **`borderLine.color`** - Color of the polygon borderline.
 * * **`borderLine.width`** - Width of the polygon borderline.
 * * **`borderLine.type`** - Type of the polygon borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     polygon {
 *         // Positional mapping
 *         x(listOf(1.0, 2.0, 3.0))
 *         y(listOf(1.0, 2.0, 1.0))
 *
 *         // Adjust the Y-axis
 *         y.limits = 0.0..60.0
 *
 *         // Non-positional settings
 *         alpha = 0.8
 *
 *         // BorderLine settings
 *         borderLine.width = .5
 *
 *         // Non-positional mapping
 *         fillColor = Color.BLUE
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.polygon(block: PolygonBuilder.() -> Unit) {
    createLayer(PolygonBuilder(this), block)
}
