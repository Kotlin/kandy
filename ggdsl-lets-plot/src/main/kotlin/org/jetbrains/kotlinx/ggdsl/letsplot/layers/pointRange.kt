package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val POINT_RANGE = LetsPlotGeom("pointrange")
@PlotDslMarker
class InnerPointSubContext(parentContext: BindingContext) : SubContext(parentContext) {
    override var data: MutableNamedData = mutableMapOf()
    val symbol = ShapeAes(parentContext)
    val fillColor = FillAes(parentContext)
    val fatten = FattenAes(parentContext)
}
@PlotDslMarker
class InnerLineSubContext(parentContext: BindingContext) : SubContext(parentContext) {
    override var data: MutableNamedData = mutableMapOf()
 //   val color = ColorAes(parentContext)
    val type = LineTypeAes(parentContext)
    //val width = SIZE // TODO mappable???
}

@PlotDslMarker
class PointRangeContext(override var data: MutableNamedData) : LayerContext() {
    val x = XAes(this)
    val y = YAes(this)
    val yMin = YMinAes(this)
    val yMax = YMaxAes(this)

    val alpha = AlphaAes(this)
    val color = ColorAes(this)

    // todo separate????
    val size = SizeAes(this)

    val innerPoint = InnerPointSubContext(this)

    inline operator fun InnerPointSubContext.invoke(block: InnerPointSubContext.() -> Unit) {
        apply(block)
    }

    val innerLine = InnerLineSubContext(this)

    inline operator fun InnerLineSubContext.invoke(block: InnerLineSubContext.() -> Unit) {
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
inline fun PlotContext.pointRange(block: PointRangeContext.() -> Unit) {
    layers.add(
        PointRangeContext(data).apply { copyFrom(this@pointRange) }.apply(block).toLayer(POINT_RANGE)
    )
}
