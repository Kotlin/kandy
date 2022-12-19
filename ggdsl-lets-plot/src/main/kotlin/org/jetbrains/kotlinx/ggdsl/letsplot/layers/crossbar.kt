/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.BorderLineContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.BorderLineContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.CrossBarContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.CrossBarContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color


@PublishedApi
internal val CROSS_BAR: LetsPlotGeom = LetsPlotGeom("crossbar")

/**
 * Adds a new cross bars (bars with horizontal median line) layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][CrossBarContextImmutable.x]
 *  - [middle][CrossBarContextImmutable.middle] - position of median bar.
 *  - [yMin][CrossBarContextImmutable.yMin] - lower bound for error bar.
 *  - [yMax][CrossBarContextImmutable.yMax] - upper bound for error bar.
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][CrossBarContextImmutable.color] - boxplot fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][CrossBarContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [width][CrossBarContextImmutable.width] - cross bar width, of the type [Double], mappable without an
 *  explicit scale. (TODO grouping)
 *  - [fatten][CrossBarContextImmutable.fatten] - multiplicative factor applied to size of the middle bar,
 *  non-mappable.
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * crossBar {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with an explicit scale
 *    middle(midSpread) // mapping from `midSpread` column to `middle`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.crossBar(block: CrossBarContextImmutable.() -> Unit) {
    addLayer(CrossBarContextImmutable(this).apply(block), CROSS_BAR)
}

/**
 * Adds a new cross bars (bars with horizontal median line) layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][CrossBarContextMutable.x]
 *  - [middle][CrossBarContextMutable.middle] - position of median bar.
 *  - [yMin][CrossBarContextMutable.yMin] - lower bound for error bar.
 *  - [yMax][CrossBarContextMutable.yMax] - upper bound for error bar.
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][CrossBarContextMutable.color] - boxplot fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][CrossBarContextMutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [width][CrossBarContextMutable.width] - cross bar width, of the type [Double], mappable without an
 *  explicit scale. (TODO grouping)
 *  - [fatten][CrossBarContextMutable.fatten] - multiplicative factor applied to size of the middle bar,
 *  non-mappable.
 *  - [borderLine.type][BorderLineContextMutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextMutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextMutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * crossBar {
 *    x(listOf("A", "B", "C").scaled(..)) // mapping from list to `X` with an explicit scale
 *    middle(listOf(12, 18, 6)) // mapping from list column to `middle`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.crossBar(block: CrossBarContextMutable.() -> Unit) {
    addLayer(CrossBarContextMutable(this).apply(block), CROSS_BAR)
}
