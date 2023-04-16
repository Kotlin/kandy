/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.PathContext

@PublishedApi
internal val PATH: LetsPlotGeom = LetsPlotGeom("path")

/**
 * Adds a new path layer.
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
 * Path aesthetics:
 * * `x`
 * * `y`
 * * `color`
 * * `lineType`
 * * `width`
 * * `alpha`
 *
 * Example:
 *
 * ```
 * path {
 *    // positional mapping
 *    x(time) {
 *       ... // some mapping parameters
 *    }
 *    // positional setting
 *    y.constant(12.5)
 *
 *    // non-positional setting
 *    width = 2.5
 *    // non-positional mapping
 *    color("type")
 * }
 * ```
 */
public inline fun LayerCollectorContext.path(block: PathContext.() -> Unit) {
    addLayer(PathContext(this).apply(block), PATH)
}
