/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val BAR: LetsPlotGeom = LetsPlotGeom("bar")

public interface BarContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val width: WidthAes get() = WidthAes(this)
}

@PlotDslMarker
public open class BarContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), BarContextInterface

@PlotDslMarker
public open class BarContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), BarContextInterface

/**
 * Adds a new bars layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BarContextImmutable.x]
 *  - [y][BarContextImmutable.y]
 *
 *   Non-positional:
 *  - [color][BarContextImmutable.color] - bars fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][BarContextImmutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [width][BarContextImmutable.width] - bars width, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * bar {
 *    x(time) // mapping from `time` column to `X` with the default scale
 *    borderLine {
 *       color(model.scaled(..)) // mapping from `model` column to `borderLine.color` with an explicit scale
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.bar(block: BarContextImmutable.() -> Unit) {
    addLayer(BarContextImmutable(this).apply(block), BAR)
}

/**
 * Adds a new bars layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BarContextMutable.x]
 *  - [y][BarContextMutable.y]
 *
 *   Non-positional:
 *  - [color][BarContextMutable.color] - bars fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][BarContextMutable.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [width][BarContextMutable.width] - bars width, of the type [Double], mappable without an
 *  explicit scale. (TODO grouping)
 *  - [borderLine.type][BorderLineContextMutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextMutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextMutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * bar {
 *    x(listOf(1.0, 2.2, 3.1, 4.0, 4.9)) // mapping from `time` column to `X` with the default scale
 *
 *    borderLine.width(2.5) // // setting of constant `borderLine.width` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.bar(block: BarContextMutable.() -> Unit) {
    addLayer(BarContextMutable(this).apply(block), BAR)
}
