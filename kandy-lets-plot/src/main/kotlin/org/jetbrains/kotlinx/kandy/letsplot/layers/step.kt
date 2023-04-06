/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.StepContext

@PublishedApi
internal val STEP: LetsPlotGeom = LetsPlotGeom("step")
/*
public interface StepContextInterface : LayerContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val lineType: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

/*@PlotDslMarker*/
public class StepContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), StepContextInterface

/*@PlotDslMarker*/
public class StepContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), StepContextInterface

/**
 * Adds a new step layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][StepContextInterface.x]
 *  - [ y][StepContextInterface.y]
 *
 *   Non-positional:
 *  - [color][StepContextInterface.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][StepContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [lineType][StepContextInterface.lineType] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][StepContextInterface.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * step {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with some scale
 *    lineType(LineType.DOTTED) // setting of constant `type` value
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.step(block: StepContextImmutable.() -> Unit) {
    addLayer(StepContextImmutable(this).apply(block), STEP)
}

/**
 * Adds a new step layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][StepContextInterface.x]
 *  - [ y][StepContextInterface.y]
 *
 *   Non-positional:
 *  - [color][StepContextInterface.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][StepContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [lineType][StepContextInterface.lineType] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][StepContextInterface.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * step {
 *    x(listOf(4.2f, 5.5f, 8.9f).scaled(..)) // mapping from list to `X` with some scale
 *    lineType(LineType.DOTTED) // setting of constant `type` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.step(block: StepContextMutable.() -> Unit) {
    addLayer(StepContextMutable(this).apply(block), STEP)
}


 */

public inline fun LayerCollectorContext.step(block: StepContext.() -> Unit) {
    addLayer(StepContext(this).apply(block), STEP)
}