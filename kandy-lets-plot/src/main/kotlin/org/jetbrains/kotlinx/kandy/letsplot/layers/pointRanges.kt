/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.PointRangesContext


/**
 * Adds a new `pointRanges` layer to the plot.
 *
 * The `pointRanges` layer represents observations as points with a vertical or horizontal range line,
 * allowing you to visualize uncertainties or variations in your data.
 *
 * This function provides a context where you can define aesthetic mappings
 * (`aes`) and aesthetic constants for the layer.
 * - Mappings are specified by calling methods that have names corresponding to aesthetic names (`aes`).
 * - Constants are set directly using properties with names that correspond to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## PointRanges Aesthetics
 * * **`x`** - The x-coordinate of the point.
 * * **`y`** - The y-coordinate of the point.
 * * **`yMin`** - The minimum y-value of the range line.
 * * **`yMax`** - The maximum y-value of the range line.
 * * **`alpha`** - The transparency of the point and range line.
 * * **`color`** - The color of the point.
 * * **`size`** - The size of the point.
 * * **`fatten`** - The amount to fatten the point relative to the line.
 * * **`innerLine.type`** - The type of line for the range.
 * * **`innerPoint.symbol`** - The symbol used for the point.
 * * **`innerPoint.fillColor`** - The fill color of the symbol.
 * * **`innerPoint.symbol`** - The symbol used for the point.
 *
 * ## Example
 *
 * ```kotlin
 * val months by columnOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
 * val avgTemperatures by columnOf(5.0, 7.0, 10.0, 15.0, 20.0, 25.0)
 * val minTemperatures by columnOf(-1.0, 1.0, 3.0, 7.0, 13.0, 18.0)
 * val maxTemperatures by columnOf(10.0, 12.0, 16.0, 20.0, 26.0, 30.0)
 *
 * plot {
 *     pointRanges {
 *         // Positional mapping for the x-axis using months
 *         x(months)
 *
 *         // Positional settings for the y-axis using average temperatures
 *         y(avgTemperatures)
 *
 *         // Mapping the min and max temperatures for the range
 *         yMin(minTemperatures)
 *         yMax(maxTemperatures)
 *
 *         // Non-positional settings
 *         alpha = 0.6 // Transparency for the point and range line
 *         size = 5.0 // Size of the point
 *
 *         // Customizing the appearance of the inner line and point
 *         innerLine {
 *             type = LineType.LONGDASH // Setting the type of the line for the range
 *         }
 *
 *         innerPoint {
 *             symbol = Symbol.DIAMOND_FILLED // The symbol used for the point
 *             fillColor = Color.RED // The fill color of the symbol
 *         }
 *
 *         color = Color.BLUE
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.pointRanges(block: PointRangesContext.() -> Unit) {
    addLayer(PointRangesContext(this).apply {
        position = Position.dodge()
    }.apply(block))
}
