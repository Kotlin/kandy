/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PieBuilder

/**
 * Adds a new `pie` layer to the plot.
 *
 * The `pie` layer represents categorical data through the angle of pie slices.
 * The size of each slice is proportional to the value it represents.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Pie Aesthetics
 * * **`x`** - The X-coordinate specifying the center of the pie.
 * * **`y`** - The Y-coordinate specifying the center of the pie.
 * * **`fillColor`** - The color of each pie slice.
 * * **`slice`** - The value each slice represents.
 * * **`hole`** - The radius of the inner hole, creating a donut chart when set.
 * * **`explode`** - The distance to offset slices from the center.
 * * **`size`** - The size of the pie chart.
 * * **`alpha`** - The transparency of the pie slices.
 * * **`stroke`** - The stroke width around each slice.
 * * **`strokeColor`** - The color of the stroke around each slice.
 *
 * ## Example
 *
 * ```kotlin
 * val categories by columnOf("Rent", "Food", "Utilities", "Transportation", "Entertainment")
 * val amounts by columnOf(500.0, 300.0, 150.0, 100.0, 50.0)
 *
 * plot {
 *     pie {
 *         // Positional settings for the center of the pie chart
 *         x.constant(0.5) // Assuming this is the normalized center on the X-axis
 *         y.constant(0.5) // Assuming this is the normalized center on the Y-axis
 *
 *         slice(amounts) // The values that each slice represents
 *
 *         // Non-positional setting for the size of the pie chart
 *         size = 30.0 // Assuming this is a normalized size relative to the plot dimensions
 *
 *         // Non-positional mapping for the fill color based on the category
 *         fillColor(categories) {
 *             // Inside this block, you would define the mapping parameters or color scale
 *             // e.g., you might want to map each category to a specific color
 *         }
 *
 *         // Non-positional settings for aesthetics like alpha, stroke, and stroke color
 *         alpha = 0.8 // Sets the transparency of the pie slices
 *         stroke = 1.0 // Sets the stroke width around each slice
 *         strokeColor = Color.BLACK // Sets the color of the stroke around each slice
 *         spacerWidth = 0.5 // Sets  Line width between sectors.
 *         // Spacers are not applied to exploded sectors and to sides of adjacent sectors
 *         spacerColor = Color.RED // Sets the color for spacers between sectors
 *
 *         // If you want to create a donut chart, set the hole radius
 *         hole = 0.2 // Assuming this creates a hole with radius equal to 20% of the pie chart radius
 *
 *         // If you want to "explode" or offset a slice from the center, specify the explode setting
 *         explode(listOf(.0, .0, 0.1, .0, .0)) // This would offset the "Utilities" slice by 10% of the pie radius
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.pie(block: PieBuilder.() -> Unit) {
    createLayer(PieBuilder(this), block)
}
