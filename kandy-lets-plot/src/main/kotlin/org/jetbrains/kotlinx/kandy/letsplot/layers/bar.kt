/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BarsContext
import org.jetbrains.kotlinx.kandy.letsplot.position.Position
import org.jetbrains.kotlinx.kandy.letsplot.position.position
import org.jetbrains.kotlinx.kandy.letsplot.reversed

@PublishedApi
internal val BAR: LetsPlotGeom = LetsPlotGeom("bar")

/**
 * Adds a new bars layer.
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
 * Bars aesthetics:
 * * `x`
 * * `y`
 * * `fillColor`
 * * `alpha`
 * * `width`
 * * `borderLine.color`
 * * `borderLine.width`
 * * `borderLine.type`
 *
 * Example:
 *
 * ```
 * bars {
 *    // positional mapping
 *    x(time) {
 *       ... // some mapping parameters
 *    }
 *    y.constant(100.0)
 *
 *    // non-positional settings
 *    alpha = 0.8
 *    borderLine.width = 2.5
 *    borderLine {
 *       color = Color.BLACK
 *    }
 *    // non-positional mapping
 *    fillColor("type")
 *
 *    // position adjustment
 *    position = Position.Stack
 * }
 * ```
 */
public inline fun LayerCollectorContext.bars(block: BarsContext.() -> Unit) {
    addLayer(BarsContext(this).apply {
        position = Position.Dodge()
    }.apply(block), BAR)
}

/**
 * Adds a new horizontal bars layer.
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
 * Bars aesthetics:
 * * `x`
 * * `y`
 * * `fillColor`
 * * `alpha`
 * * `width`
 * * `borderLine.color`
 * * `borderLine.width`
 * * `borderLine.type`
 *
 * Example:
 *
 * ```
 * barsH {
 *    // positional mapping
 *    y(time) {
 *       ... // some mapping parameters
 *    }
 *    x.constant(100.0)
 *
 *    // non-positional settings
 *    alpha = 0.8
 *    borderLine.width = 2.5
 *    borderLine {
 *       color = Color.BLACK
 *    }
 *    // non-positional mapping
 *    fillColor("type")
 *
 *    // position adjustment
 *    position = Position.Stack
 * }
 * ```
 */
public inline fun LayerCollectorContext.barsH(block: BarsContext.() -> Unit) {
    addLayer(BarsContext(this).apply {
        reversed = true
    }.apply(block), BAR)
}
