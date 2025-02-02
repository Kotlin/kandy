/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.ErrorBarsBuilder


/**
 * Adds a new `errorBars` layer to the plot.
 *
 * The `errorBar` layer represents the uncertainty around a measurement
 * by displaying vertical lines above and below the point estimate.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## ErrorBars Aesthetics
 * * **`x`** - The X-coordinate specifying the categories.
 * * **`yMin`** - The minimum value for the Y-coordinate, representing the lower end of the error bar.
 * * **`yMax`** - The maximum value for the Y-coordinate, representing the upper end of the error bar.
 * * **`alpha`** - The transparency of the error bars.
 * * **`width`** - The width of the error bars.
 * * **`borderLine.color`** - The color of the error bars' borderline.
 * * **`borderLine.width`** - The width of the error bars' borderline.
 * * **`borderLine.type`** - The type of the error bars' borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     errorBars {
 *         // Positional mapping
 *         x(listOf("Group A", "Group B", "Group C", "Group D"))
 *         yMin(listOf(48.0, 33.0, 20.0, 22.0))
 *         yMax(listOf(75.0, 88.0, 60.0, 68.0))
 *
 *         // Adjust the Y-axis
 *         y.axis.limits = 0.0..100.0
 *
 *         // Non-positional settings
 *         alpha = 0.8
 *
 *         // BorderLine settings
 *         borderLine {
 *             color(listOf("A", "B", "A", "B"))
 *             width = 1.0
 *             type = LineType.SOLID
 *         }
 *         // Non-positional mapping
 *         width = 0.2
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.errorBars(block: ErrorBarsBuilder.() -> Unit) {
    createLayer(ErrorBarsBuilder(this).apply {
        position = Position.dodge()
    }, block)
}
