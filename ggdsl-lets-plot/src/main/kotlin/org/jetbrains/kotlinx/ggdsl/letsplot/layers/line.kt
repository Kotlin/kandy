/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

// TODO

@PublishedApi
internal val LINE: LetsPlotGeom = LetsPlotGeom("line")

@PublishedApi
internal val PATH: LetsPlotGeom = LetsPlotGeom("path")


public interface LineContextInterface : BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

@PlotDslMarker
public class LineContext(parent: LayerCollectorContext): LayerContext(parent), LineContextInterface

@PlotDslMarker
public class LineMutableContext(parent: LayerCollectorMutableDataContext):
    LayerMutableDataContext(parent), LineContextInterface
/**
 * Adds a new line layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * line {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineContext.x]
 *  - [y][LineContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][LineContext.color] - this line color, of the type [Color], mappable
 *  - [alpha][LineContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [type][LineContext.type] - this line type, of the type [LineType], mappable
 *  - [width][LineContext.width] - this line width, of the type [Double], mappable
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][LineContext.data].
 *
 * // TODO move data overriding to args

 *  // TODO refer to bindings?
 */

public inline fun LayerCollectorContext.line(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), LINE)
}

public inline fun LayerCollectorContext.path(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), PATH)
}

public inline fun LayerCollectorMutableDataContext.line(block: LineMutableContext.() -> Unit) {
    addLayer(LineMutableContext(this).apply(block), LINE)
}

public inline fun LayerCollectorMutableDataContext.path(block: LineMutableContext.() -> Unit) {
    addLayer(LineMutableContext(this).apply(block), PATH)
}
