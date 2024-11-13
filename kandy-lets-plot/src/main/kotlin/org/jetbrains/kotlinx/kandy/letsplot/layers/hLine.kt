/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.HLineBuilder


/**
 * Adds a new `hLine` layer to the plot.
 *
 * The `hLine` layer adds a horizontal line to a plot, which can serve as a reference line or highlight specific data ranges.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## HLine Aesthetics
 * * **`y`** - The Y-coordinate where the horizontal line should appear.
 * * **`color`** - The color of the line.
 * * **`type`** - The type of the line, such as solid, dashed, or dotted.
 * * **`width`** - The width of the line.
 * * **`alpha`** - The transparency of the line.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     hLine {
 *         // Set the Y-intercept of the horizontal line
 *         yIntercept.constant(50.0)
 *
 *         // Optionally, set the color of the line
 *         color = Color.RED
 *
 *         // Set the type or style of the line, such as solid, dashed, or dotted
 *         type = LineType.DASHED
 *
 *         // Set the width of the line
 *         width = 2.0
 *
 *         // Set the transparency of the line
 *         alpha = 0.5
 *
 *         // Even though hLine does not have an "x" attribute, you can still adjust the X-axis limits if needed
 *         x.axis.limits = 0.0..100.0
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.hLine(block: HLineBuilder.() -> Unit) {
    createLayer(HLineBuilder(this), block)
}
