/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.VLineContext


/**
 * Adds a new vertical line layer.
 *
 * Creates a context in which you can configure layer. Within it, you can set mappings and settings
 * on aesthetic attributes. Mappings allow you to set a relationship between data and attribute values,
 * while settings allow you to assign a constant value to the attributes.
 *
 * Mapping can be performed via method with name of corresponding aes.
 * Setting for non-positional attributes can be performed with simple assignment of variable with name of aes.
 * Setting for positional attributes can be performed with `.constant()` method of special property with
 * the same name as the attribute.
 *
 * Vertical ine aesthetics:
 * * `x`
 * * `color`
 * * `type`
 * * `width`
 * * `alpha`
 *
 * Example:
 *
 * ```
 * vLine {
 *    // positional mapping
 *    x(count) {
 *       ... // some mapping parameters
 *    }
 *    // even though the v-line have no "y" attribute we can adjust the `Y` axis
 *    y.limits = 0.0 to 5.0
 *    // non-positional setting
 *    width = 2.5
 *    // non-positional mapping
 *    color("count")
 * }
 * ```
 */
public inline fun LayerCollectorContext.vLine(block: VLineContext.() -> Unit) {
    addLayer(VLineContext(this).apply(block))
}