/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val RECT: LetsPlotGeom = LetsPlotGeom("rect")

@PlotDslMarker
public class RectContext(parent: LayerCollectorContext) :
    WithBorderLineContext(parent) {
    public val xMin: XMinAes get() = XMinAes(this)
    public val xMax: XMaxAes get() = XMaxAes(this)
    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

/**
 * Adds a new rect layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * rect {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Sub-positional:
 *
 *  - [xMin][RectContext.xMin]
 *  - [yMin][RectContext.yMin]
 *  - [xMax][RectContext.xMax]
 *  - [yMax][RectContext.yMax]
 *
 *   Non-positional:
 *  - [color][RectContext.color] - color of the raster filling, of the type [Color], mappable
 *  - [alpha][RectContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.

 *
 *  By default, the dataset inherited from the parent [LayerPlotContext] is used,
 *  but can be overridden with an assignment to the [data][RasterContext.data].
 *
 * // TODO move data overriding to args
 *  ```
 *  rect {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *  // TODO refer to bindings?
 */
public inline fun LayerCollectorContext.rect(block: RectContext.() -> Unit) {
    addLayer(RectContext(this).apply(block), RECT)
}
