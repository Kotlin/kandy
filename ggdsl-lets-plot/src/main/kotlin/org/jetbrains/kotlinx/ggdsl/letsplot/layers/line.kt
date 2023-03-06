/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.LineContext

// TODO

@PublishedApi
internal val LINE: LetsPlotGeom = LetsPlotGeom("line")
/*
/**
 * Adds a new line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineContextInterface.x]
 *  - [ y][LineContextInterface.y]
 *
 *   Non-positional:
 *  - [color][LineContextInterface.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][LineContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][LineContextInterface.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][LineContextInterface.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * line {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with some scale
 *    type(LineType.DOTTED) // setting of constant `type` value
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.line(block: LineContextImmutable.() -> Unit) {
    addLayer(LineContextImmutable(this).apply(block), LINE)
}

/**
 * Adds a new line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineContextInterface.x]
 *  - [ y][LineContextInterface.y]
 *
 *   Non-positional:
 *  - [color][LineContextInterface.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][LineContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][LineContextInterface.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][LineContextInterface.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * line {
 *    x(listOf(4.2f, 5.5f, 8.9f).scaled(..)) // mapping from list to `X` with some scale
 *    type(LineType.DOTTED) // setting of constant `type` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.line(block: LineContextMutable.() -> Unit) {
    addLayer(LineContextMutable(this).apply(block), LINE)
}


 */

public inline fun LayerCollectorContext.line(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), LINE)
}