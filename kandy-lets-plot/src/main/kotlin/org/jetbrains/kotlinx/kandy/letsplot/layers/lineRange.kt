/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.LineRangeContext


@PublishedApi
internal val LINE_RANGE: LetsPlotGeom = LetsPlotGeom("linerange")

/**
 * Adds a new line-range layer.
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
 * Line-range aesthetics:
 * * `x`
 * * `yMin`
 * * `yMax`
 * * `alpha`
 * * `borderLine.color`
 * * `borderLine.width`
 * * `borderLine.type`
 *
 * Example:
 *
 * ```
 * lineRange {
 *    // positional mapping
 *    x(time) {
 *       ... // some mapping parameters
 *    }
 *    yMax.constant(100.0)
 *    // even though the line-range bars have no "y" attribute we can adjust the `Y` axis
 *    y.limits = 0.0 .. 110.0
 *
 *    // non-positional settings
 *    alpha = 0.9
 *    borderLine.width = 2.5
 *    // non-positional mapping
 *    borderLine {
 *       color("capacity")
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContext.lineRange(block: LineRangeContext.() -> Unit) {
    addLayer(LineRangeContext(this).apply(block), LINE_RANGE)
}