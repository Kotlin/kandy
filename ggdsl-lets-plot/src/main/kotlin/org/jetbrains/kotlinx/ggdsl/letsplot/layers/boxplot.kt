/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.BorderLineContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.BoxplotContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.BoxplotContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val BOXPLOT: LetsPlotGeom = LetsPlotGeom("boxplot")

/**
 * Adds a new boxplot layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BoxplotContextImmutable.x]
 *  - [lower][BoxplotContextImmutable.lower] - lower hinge, 25% quantile.
 *  - [middle][BoxplotContextImmutable.middle] - middle median, 50% quantile.
 *  - [upper][BoxplotContextImmutable.upper] - upper upper hinge, 75% quantile.
 *  - [yMin][BoxplotContextImmutable.yMin] - lower whisker = smallest observation greater than
 *  or equal to lower hinge - 1.5 * IQR.
 *  - [yMax][BoxplotContextImmutable.yMax] - upper whisker = largest observation less than
 *  or equal to upper hinge + 1.5 * IQR.
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][BoxplotContextImmutable.color] - boxplot fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][BoxplotContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [fatten][BoxplotContextImmutable.fatten] - multiplicative factor applied to size of the middle bar,
 *  non-mappable.
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * boxplot {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with an explicit scale
 *    middle(midSpread) // mapping from `midSpread` column to `middle`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.boxplot(block: BoxplotContextImmutable.() -> Unit) {
    addLayer(BoxplotContextImmutable(this).apply(block), BOXPLOT)
}

/**
 * Adds a new boxplot layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BoxplotContextImmutable.x]
 *  - [lower][BoxplotContextImmutable.lower] - lower hinge, 25% quantile.
 *  - [middle][BoxplotContextImmutable.middle] - middle median, 50% quantile.
 *  - [upper][BoxplotContextImmutable.upper] - upper upper hinge, 75% quantile.
 *  - [yMin][BoxplotContextImmutable.yMin] - lower whisker = smallest observation greater than
 *  or equal to lower hinge - 1.5 * IQR.
 *  - [yMax][BoxplotContextImmutable.yMax] - upper whisker = largest observation less than
 *  or equal to upper hinge + 1.5 * IQR.
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][BoxplotContextImmutable.color] - boxplot fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][BoxplotContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [fatten][BoxplotContextImmutable.fatten] - multiplicative factor applied to size of the middle bar,
 *  non-mappable.
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * boxplot {
 *    x(listOf("A", "B", "C").scaled(..)) // mapping from list to `X` with an explicit scale
 *    middle(listOf(0.2, 0.44, 0.29)) // mapping from list to `middle`
 *
 *    borderLine.width(2.5) // // setting of constant `borderLine.width` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.boxplot(block: BoxplotContextMutable.() -> Unit) {
    addLayer(BoxplotContextMutable(this).apply(block), BOXPLOT)
}
