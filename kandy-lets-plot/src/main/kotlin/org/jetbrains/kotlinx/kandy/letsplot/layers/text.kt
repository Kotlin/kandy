/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.TextContext

@PublishedApi
internal val TEXT: LetsPlotGeom = LetsPlotGeom("text")

/**
 * Adds a new text layer.
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
 * Text aesthetics:
 * * `x`
 * * `y`
 * * `label`
 * * `alpha`
 * * `width`
 * * `font.color`
 * * `font.family`
 * * `font.size`
 * * `font.face`
 *
 * Example:
 *
 * ```
 * text {
 *    // positional mapping
 *    x(time) {
 *       ... // some mapping parameters
 *    }
 *    y.constant(20.0F)
 *
 *    // non-positional settings
 *    alpha = 0.8
 *    font.size = 2.5
 *    font {
 *       color = Color.RED
 *    }
 *    // non-positional mapping
 *    label("status")
 * }
 * ```
 */
public inline fun LayerCollectorContext.text(block: TextContext.() -> Unit) {
    addLayer(TextContext(this).apply(block), TEXT)
}
