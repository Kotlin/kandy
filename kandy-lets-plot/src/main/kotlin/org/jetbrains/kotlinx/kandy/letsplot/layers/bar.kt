/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.feature.reversed
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BarsContext

/**
 * Adds a new `bars` layer to the plot.
 *
 * The `bars` layer is used to create bar charts, which are useful for comparing quantities among discrete categories.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Bars Aesthetics
 * * **`x`** - The X-coordinate specifying the categories.
 * * **`y`** - The Y-coordinate specifying the height of the bars.
 * * **`fillColor`** - The fill color of the bars.
 * * **`alpha`** - The transparency of the bars.
 * * **`width`** - The width of the bars.
 * * **`borderLine.color`** - Color of the bars' borderline.
 * * **`borderLine.width`** - Width of the bars' borderline.
 * * **`borderLine.type`** - Type of the bars' borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 * plot {
 *     bars {
 *         // Positional mapping
 *         x(listOf("Apple", "Banana", "Cherry", "Orange", "Strawberry"))
 *         y(listOf(5.0, 7.5, 3.0, 4.5, 6.0)) {
 *             axis.breaks((0..16).map { it / 2.0 })
 *         }
 *
 *         // Non-positional settings
 *         alpha = 0.7
 *         width = 0.4
 *
 *         // BorderLine settings
 *         borderLine {
 *             color = Color.BLACK
 *             width = 2.5
 *             type = LineType.DASHED
 *         }
 *
 *         // Non-positional mapping
 *         fillColor(listOf("a", "b", "b", "c", "a"))
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.bars(block: BarsContext.() -> Unit) {
    addLayer(BarsContext(this).apply {
        position = Position.dodge()
    }.apply(block))
}

/**
 * Adds a new `barsH` layer to the plot.
 *
 * The `barsH` layer is designed to visualize data using horizontal bars.
 * It serves a similar purpose as `bars`, but the orientation of the bars is horizontal rather than vertical.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 * For positional aesthetics, you can use the `.constant()` method.
 *
 * ## BarsH Aesthetics
 * * **`x`** - The X-coordinate specifying the length of the bars.
 * * **`y`** - The Y-coordinate specifying the categories.
 * * **`fillColor`** - The fill color of the bars.
 * * **`alpha`** - The transparency of the bars.
 * * **`width`** - The width of the bars.
 * * **`borderLine.color`** - Color of the bars' borderline.
 * * **`borderLine.width`** - Width of the bars' borderline.
 * * **`borderLine.type`** - Type of the bars' borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     barsH {
 *         // Positional mapping
 *         y(listOf("Apple", "Banana", "Cherry", "Orange", "Strawberry"))
 *         x(listOf(5.0, 7.5, 3.0, 4.5, 6.0)) {
 *             axis.breaks((0..16).map { it / 2.0 })
 *         }
 *
 *         // Non-positional settings
 *         alpha = 0.7
 *         width = 0.4
 *
 *         // BorderLine settings
 *         borderLine {
 *             color = Color.BLACK
 *             width = 2.5
 *             type = LineType.DASHED
 *         }
 *
 *         // Non-positional mapping
 *         fillColor(listOf("a", "b", "b", "c", "a"))
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.barsH(block: BarsContext.() -> Unit) {
    addLayer(BarsContext(this).apply {
        reversed = true
    }.apply(block))
}
