/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PolygonsBuilder

/**
 * Adds a new `polygons` layer to the plot.
 *
 * The `polygons` layer is responsible for constructing a polygons by points.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Polygons Aesthetics
 * * **`x`** - The X-coordinate of polygons points.
 * * **`y`** - The Y-coordinate of polygons points.
 * * **`fillColor`** - The fill color of the polygons.
 * * **`alpha`** - The transparency of the polygons.
 * * **`borderLine.color`** - Color of the polygons borderline.
 * * **`borderLine.width`** - Width of the polygons borderline.
 * * **`borderLine.type`** - Type of the polygons borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     polygons {
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
public inline fun LayerCreatorScope.polygons(block: PolygonsBuilder.() -> Unit) {
    createLayer(PolygonsBuilder(this), block)
}
