/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val STEP: LetsPlotGeom = LetsPlotGeom("step")


@PlotDslMarker
public class StepContext(parent: LayerCollectorContext) :
    LayerContext(parent) {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val lineType: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)

}

/**
 * Adds a new step layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * step {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][StepContext.x]
 *  - [y][StepContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [LayerPlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][StepContext.color] - this line color, of the type [Color], mappable
 *  - [alpha][StepContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [lineType][StepContext.lineType] - this line type, of the type [LineType], mappable
 *  - [width][StepContext.width] - this line width, of the type [Double], mappable
 *
 *  By default, the dataset inherited from the parent [LayerPlotContext] is used,
 *  but can be overridden with an assignment to the [data][StepContext.data].
 *
 * // TODO move data overriding to args

 *  // TODO refer to bindings?
 */

public inline fun LayerCollectorContext.step(block: StepContext.() -> Unit) {
    addLayer(StepContext(this).apply(block), STEP)
}
