package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

val POINT_RANGE = LetsPlotGeom("pointrange")

class InnerFilledPointSubContext : BindingContext() {
    override var data: MutableNamedData = mutableMapOf()
    val symbol = FILLED_SYMBOL
    val color = FILL
    val fatten = FATTEN
}

class InnerUnfilledPointSubContext : BindingContext() {
    override var data: MutableNamedData = mutableMapOf()
    val symbol = UNFILLED_SYMBOL
    val fatten = FATTEN
}

class InnerLineSubContext : BindingContext() {
    override var data: MutableNamedData = mutableMapOf()
    val color = COLOR
    val type = LINE_TYPE
    val width = SIZE // TODO mappable???
}

class FilledPointRangeContext(override var data: MutableNamedData) : LayerContext() {
    val yMin = Y_MIN
    val yMax = Y_MAX

    val alpha = ALPHA
    val color = FILL

    // todo separate????
    val size = SIZE

    val innerPoint = InnerFilledPointSubContext()

    inline operator fun InnerFilledPointSubContext.invoke(block: InnerFilledPointSubContext.() -> Unit) {
        apply(block)
        this@FilledPointRangeContext.copyFrom(this, false)
    }

    val innerLine = InnerLineSubContext()

    inline operator fun InnerLineSubContext.invoke(block: InnerLineSubContext.() -> Unit) {
        apply(block)
        this@FilledPointRangeContext.copyFrom(this, false)
    }
}

class UnfilledPointRangeContext(override var data: MutableNamedData) : LayerContext() {
    val yMin = Y_MIN
    val yMax = Y_MAX

    val alpha = ALPHA
    val color = FILL

    // todo separate????
    val size = SIZE

    val innerPoint = InnerUnfilledPointSubContext()

    inline operator fun InnerUnfilledPointSubContext.invoke(block: InnerUnfilledPointSubContext.() -> Unit) {
        apply(block)
        this@UnfilledPointRangeContext.copyFrom(this, false)
    }

    val innerLine = InnerLineSubContext()

    inline operator fun InnerLineSubContext.invoke(block: InnerLineSubContext.() -> Unit) {
        apply(block)
        this@UnfilledPointRangeContext.copyFrom(this, false)
    }
}

/**
 * Adds a new line range layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * boxplot {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    borderColor(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointRangeContext.x]
 *  - [y][PointRangeContext.y] // TODO - move to another geom???
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *  - [yMin][PointRangeContext.yMin]
 *  - [yMax][PointRangeContext.yMax]
 *
 *   Non-positional:
 *  - [pointColor][PointRangeContext.pointColor] - this point color, of the type [Color], mappable.
 *  - [symbol][PointRangeContext.symbol] - this point symbol, of the type [Color], mappable.
 *  - [alpha][PointRangeContext.alpha] - this point range alpha, of the type [Double], mappable.
 *  - [size][PointRangeContext.size] - this point size, of the type [Double], mappable.
 *  - [fatten][PointRangeContext.fatten] - this line fatten, of the type [Double], non-mappable.
 *  - [lineType][PointRangeContext.lineType] - this line type, of the type [LineType], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][PointRangeContext.data].
 *
 *  @see [BaseBindingContext]
 */
fun PlotContext.filledPointRange(block: FilledPointRangeContext.() -> Unit) {
    layers.add(
        FilledPointRangeContext(data).apply { copyFrom(this@filledPointRange) }.apply(block).toLayer(POINT_RANGE)
    )
}
