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
internal val ERROR_BAR: LetsPlotGeom = LetsPlotGeom("errorbar")

@PlotDslMarker
public class ErrorBarContext(parent: LayerCollectorContext) :
    WithBorderLineContext(parent) {
    public val x: XAes = XAes(this)

    public val yMin: YMinAes = YMinAes(this)
    public val yMax: YMaxAes = YMaxAes(this)

    public val alpha: AlphaAes = AlphaAes(this)
    public val width: WidthAes = WidthAes(this)
    // todo just 'line' instead borderline???
}

/**
 * Adds a new error bar layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * errorBar {
 *    x(source<Double>("time")) // mapping from data source to 'x' coordinate
 *    borderLine.color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][ErrorBarContext.x]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [yMin][ErrorBarContext.yMin] - lower bound of the error bar
 *  - [yMax][ErrorBarContext.yMax] - upper bound of the error bar
 *
 *   Non-positional:
 *  - [alpha][ErrorBarContext.alpha] - layer alpha, of the type [Double], mappable
 *  - [width][ErrorBarContext.width] - with of the error bar, of the type [Double], mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.
 *
 *  // TODO write about borderLine invocation?
 *  ```
 *  errorBar {
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
public inline fun LayerCollectorContext.errorBar(block: ErrorBarContext.() -> Unit) {
    addLayer(ErrorBarContext(this).apply(block), ERROR_BAR)
}
