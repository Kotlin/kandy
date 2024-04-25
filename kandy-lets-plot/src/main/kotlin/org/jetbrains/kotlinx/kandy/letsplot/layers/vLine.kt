/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.VLineBuilder

/**
 * Adds a new vertical line (`vLine`) layer to the plot.
 *
 * The `vLine` layer is primarily used for adding vertical lines that serve as a reference line
 * or to highlight specific values on the X-axis.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Vertical Line Aesthetics
 * * **`x`** - The X-coordinate where the line is placed.
 * * **`color`** - The color of the line.
 * * **`type`** - The type of line (solid, dashed, etc.).
 * * **`width`** - The width of the line.
 * * **`alpha`** - The transparency of the line.
 *
 * ## Example Usage
 *
 * ```kotlin
 * plot {
 *     vLine {
 *         // Set the X-intercept of the vertical line
 *         xIntercept.constant(10.0)
 *
 *         // Optionally, set the color of the line
 *         color = Color.GREEN
 *
 *         // Set the type or style of the line, such as solid, dashed, or dotted
 *         type = LineType.SOLID
 *
 *         // Set the width of the line
 *         width = 3.0
 *
 *         // Set the transparency of the line
 *         alpha = 0.7
 *
 *         // Although vLine does not have a "y" attribute, you can adjust the Y-axis limits if needed
 *         y.limits = 0.0..30.0
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.vLine(block: VLineBuilder.() -> Unit) {
    createLayer(VLineBuilder(this), block)
}
