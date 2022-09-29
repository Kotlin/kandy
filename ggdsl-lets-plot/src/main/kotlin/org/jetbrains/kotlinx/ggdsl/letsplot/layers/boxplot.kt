/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color


public val BOXPLOT: LetsPlotGeom = LetsPlotGeom("boxplot")

@PlotDslMarker
public class BoxplotContext(override var data: MutableNamedData) : WithBorderLineContext() {
    public val x: XAes = XAes(this)

    public val lower: LowerAes = LowerAes(this)
    public val upper: UpperAes = UpperAes(this)
    public val middle: MiddleAes = MiddleAes(this)
    public val yMin: YMinAes = YMinAes(this)
    public val yMax: YMaxAes = YMaxAes(this)

    public val fatten: FattenAes = FattenAes(this)

    public val color: FillAes = FillAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
}

/**
 * Adds a new boxplot layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * boxplot {
 *    x(source<Double>("time")) // mapping from data source to 'x' coordinate
 *    borderLine.color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BoxplotContext.x]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [lower][BoxplotContext.lower] - lower hinge of the boxplot
 *  - [upper][BoxplotContext.upper] - upper hinge of the boxplot
 *  - [middle][BoxplotContext.middle] - median of the boxplot
 *  - [yMin][BoxplotContext.yMin] - lower whisker of the boxplot
 *  - [yMax][BoxplotContext.yMax] - upper whisker of the boxplot
 *
 *   Non-positional:
 *  - [color][BoxplotContext.color] - color of the boxplot filling, of the type [Color], mappable
 *  - [alpha][BoxplotContext.alpha] - layer alpha, of the type [Double], mappable
 *  - [fatten][BoxplotContext.fatten] - a multiplicative factor applied to size of the middle bar, of the type [Double], non-mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.
 *
 *  // TODO write about borderLine invocation?
 *  ```
 *  boxplot {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *
 * // TODO move data overriding to args
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][BoxplotContext.data].
 *
 *  // TODO refer to bindings?
 */
public inline fun PlotContext.boxplot(block: BoxplotContext.() -> Unit) {
    layers.add(BoxplotContext(data).apply { copyFrom(this@boxplot) }.apply(block).toLayer(BOXPLOT))
}
