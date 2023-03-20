/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.LineRangeContext


@PublishedApi
internal val LINE_RANGE: LetsPlotGeom = LetsPlotGeom("linerange")
/*
/**
 * Adds a new line ranges layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineRangeContextInterface.x]
 *  - [yMin][LineRangeContextInterface.yMin] - lower bound for range.
 *  - [yMax][LineRangeContextInterface.yMax] - upper bound for range.
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [alpha][LineRangeContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextInterface.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextInterface.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextInterface.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * lineRange {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with an explicit scale
 *    yMin(minSpread) // mapping from `minSpread` column to `yMin`
 *
 *    borderLine {
 *       color(Color.GREEN) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.lineRange(block: LineRangeContextImmutable.() -> Unit) {
    addLayer(LineRangeContextImmutable(this).apply(block), LINE_RANGE)
}

/**
 * Adds a new line ranges layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineRangeContextInterface.x]
 *  - [yMin][LineRangeContextInterface.yMin] - lower bound for range.
 *  - [yMax][LineRangeContextInterface.yMax] - upper bound for range.
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [alpha][LineRangeContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextInterface.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextInterface.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextInterface.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * lineRange {
 *    x(listOf("A" ,"B").scaled(..)) // mapping from list to `X` with an explicit scale
 *    yMin(listOf(3.4, 1.2)) // mapping from list to `yMin`
 *
 *    borderLine {
 *       color(Color.GREEN) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.lineRange(block: LineRangeContextMutable.() -> Unit) {
    addLayer(LineRangeContextMutable(this).apply(block), LINE_RANGE)
}

 */

public inline fun LayerCollectorContext.lineRange(block: LineRangeContext.() -> Unit) {
    addLayer(LineRangeContext(this).apply(block), LINE_RANGE)
}