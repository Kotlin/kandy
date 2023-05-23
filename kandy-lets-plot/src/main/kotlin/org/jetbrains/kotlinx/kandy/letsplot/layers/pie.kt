/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.PieContext

@PublishedApi
internal val PIE: LetsPlotGeom = LetsPlotGeom("pie")

/**
 * Adds a new pie layer.
 *
 * Creates a context in which you can configure layer. Within it, you can set mappings and settings
 * on aesthetic attributes. Mappings allow you to set a relationship between data and attribute values,
 * while settings allow you to assign a constant value to the attributes.
 *
 * Mapping can be performed via method with the name of the corresponding aes.
 * Setting for non-positional attributes can be performed with simple assignment of variable with name of aes.
 * Setting for positional attributes can be performed with `.constant()` method of special property with
 * the same name as the attribute.
 *
 * Pie aesthetics:
 * * `x`
 * * `y`
 * * `fillColor`
 * * `slice`
 * * `hole`
 * * `explode`
 * * `size`
 * * `alpha`
 * * `stroke`
 * * `strokeColor`
 *
 * Example:
 *
 * ```
 * pie {
 *    // positional settings
 *    x.constant(10.0)
 *    y.constant(10.0)
 *
 *    slice(column<Double>("value"))
 *
 *    // non-positional setting
 *    size = 20.0
 *    // non-positional mapping
 *    fillColor("name") {
 *       // scale = ...
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContext.pie(block: PieContext.() -> Unit) {
    addLayer(PieContext(this).apply(block), PIE)
}
