/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.RectanglesBuilder

/**
 * Adds a new `rectangles` layer to the plot.
 *
 * The `rectangles` layer is used for drawing rectangles on the plot, often used to mark areas or ranges.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Rectangle Aesthetics
 * * **`xMin`** - The minimum X-coordinate specifying one corner of the rectangle.
 * * **`xMax`** - The maximum X-coordinate specifying the opposite corner of the rectangle.
 * * **`yMin`** - The minimum Y-coordinate specifying one corner of the rectangle.
 * * **`yMax`** - The maximum Y-coordinate specifying the opposite corner of the rectangle.
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 * val startTime by columnOf(1, 2, 3)
 * val endTime by columnOf(2, 3.5, 4)
 * val startCnt by columnOf(5.1, 2.3, 3.7)
 * val endCnt by columnOf(5.3, 2.4, 4.3)
 * val event by columnOf("Event A", "Event B", "Event C")
 *
 * // Now, let's visualize the duration of each event as a rectangle.
 * plot {
 *     rectangles {
 *         // Map the start and end times to the X-axis to create the rectangles
 *         xMin(startTime)
 *         xMax(endTime)
 *         // Map the start and end counts to the Y-axis
 *         yMin(startCnt)
 *         yMax(endCnt)
 *
 *         // Set the fill color based on the event
 *         fillColor(event) {
 *             // Additional scale parameters can be set here if needed
 *         }
 *
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.rectangles(block: RectanglesBuilder.() -> Unit) {
    createLayer(RectanglesBuilder(this), block)
}