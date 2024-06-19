/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.TextBuilder

/**
 * Adds a new `text` layer to the plot.
 *
 * The `text` layer is used for annotating a plot with text labels,
 * providing supplementary information that can help in data interpretation or presentation.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Text Aesthetics
 * * **`x`** - The X-coordinate specifying the position of the text.
 * * **`y`** - The Y-coordinate specifying the position of the text.
 * * **`label`** - The text content of the label.
 * * **`alpha`** - The transparency of the text.
 * * **`font.color`** - The color of the text.
 * * **`font.family`** - The font family for the text.
 * * **`font.size`** - The font size of the text.
 * * **`font.face`** - The font style (italic, bold, etc.) of the text.
 *
 * ## Example Usage
 *
 * ```kotlin
 * plot {
 *     text {
 *         // Positional mapping
 *         x(listOf(1, 2, 3, 4, 5))
 *         y(listOf(5, 10, 20, 15, 8))
 *
 *         // Non-positional mapping
 *         label(listOf("Low", "Medium", "High", "Medium", "Low").toColumn("status"))
 *
 *         // Non-positional settings
 *         alpha = 0.8
 *         font {
 *             size = 12.0
 *             color = Color.BLUE
 *             face = FontFace.BOLD
 *         }
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.text(block: TextBuilder.() -> Unit) {
    createLayer(TextBuilder(this), block)
}
