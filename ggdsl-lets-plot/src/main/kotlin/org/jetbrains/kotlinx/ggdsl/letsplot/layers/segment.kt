/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val SEGMENT: LetsPlotGeom = LetsPlotGeom("segment")


public class SegmentContext(override var data: MutableNamedData) :
    LayerContext() {
    // TODO
    public val xBegin: XAes = XAes(this)
    public val yBegin: YAes = YAes(this)
    public val xEnd: XEndAes = XEndAes(this)
    public val yEnd: YEndAes = YEndAes(this)
    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
    public val lineType: LineTypeAes = LineTypeAes(this)
    public val width: SizeAes = SizeAes(this)

    // todo speed and flow
}

/**
 * Adds a new ribbon layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * ribbon {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointRangeContext.x]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).

 *  Sub-positional:
 *
 *  - [yMin][RibbonContext.yMin]
 *  - [yMax][RibbonContext.yMax]
 *
 *   Non-positional:
 *  - [color][RibbonContext.color] - color of the raster filling, of the type [Color], mappable
 *  - [alpha][RibbonContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.

 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][RibbonContext.data].
 *
 * // TODO move data overriding to args
 *  ```
 *  ribbon {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *  // TODO refer to bindings?
 */
public fun PlotContext.segment(block: SegmentContext.() -> Unit) {
    layers.add(SegmentContext(data).apply { copyFrom(this@segment) }.apply(block).toLayer(SEGMENT))
}
