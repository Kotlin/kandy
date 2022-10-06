/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color


@PublishedApi
internal val CROSS_BAR: LetsPlotGeom = LetsPlotGeom("crossbar")

@PlotDslMarker
public class CrossBarContext(parent: LayerCollectorContext) :
    WithBorderLineContext(parent) {
    public val x: XAes = XAes(this)
    //todo
    public val y: YDummyAes = YDummyAes(this)

    public val yMin: YMinAes = YMinAes(this)
    public val yMax: YMaxAes = YMaxAes(this)
    public val middle: MiddleAes = MiddleAes(this)

    public val fatten: FattenAes = FattenAes(this)

    public val width: WidthAes = WidthAes(this)
    public val color: FillAes = FillAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

}

/**
 * Adds a new cross bar layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * crossBar {
 *    x(source<Double>("time")) // mapping from data source to 'x' coordinate
 *    borderLine.color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][CrossBarContext.x]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [middle][CrossBarContext.middle] - median of the cross bar
 *  - [yMin][CrossBarContext.yMin] - lower bound of the cross bar
 *  - [yMax][CrossBarContext.yMax] - upper bound of the cross bar
 *
 *   Non-positional:
 *  - [color][CrossBarContext.color] - color of the cross bar filling, of the type [Color], mappable
 *  - [alpha][CrossBarContext.alpha] - layer alpha, of the type [Double], mappable
 *  - [width][CrossBarContext.width] - with of the cross bar, of the type [Double], mappable
 *  - [fatten][CrossBarContext.fatten] - a multiplicative factor applied to size of the middle bar, of the type [Double], non-mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.
 *
 *  // TODO write about borderLine invocation?
 *  ```
 *  crossBar {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *
 * // TODO move data overriding to args
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][CrossBarContext.data].
 *
 *  // TODO refer to bindings?
 */
public inline fun LayerCollectorContext.crossBar(block: CrossBarContext.() -> Unit) {
    addLayer(CrossBarContext(this).apply(block), CROSS_BAR)
}