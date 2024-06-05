/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.LineBuilder

/**
 * Adds a new `line` layer to the plot.
 *
 * The `line` layer plots lines connecting data points, usually to show trends or patterns over time or other continuous variables.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Line Aesthetics
 * * **`x`** - The X-coordinate specifying the categories.
 * * **`y`** - The Y-coordinate specifying the numerical values.
 * * **`color`** - The color of the line.
 * * **`type`** - The type of the line, such as solid, dashed, or dotted.
 * * **`width`** - The width of the line.
 * * **`alpha`** - The transparency of the line.
 *
 * ## Example
 *
 * ```kotlin
 * // or use mapOf<String, List<Any>>(...)
 * val months by columnOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
 * val sales by columnOf(120, 150, 170, 210, 240, 220, 230, 210, 200, 230, 250, 280)
 * val data = dataFrameOf(months, sales)
 *
 * plot(data) {
 *     line {
 *         // Positional mapping for the x-axis using months
 *         x(months)
 *
 *         // Positional mapping for the y-axis using sales data
 *         y(sales) {
 *             scale = continuous(100..300)
 *         }
 *
 *         // Non-positional settings for the line aesthetics
 *         width = 2.5
 *         color = Color.RED
 *         type = LineType.SOLID
 *         alpha = 0.8
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.line(block: LineBuilder.() -> Unit) {
    createLayer(LineBuilder(this), block)
}
