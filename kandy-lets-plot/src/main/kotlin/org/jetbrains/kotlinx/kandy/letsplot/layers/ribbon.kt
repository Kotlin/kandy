/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.RibbonBuilder

/**
 * Adds a new `ribbon` layer to the plot.
 *
 * The `ribbon` layer is used to display a shaded region between two lines,
 * often used to represent confidence intervals, ranges, or other types of uncertainties.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Ribbon Aesthetics
 * * **`x`** - The X-coordinate specifying the line along which the ribbon is drawn.
 * * **`yMin`** - The minimum Y-coordinate specifying the lower bound of the ribbon.
 * * **`yMax`** - The maximum Y-coordinate specifying the upper bound of the ribbon.
 * * **`alpha`** - The transparency of the ribbon.
 * * **`fillColor`** - The fill color of the ribbon.
 * * **`borderLine.color`** - Color of the ribbon's borderline.
 * * **`borderLine.width`** - Width of the ribbon's borderline.
 * * **`borderLine.type`** - Type of the ribbon's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 * val year by columnOf(2000, 2001, 2002, 2003, 2004)
 * val level by columnOf(2.0, 3.5, 6.0, 4.3, 5.1)
 *
 * plot {
 *     ribbon {
 *         // Map 'year' for x-axis, which specifies the line along which the ribbon is drawn
 *         x(year) {
 *             scale = continuous(1999..2005)
 *         }
 *
 *         // Map `level -1` and `level + 1` for the y-axis to specify the bounds of the ribbon
 *         yMin(level.map { it - 1 })
 *         yMax(level.map { it + 1 })
 *
 *         // Set the fill color
 *         // fillColor("category") {
 *         // Here, you can set the scale and legend parameters as needed
 *         // }
 *
 *         // Set the transparency of the ribbon
 *         alpha = 0.5
 *
 *         // Set the properties for the border of the ribbon
 *         borderLine {
 *             color = Color.RED
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.ribbon(block: RibbonBuilder.() -> Unit) {
    createLayer(RibbonBuilder(this), block)
}
