/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val POINT: LetsPlotGeom = LetsPlotGeom("point")

public interface PointContextInterface : BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val symbol: ShapeAes get() = ShapeAes(this)

    public val size: SizeAes get() = SizeAes(this)
    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)

    // FILL SHAPES only
    public val borderWidth: StrokeAes  // TODO doesnt work lol
        get() = StrokeAes(this)
    public val fillColor: FillAes
        get() = FillAes(this)
}

@PlotDslMarker
public class PointContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerContextImmutable(parent), PointContextInterface

@PlotDslMarker
public class PointMutableMutableContextMutable(parent: LayerCollectorContextMutable) :
    LayerContextMutable(parent), PointContextInterface


/**
 * Adds a new point layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * point {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointContextImmutable.x]
 *  - [y][PointContextImmutable.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [LayerPlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][PointContextImmutable.color] - color of the point (color of the point border for "FILLED" symbols), of the type [Color], mappable
 *  - [alpha][PointContextImmutable.alpha] - this layer alpha, of the type [Double], mappable
 *  - [symbol][PointContextImmutable.symbol] - symbol of the point, of the type [Symbol], mappable
 *  - [size][PointContextImmutable.size] - this point size, of the type [Double], mappable
 *  - [fillColor][PointContextImmutable.fillColor] - color of the point filling (for "FILLED" symbols), of the type [Color], mappable
 *  - [borderWidth][PointContextImmutable.borderWidth] - width of the point border (for "FILLED" symbols), of the type [Double], mappable
 *
 * // TODO move data overriding to args
 *  By default, the dataset inherited from the parent [LayerPlotContext] is used,
 *  but can be overridden with an assignment to the [data][PointContextImmutable.data].
 *
 *  // TODO refer to bindings?
 */
// todo rename to point/scatter?
public inline fun LayerCollectorContextImmutable.point(block: PointContextImmutable.() -> Unit) {
    addLayer(PointContextImmutable(this).apply(block), POINT)
}

public inline fun LayerCollectorContextMutable.point(block: PointMutableMutableContextMutable.() -> Unit) {
    addLayer(PointMutableMutableContextMutable(this).apply(block), POINT)
}


