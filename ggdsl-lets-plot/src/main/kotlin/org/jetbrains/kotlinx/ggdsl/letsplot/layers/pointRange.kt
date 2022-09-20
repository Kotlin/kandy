/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val POINT_RANGE: LetsPlotGeom = LetsPlotGeom("pointrange")

@PlotDslMarker
public class InnerPointSubContext(parentContext: BindingContext) : SubContext(parentContext) {
    override var data: MutableNamedData = mutableMapOf()
    public val symbol: ShapeAes = ShapeAes(parentContext)
    public val fillColor: FillAes = FillAes(parentContext)
    public val fatten: FattenAes = FattenAes(parentContext)
}

@PlotDslMarker
public class InnerLineSubContext(parentContext: BindingContext) : SubContext(parentContext) {
    override var data: MutableNamedData = mutableMapOf()

    //   val color = ColorAes(parentContext)
    public val type: LineTypeAes = LineTypeAes(parentContext)
    //val width = SIZE // TODO mappable???
}

@PlotDslMarker
public class PointRangeContext(override var data: MutableNamedData) : LayerContext() {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
    public val yMin: YMinAes = YMinAes(this)
    public val yMax: YMaxAes = YMaxAes(this)

    public val alpha: AlphaAes = AlphaAes(this)
    public val color: ColorAes = ColorAes(this)

    // todo separate????
    public val size: SizeAes = SizeAes(this)

    public val innerPoint: InnerPointSubContext = InnerPointSubContext(this)

    public inline operator fun InnerPointSubContext.invoke(block: InnerPointSubContext.() -> Unit) {
        apply(block)
    }

    public val innerLine: InnerLineSubContext = InnerLineSubContext(this)

    public inline operator fun InnerLineSubContext.invoke(block: InnerLineSubContext.() -> Unit) {
        apply(block)
    }
}

/**
 * Adds a new point range layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * lineRange {
 *    x(source<Double>("time")) // mapping from data source to 'x' coordinate
 *    innerLine.type(LineType.SOLID) // setting of constant line type value
 *    innerPoint {
 *       symbol(Symbol.CIRCLE) // setting of constant symbol value
 *    }
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
 *
 *  Sub-positional:
 *  - [yMin][PointRangeContext.yMin] - lower bound of the error bar
 *  - [yMax][PointRangeContext.yMax] - upper bound of the error bar
 *
 *   Non-positional:
 *  - [alpha][PointRangeContext.alpha] - layer alpha, of the type [Double], mappable
 *  - [color][PointRangeContext.color] - color of the point range, of the type [Color], mappable
 *  - [size][PointRangeContext.size] - width of the line and size of the mid-point, of the type [Double], mappable
 *  - [innerPoint.symbol][InnerPointSubContext.symbol] - symbol of the borderline, of the type [Symbol], mappable.
 *  - [innerPoint.fillColor][InnerPointSubContext.fillColor] - color of the point filling (only for "FILLED" symbols),
 *  of the type [Color], mappable.
 *  - [innerPoint.fatten][InnerPointSubContext.fatten] - a multiplicative factor applied to size
 *  of the middle point, of the type [Double], non-mappable.
 *
 *  // TODO write about inners invocation?
 *  ```
 *  pointRange {
 *     innerLine {
 *        type(LineType.DOTTED)
 *     }
 *     innerPoint {
 *        fillColor(Color.RED)
 *        symbol(Symbol.DIAMOND_FILLED)
 *     }
 *  }
 *  ```
 *
 * // TODO move data overriding to args
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][PointRangeContext.data].
 *
 *  // TODO refer to bindings?
 */
public inline fun PlotContext.pointRange(block: PointRangeContext.() -> Unit) {
    layers.add(
        PointRangeContext(data).apply { copyFrom(this@pointRange) }.apply(block).toLayer(POINT_RANGE)
    )
}
