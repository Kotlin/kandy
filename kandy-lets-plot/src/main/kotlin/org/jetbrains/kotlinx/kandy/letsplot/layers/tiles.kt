/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.TilesContext


/**
 * Adds a new `tiles` layer to the plot.
 *
 * The `tiles` layer is used for visualizing data in a grid format, useful for creating heatmaps or 2D binning plots.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Tiles Aesthetics
 * * **`x`** - The X-coordinate for the bottom-left corner of the tile.
 * * **`y`** - The Y-coordinate for the bottom-left corner of the tile.
 * * **`fillColor`** - The fill color of the tile.
 * * **`alpha`** - The transparency of the tile.
 * * **`width`** - The width of the tile.
 * * **`height`** - The height of the tile.
 * * **`borderLine.color`** - The color of the borderLine.
 * * **`borderLine.width`** - The width of the borderLine.
 * * **`borderLine.type`** - The type of the borderLine (solid, dashed, etc.).
 *
 * ## Example Usage
 *
 * ```kotlin
 * val xCord by columnOf(1, 1, 2, 2, 3, 3)
 * val yCord by columnOf(1, 2, 1, 2, 1, 2)
 * val value by columnOf(5, 10, 15, 20, 25, 30)
 *
 * plot {
 *     tiles {
 *         // Positional mapping
 *         x(xCord)
 *         y(yCord)
 *
 *         // Non-positional mapping
 *         fillColor(value) {
 *             scale = continuousColorGradient2(Color.WHITE, Color.BLUE, Color.RED, 0.5)
 *         }
 *
 *         // Non-positional settings
 *         alpha = 0.8
 *         borderLine {
 *             color = Color.BLACK
 *             width = 0.5
 *         }
 *
 *         width = 0.9
 *         height = 0.9
 *     }
 * }
 * ```
 */
public inline fun LayerCollectorContext.tiles(block: TilesContext.() -> Unit) {
    addLayer(TilesContext(this).apply(block))
}