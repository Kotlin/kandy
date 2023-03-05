/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom

// TODO

@PublishedApi
internal val H_LINE: LetsPlotGeom = LetsPlotGeom("hLine")
/*
/**
 * Adds a new horizontal line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ y][HLineContextImmutable.y]
 *
 *   Non-positional:
 *  - [color][HLineContextImmutable.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][HLineContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][HLineContextImmutable.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][HLineContextImmutable.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * hLine {
 *    y(0.5) // setting of constant `y` value
 *    color(gType.scaled(..)) // mapping from `gType` column to `color` with some scale
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.hLine(block: HLineContextImmutable.() -> Unit) {
    addLayer(HLineContextImmutable(this).apply(block), H_LINE)
}

/**
 * Adds a new horizontal line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ y][HLineContextMutable.y]
 *
 *   Non-positional:
 *  - [color][HLineContextMutable.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][HLineContextMutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][HLineContextMutable.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][HLineContextMutable.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * hLine {
 *    y(listOf(0.1, 0.5, 0.6) // mapping from list to `Y` with an implicit scale
 *    color(Color.RED) // setting of constant `color` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.hLine(block: HLineContextMutable.() -> Unit) {
    addLayer(HLineContextMutable(this).apply(block), H_LINE)
}

 */
