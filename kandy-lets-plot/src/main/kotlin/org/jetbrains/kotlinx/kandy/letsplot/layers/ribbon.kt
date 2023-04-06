/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.RibbonContext


@PublishedApi
internal val RIBBON: LetsPlotGeom = LetsPlotGeom("ribbon")
/*
/**
 * Adds a new ribbon layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][RibbonContextInterface.x]
 *  - [yMin][RibbonContextInterface.yMin]
 *  - [yMax][RibbonContextInterface.yMax]
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][RibbonContextInterface.color] - ribbon fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][RibbonContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * ribbon {
 *    yMin(leftDown) // mapping from `leftDown` column to `yMin`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.ribbon(block: RibbonContextImmutable.() -> Unit) {
    addLayer(RibbonContextImmutable(this).apply(block), RIBBON)
}

/**
 * Adds a new rect layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][RibbonContextInterface.x]
 *  - [yMin][RibbonContextInterface.yMin]
 *  - [yMax][RibbonContextInterface.yMax]
 *  // TODO y
 *
 *   Non-positional:
 *
 *  - [color][RibbonContextInterface.color] - rect fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][RibbonContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * rect {
 *    yMin(listOf(1, 1, 2, 2)) // mapping from list to `yMin`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.ribbon(block: RibbonContextMutable.() -> Unit) {
    addLayer(RibbonContextMutable(this).apply(block), RIBBON)
}


 */

public inline fun LayerCollectorContext.ribbon(block: RibbonContext.() -> Unit) {
    addLayer(RibbonContext(this).apply(block), RIBBON)
}