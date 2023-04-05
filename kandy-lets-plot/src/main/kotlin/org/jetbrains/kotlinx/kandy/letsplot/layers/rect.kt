/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.RectContext

@PublishedApi
internal val RECT: LetsPlotGeom = LetsPlotGeom("rect")
/*
/**
 * Adds a new rect layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [xMin][RectContextInterface.xMin]
 *  - [yMin][RectContextInterface.yMin]
 *  - [xMax][RectContextInterface.xMax]
 *  - [yMax][RectContextInterface.yMax]
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][RectContextInterface.color] - rect fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][RectContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * rect {
 *    yMin(leftDown) // mapping from `leftDown` column to `yMin`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.rect(block: RectContextImmutable.() -> Unit) {
    addLayer(RectContextImmutable(this).apply(block), RECT)
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
 *  - [xMin][RectContextInterface.xMin]
 *  - [yMin][RectContextInterface.yMin]
 *  - [xMax][RectContextInterface.xMax]
 *  - [yMax][RectContextInterface.yMax]
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][RectContextInterface.color] - rect fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][RectContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
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
public inline fun LayerCollectorContextMutable.rect(block: RectContextMutable.() -> Unit) {
    addLayer(RectContextMutable(this).apply(block), RECT)
}


 */

public inline fun LayerCollectorContext.rect(block: RectContext.() -> Unit) {
    addLayer(RectContext(this).apply(block), RECT)
}