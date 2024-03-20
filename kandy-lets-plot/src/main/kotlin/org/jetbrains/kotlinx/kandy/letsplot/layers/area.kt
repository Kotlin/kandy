/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.AreaContext

/**
 * Adds a new `area` layer to the plot.
 *
 * The `area` layer is designed to visualize an area under a curve in a Cartesian coordinate system.
 * The layer fills the area between the X-axis and the curve defined by the mapped values of X and Y.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 *
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 * For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Area Aesthetics
 * * `x` - The X-coordinate of the area.
 * * `y` - The Y-coordinate of the area.
 * * `fillColor` - The fill color of the area.
 * * `alpha` - The transparency of the area.
 * * `borderLine.color` - Color of the area's borderline.
 * * `borderLine.width` - Width of the area's borderline.
 * * `borderLine.type` - Type of the area's borderline.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     area {
 *         // Positional mapping
 *         x(listOf("January", "February", "March", "April", "May")) {
 *             axis.name = "months"
 *         }
 *         y(listOf(200, 150, 300, 250, 420)) {
 *             axis.name = "sales"
 *             scale = continuous(min = 100, max = 500)
 *         }
 *
 *         // Non-positional settings
 *         alpha = 0.5
 *
 *         // Non-positional mapping with constant fillColor
 *         fillColor = Color.BLUE
 *
 *         // BorderLine settings
 *         borderLine {
 *             color = Color.BLACK
 *             width = 1.5
 *         }
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.area(block: AreaContext.() -> Unit) {
    addLayer(AreaContext(this).apply(block))
}
