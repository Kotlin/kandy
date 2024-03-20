/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.RasterContext

/**
 * Adds a new `raster` layer to the plot.
 *
 * The `raster` layer displays data as colored or shaded rectangles with respect to an x and y coordinate,
 * like an image or heat map.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Raster Aesthetics
 * * **`x`** - The X-coordinate specifying the position of the rectangles.
 * * **`y`** - The Y-coordinate specifying the position of the rectangles.
 * * **`fillColor`** - The fill color of the rectangles.
 * * **`alpha`** - The transparency of the rectangles.
 * * **`borderLine.color`** - Color of the rectangles' borderline.
 * * **`borderLine.width`** - Width of the rectangles' borderline.
 * * **`borderLine.type`** - Type of the rectangles' borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 * // Assume we have data on average monthly temperatures in various cities.
 * val data = dataFrameOf(
 *     "month" to List(4) { listOf("January", "February", "March", "April", "May") }.flatten(),
 *     "city" to List(5) { listOf("London", "Berlin", "Saint-Petersburg", "Yerevan") }.flatten(),
 *     "temp" to listOf(-5, -3, 0, 5, 10, -7, -5, -1, 4, 9, -10, -8, -5, 2, 7, -8, -6, -3, 3, 8)
 * )
 *
 * // Now, let's create a heatmap based on this data.
 * data.plot {
 *     raster {
 *         // Map temperatures to the x and y axes
 *         x("month")
 *         y("city")
 *
 *         // Set the colors based on temperature
 *         fillColor("temp") {
 *             // Here, you can set scale parameters so the colors correspond to temperatures
 *         }
 *
 *         // Transparency and border parameters for each rectangle
 *         alpha = 0.8
 *         borderLine {
 *             color = Color.BLACK
 *             width = 0.5
 *         }
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.raster(block: RasterContext.() -> Unit) {
    addLayer(RasterContext(this).apply(block))
}