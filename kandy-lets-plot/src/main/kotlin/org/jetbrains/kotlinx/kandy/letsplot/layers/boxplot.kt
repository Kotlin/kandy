/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BoxplotContext
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position

/**
 * Adds a new boxplot layer.
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
 * Boxplot aesthetics:
 * * `x`
 * * `yMin`
 * * `lower`
 * * `middle`
 * * `upper`
 * * `yMax`
 * * `fillColor`
 * * `alpha`
 * * `width`
 * * `fatten`
 * * `borderLine.color`
 * * `borderLine.width`
 * * `borderLine.type`
 *
 * Example:
 *
 * ```
 * boxplot {
 *    // positional mapping
 *    x(time) {
 *       ... // some mapping parameters
 *    }
 *    yMax.constant(100.0)
 *    // even though the boxplot has no "y" attribute we can adjust the `Y` axis
 *    y.limits = 0.0 .. 110.0
 *
 *    // non-positional settings
 *    fatten = 0.8
 *    borderLine.width = 2.5
 *    borderLine {
 *       color = Color.BLACK
 *    }
 *    // non-positional mapping
 *    fillColor("type")
 *
 * }
 * ```
 */
@Suppress("invisible_reference")
public inline fun LayerCollectorContext.boxplot(block: BoxplotContext.() -> Unit) {
    // todo letsplot fix
    addLayer(BoxplotContext(this).apply {
        position = Position.dodge()
    }.apply(block).apply {
        addPositionalMapping(Y, List(datasetHandler.rowsCount()) { null }, null, null)
    })
}
