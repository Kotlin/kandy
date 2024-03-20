/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.PointsContext

/**
 * Adds a new `points` layer to the plot.
 *
 * The `points` layer represents observations in your data through individual points in a Cartesian coordinate system.
 *
 * This function provides a context where you can define aesthetic mappings (`aes`) and aesthetic constants for the layer.
 * - Mappings are specified by calling methods that have names corresponding to aesthetic names (`aes`).
 * - Constants are set directly using properties with names that correspond to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Points Aesthetics
 * * **`x`** - The x-coordinate of the point.
 * * **`y`** - The y-coordinate of the point.
 * * **`color`** - The color of the point.
 * * **`symbol`** - The symbol used to represent the point.
 * * **`size`** - The size of the point.
 * * **`alpha`** - The transparency of the point.
 * * **`fillColor`** - The fill color for symbols that have a fill.
 * * **`stroke`** - width of the shape border. Applied only to the shapes having border.
 *
 * ## Example
 *
 * ```kotlin
 * val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
 * val sales = listOf(10000, 15000, 18000, 25000, 22000, 20000)
 * val customerCounts = listOf(80, 120, 150, 200, 180, 160)
 *
 * plot {
 *     points {
 *         // Positional mapping
 *         x(months) // Categories on the x-axis
 *         y(sales) // Numerical values on the y-axis
 *
 *         // Non-positional settings
 *         color = Color.BLUE // Set a constant color for the points
 *         alpha = 0.7 // Set a constant transparency for the points
 *         fillColor = Color.GREEN // Set a fill color for the points (for filled symbols)
 *         stroke = 3
 *
 *         // Map 'customerCounts' to 'size' to represent the number of customers as the size of the point
 *         size(customerCounts) {
 *             // Additional mapping parameters if necessary
 *             // For example, you might want to normalize or scale the sizes
 *         }
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.points(block: PointsContext.() -> Unit) {
    addLayer(PointsContext(this).apply(block))
}
