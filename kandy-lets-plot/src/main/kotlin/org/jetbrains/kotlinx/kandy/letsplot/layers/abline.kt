/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers


import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.ABLineContext
import org.jetbrains.kotlinx.kandy.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color

@PublishedApi
internal val AB_LINE: LetsPlotGeom = LetsPlotGeom("abline")
/*
*/
/**
 * Adds a new line with specified slope and intercept layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [slope][ABLineContextImmutable.slope] - line slope.
 *  - [intercept][ABLineContextImmutable.intercept] - line intercept.
 *
 *   Non-positional:
 *  - [color][ABLineContextImmutable.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][ABLineContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][ABLineContextImmutable.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][ABLineContextImmutable.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * abLine {
 *    intercept(0.5) // setting of constant `intercept` value
 *    color(gType.scaled(..)) // mapping from `gType` column to `color` with some scale
 * }
 * ```
 *//*

public inline fun LayerCollectorContextImmutable.abLine(block: ABLineContextImmutable.() -> Unit) {
    addLayer(ABLineContextImmutable(this).apply(block), AB_LINE)
}

*/
/**
 * Adds a new line with specified slope and intercept layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [slope][ABLineContextMutable.slope] - line slope.
 *  - [intercept][ABLineContextMutable.intercept] - line intercept.
 *
 *   Non-positional:
 *  - [color][ABLineContextMutable.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][ABLineContextMutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][ABLineContextMutable.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][ABLineContextMutable.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * abLine {
 *    slope(0.5) // setting of constant `slope` value
 *    color(listOf("A", "B", "B", "A").scaled(..)) // mapping from list to `color` with some scale
 * }
 * ```
 *//*


*/

public inline fun LayerCollectorContext.abLine(block: ABLineContext.() -> Unit) {
    addLayer(ABLineContext(this).apply(block), AB_LINE)
}