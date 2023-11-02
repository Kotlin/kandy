/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.PathContext

/**
 * Adds a new `path` layer to the plot.
 *
 * The `path` layer connects data points in the order they appear in the data frame,
 * unlike `line` which sorts the points before connecting them.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Path Aesthetics
 * * **`x`** - The X-coordinate specifying the position.
 * * **`y`** - The Y-coordinate specifying the position.
 * * **`color`** - The color of the path.
 * * **`type`** - The type of the line, such as dashed or dotted.
 * * **`width`** - The width of the path.
 * * **`alpha`** - The transparency of the path.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     path {
 *         // Positional mapping
 *         // Here we would have our mapping parameters to define the scale and possibly transformations
 *         x(listOf("01:00", "02:00", "03:00", "04:00", "05:00"))
 *         // Similarly, mapping parameters for the Y-axis
 *         y(listOf(14, 16, 15, 17, 18))
 *
 *         // Non-positional settings
 *         width = 2.0 // Sets the width of the path
 *
 *         // Non-positional mapping
 *         // color("type") {
 *         //      Inside this block, we would define how "type" is mapped to color,
 *         //      e.g., providing a color scale if "type" is a categorical variable
 *         // }
 *
 *         // Here, we specify the line type and alpha if needed
 *         type = LineType.DOTDASH // Example setting, if your DSL allows direct assignments like this
 *         alpha = 0.6 // Setting the transparency of the path
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.path(block: PathContext.() -> Unit) {
    addLayer(PathContext(this).apply(block))
}
