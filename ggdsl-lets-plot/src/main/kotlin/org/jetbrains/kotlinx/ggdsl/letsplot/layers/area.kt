/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.AreaContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.AreaContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val AREA: LetsPlotGeom = LetsPlotGeom("area")

/**
 * Adds a new area layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][AreaContextImmutable.x]
 *  - [y][AreaContextImmutable.y]
 *
 *   Non-positional:
 *  - [color][AreaContextImmutable.color] - area fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][AreaContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * area {
 *    x(time) // mapping from `time` column to `X` with the default scale
 *    borderLine {
 *       color(model.scaled(..)) // mapping from `model` column to `borderLine.color` with an explicit scale
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.area(block: AreaContextImmutable.() -> Unit) {
    addLayer(AreaContextImmutable(this).apply(block), AREA)
}

/**
 * Adds a new area layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][AreaContextMutable.x]
 *  - [y][AreaContextMutable.y]
 *
 *   Non-positional:
 *  - [color][AreaContextMutable.color] - area fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][AreaContextMutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextMutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextMutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextMutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * area {
 *    x(listOf(1.0, 2.2, 3.1, 4.0, 4.9)) // mapping from list to `X` with the default scale
 *    borderLine.width(2.5) // // setting of constant `borderLine.width` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.area(block: AreaContextMutable.() -> Unit) {
    addLayer(AreaContextMutable(this).apply(block), AREA)
}
