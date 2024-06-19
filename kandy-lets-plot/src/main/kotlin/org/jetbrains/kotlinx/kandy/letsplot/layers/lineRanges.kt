/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.LineRangesBuilder


/**
 * Adds a new `lineRanges` layer to the plot.
 *
 * The `lineRanges` layer displays vertical lines, usually to indicate the range of some variable.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## LineRanges Aesthetics
 * * **`x`** - The X-coordinate specifying the categories.
 * * **`yMin`** - The minimum value for the Y-coordinate.
 * * **`yMax`** - The maximum value for the Y-coordinate.
 * * **`alpha`** - The transparency of the line-range.
 * * **`borderLine.color`** - The color of the line-range's borderline.
 * * **`borderLine.width`** - The width of the line-range's borderline.
 * * **`borderLine.type`** - The type of the line-range's borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     lineRanges {
 *         // Positional mapping
 *         x(listOf("Jan", "Feb", "Mar", "Apr", "May"))
 *         yMin(listOf(10, 12, 8, 14, 9))
 *         yMax(listOf(20, 22, 18, 24, 19))
 *
 *         // Non-positional settings
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 2.0
 *             type = LineType.DASHED
 *         }
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.lineRanges(block: LineRangesBuilder.() -> Unit) {
    createLayer(LineRangesBuilder(this).apply {
        position = Position.dodge()
    }, block)
}
