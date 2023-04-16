/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.HLineContext

// TODO

@PublishedApi
internal val H_LINE: LetsPlotGeom = LetsPlotGeom("hLine")

/**
 * Adds a new horizontal line layer.
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
 * Line aesthetics:
 * * `y`
 * * `color`
 * * `type`
 * * `width`
 * * `alpha`
 *
 * Example:
 *
 * ```
 * hLine {
 *    // positional mapping
 *    y(count) {
 *       ... // some mapping parameters
 *    }
 *    // even though the h-line have no "x" attribute we can adjust the `X` axis
 *    x.limits = 0.0 to 5.0
 *    // non-positional setting
 *    width = 2.5
 *    // non-positional mapping
 *    color("count")
 * }
 * ```
 */
public inline fun LayerCollectorContext.hLine(block: HLineContext.() -> Unit) {
    addLayer(HLineContext(this).apply(block), H_LINE)
}
