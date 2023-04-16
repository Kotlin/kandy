/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.PointRangeContext


@PublishedApi
internal val POINT_RANGE: LetsPlotGeom = LetsPlotGeom("pointrange")

/**
 * Adds a new point-range layer.
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
 * Point-range aesthetics:
 * * `x`
 * * `y`
 * * `yMin`
 * * `yMax`
 * * `alpha`
 * * `color`
 * * `size`
 * * `fatten`
 * * `innerLine.type`
 * * `innerPoint.symbol`
 * * `innerPoint.fillColor`
 * * `innerPoint.symbol`
 *
 * Example:
 *
 * ```
 * pointRange {
 *    // positional mapping
 *    x(time) {
 *       ... // some mapping parameters
 *    }
 *    yMax.constant(100.0)
 *
 *    // non-positional settings
 *    alpha = 0.9
 *    innerLine.type = LineType.DOTTED
 *    // non-positional mapping
 *    innerPoint {
 *       symbol("capacity")
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContext.pointRange(block: PointRangeContext.() -> Unit) {
    addLayer(PointRangeContext(this).apply(block), POINT_RANGE)
}
