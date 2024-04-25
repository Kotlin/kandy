/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers


import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.ABLineBuilder

/**
 * Adds an `abLine` layer to the plot.
 *
 * The `abLine` layer draws a line defined by its slope and y-intercept,
 * which is commonly used for regression lines or simple references in plots.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 *
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 * For positional aesthetics, you can use the `.constant()` method.
 *
 * ## ABLine Aesthetics
 * * `slope` - Slope of the line.
 * * `intercept` - Y-intercept at which the line crosses the vertical axis.
 * * `color` - Color of the line.
 * * `type` - Style of the line, such as dashed or dotted.
 * * `width` - Width of the line.
 * * `alpha` - Transparency of the line.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     abLine {
 *         // Map values to intercept aesthetic
 *         intercept(listOf(.1, .2, .3, .4, .5))
 *         // Set a constant slope
 *         slope.constant(0.5)
 *         // Set a constant width for the line
 *         width = 2.5
 *         // Map categorical values to color aesthetic
 *         color(listOf("A", "A", "B", "B", "C")) {
 *             // Additional mapping parameters, e.g., you can specify a color palette here
 *             scale = categorical("A" to Color.RED, "B" to Color.PURPLE, "C" to Color.BLUE)
 *         }
 *         // Set alpha (transparency) of the line
 *         alpha = 0.7
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.abLine(block: ABLineBuilder.() -> Unit) {
    createLayer(ABLineBuilder(this), block)
}
