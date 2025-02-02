/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.CrossBarsBuilder


/**
 * Adds a new `errorBars` layer to the plot.
 *
 * The `errorBars` layer represents the uncertainty around a measurement
 * by displaying vertical lines above and below the point estimate.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## ErrorBars Aesthetics
 * * **`x`** - The X-coordinate specifying the categories.
 * * **`y`** - The medium value for the Y-coordinate, representing the medium of the error bar.
 * * **`yMin`** - The minimum value for the Y-coordinate, representing the lower end of the error bar.
 * * **`yMax`** - The maximum value for the Y-coordinate, representing the upper end of the error bar.
 * * **`alpha`** - The transparency of the error bars.
 * * **`fatten`** - The factor by which to "fatten" the width of the notch relative to the body.
 * * **`width`** - The width of the error bars.
 * * **`fillColor`** - The fill color of the crossbars.
 * * **`borderLine.color`** - The color of the error bars' borderline.
 * * **`borderLine.width`** - The width of the error bars' borderline.
 * * **`borderLine.type`** - The type of the error bars' borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * errorBars {
 *    // Positional mapping
 *    x(time) {
 *       ... // some mapping parameters
 *    }
 *    yMax.constant(100.0)
 *    // Even though the error bars have no "y" attribute, we can adjust the Y-axis
 *    y.axis.limits = 0.0 .. 110.0
 *
 *    // Non-positional settings
 *    alpha = 0.9
 *    borderLine.width = 2.5
 *    borderLine {
 *       color = Color.BLACK
 *    }
 *    // Non-positional mapping
 *    width("capacity")
 * }
 * ```
 */
public inline fun LayerCreatorScope.crossBars(block: CrossBarsBuilder.() -> Unit) {
    createLayer(CrossBarsBuilder(this).apply {
        position = Position.dodge()
    }, block)
}
