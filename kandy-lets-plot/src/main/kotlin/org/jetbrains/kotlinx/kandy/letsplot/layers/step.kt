/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.StepBuilder

/**
 * Adds a new `step` layer to the plot.
 *
 * The `step` layer is used to create step plots,
 * which are useful for representing data that changes at discrete intervals,
 * often seen in time series or ordinal data.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *   For positional aesthetics, you can use the `.constant()` method.
 *
 * ## Step Aesthetics
 * * **`x`** - The X-coordinate specifying the points at which the steps change.
 * * **`y`** - The Y-coordinate specifying the height of each step.
 * * **`color`** - The color of the steps.
 * * **`lineType`** - The type of the step line, such as dashed or dotted.
 * * **`width`** - The width of the step line.
 * * **`alpha`** - The transparency of the step line.
 *
 * ## Example Usage
 *
 * ```kotlin
 * plot {
 *     step {
 *         // Positional mapping
 *         x(listOf(1, 2, 3, 4, 5))
 *         y(listOf(3, 5, 2, 8, 3))
 *
 *         // Non-positional mapping
 *         color = Color.RED
 *
 *         // Non-positional settings
 *         width = 2.5
 *         alpha = 0.7
 *         lineType = LineType.LONGDASH
 *     }
 * }
 * ```
 */
public inline fun LayerCreatorScope.step(block: StepBuilder.() -> Unit) {
    createLayer(StepBuilder(this), block)
}