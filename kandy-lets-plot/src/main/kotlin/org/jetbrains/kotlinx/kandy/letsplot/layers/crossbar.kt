/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.CrossBarContext


// todo rename to cross bars
/**
 * Adds a new `crossBar` layer to the plot.
 *
 * The `crossBar` layer displays the distribution of a dataset by marking the minimum, median, and maximum values.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## CrossBar Aesthetics
 * * **`x`** - The X-coordinate specifying the categories.
 * * **`yMin`** - The minimum value for the Y-coordinate.
 * * **`middle`** - The median value for the Y-coordinate.
 * * **`yMax`** - The maximum value for the Y-coordinate.
 * * **`fillColor`** - The fill color of the crossbars.
 * * **`alpha`** - The transparency of the crossbars.
 * * **`width`** - The width of the crossbars.
 * * **`fatten`** - The factor by which to "fatten" the width of the bars.
 * * **`borderLine.color`** - Color of the crossbars' borderline.
 * * **`borderLine.width`** - Width of the crossbars' borderline.
 * * **`borderLine.type`** - Type of the crossbars' borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     crossBar {
 *         // Positional mapping
 *         x(listOf("A", "B", "C", "D"))
 *         yMin(listOf(10.0, 15.0, 5.0, 20.0))
 *         middle(listOf(25.0, 30.0, 22.0, 35.0))
 *         yMax(listOf(40.0, 35.0, 30.0, 50.0))
 *
 *         // Adjust the Y-axis
 *         y.limits = 0.0..55.0
 *
 *         // Non-positional settings
 *         fatten = 1.2
 *         width = 0.6
 *
 *         // BorderLine settings
 *         borderLine {
 *             color = Color.BLACK
 *             width = 1.5
 *             type = LineType.SOLID
 *         }
 *
 *         // Non-positional mapping
 *         fillColor(listOf("red", "green", "blue", "yellow")) {
 *             scale = categorical(
 *                 "red" to Color.RED, "green" to Color.GREEN,
 *                 "blue" to Color.BLUE, "yellow" to Color.YELLOW
 *             )
 *         }
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.crossBar(block: CrossBarContext.() -> Unit) {
    addLayer(CrossBarContext(this).apply(block))
}