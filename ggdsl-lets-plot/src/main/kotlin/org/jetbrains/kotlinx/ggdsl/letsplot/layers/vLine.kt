/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom

// TODO

@PublishedApi
internal val V_LINE: LetsPlotGeom = LetsPlotGeom("vLine")
/*
/**
 * Adds a new vertical line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][VLineContextImmutable.x]
 *
 *   Non-positional:
 *  - [color][VLineContextImmutable.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][VLineContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][VLineContextImmutable.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][VLineContextImmutable.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * vLine {
 *    x(0.5) // setting of constant `y` value
 *    color(gType.scaled(..)) // mapping from `gType` column to `color` with some scale
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.vLine(block: VLineContextImmutable.() -> Unit) {
    addLayer(VLineContextImmutable(this).apply(block), V_LINE)
}

/**
 * Adds a new vertical line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][VLineContextMutable.x]
 *
 *   Non-positional:
 *  - [color][VLineContextMutable.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][VLineContextMutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][VLineContextMutable.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][VLineContextMutable.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * vLine {
 *    x(listOf(0.1, 0.5, 0.6) // mapping from list to `Y` with an implicit scale
 *    color(Color.RED) // setting of constant `color` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.vLine(block: VLineContextMutable.() -> Unit) {
    addLayer(VLineContextMutable(this).apply(block), V_LINE)
}



 */