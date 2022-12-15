/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType


@PublishedApi
internal val ERROR_BAR: LetsPlotGeom = LetsPlotGeom("errorbar")

/**
 * Adds a new error bars layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][ErrorBarContextImmutable.x]
 *  - [yMin][ErrorBarContextImmutable.yMin] - lower bound for error bar.
 *  - [yMax][ErrorBarContextImmutable.yMax] - upper bound for error bar.
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [alpha][ErrorBarContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [width][ErrorBarContextImmutable.width] - error bar width, of the type [Double], mappable without an
 *  explicit scale. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * errorBar {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with an explicit scale
 *    yMin(minSpread) // mapping from `minSpread` column to `yMin`
 *
 *    borderLine {
 *       color(Color.GREEN) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.errorBar(block: ErrorBarContextImmutable.() -> Unit) {
    addLayer(ErrorBarContextImmutable(this).apply(block), ERROR_BAR)
}

/**
 * Adds a new error bars layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][CrossBarContextMutable.x]
 *  - [yMin][CrossBarContextMutable.yMin] - lower bound for error bar.
 *  - [yMax][CrossBarContextMutable.yMax] - upper bound for error bar.
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [alpha][ErrorBarContextMutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [width][ErrorBarContextMutable.width] - error bar width, of the type [Double], mappable without an
 *  explicit scale. (TODO grouping)
 *  - [borderLine.type][BorderLineContextMutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextMutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextMutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * errorBar {
 *    x(listOf("A", "B", "C").scaled(..)) // mapping from list to `X` with an explicit scale
 *    yMin(listOf(12, 18, 6)) // mapping from list column to `middle`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.errorBar(block: ErrorBarContextMutable.() -> Unit) {
    addLayer(ErrorBarContextMutable(this).apply(block), ERROR_BAR)
}
