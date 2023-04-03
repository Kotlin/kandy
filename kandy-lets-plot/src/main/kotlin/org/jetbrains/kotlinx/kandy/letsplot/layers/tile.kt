/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.kandy.letsplot.layers

// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.TileContext

@PublishedApi
internal val TILE: LetsPlotGeom = LetsPlotGeom("tile")
/*
public interface TileContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val width: WidthPosAes get() = WidthPosAes(this)
    public val height: HeightPosAes get() = HeightPosAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

/*@PlotDslMarker*/
public class TileContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), TileContextInterface

/*@PlotDslMarker*/
public class TileContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), TileContextInterface

/**
 * Adds a new tile layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][TileContextInterface.x]
 *  - [y][TileContextInterface.y]
 *  - [width][TileContextInterface.width]
 *  - [height][TileContextInterface.height]
 *
 *   Non-positional:
 *  - [color][TileContextInterface.color] - tile fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][TileContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * tile {
 *    x(time) // mapping from `time` column to `X` with the default scale
 *    borderLine {
 *       color(model.scaled(..)) // mapping from `model` column to `borderLine.color` with an explicit scale
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.tile(block: TileContextImmutable.() -> Unit) {
    addLayer(TileContextImmutable(this).apply(block), TILE)
}

/**
 * Adds a new tile layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][TileContextInterface.x]
 *  - [y][TileContextInterface.y]
 *  - [width][TileContextInterface.width]
 *  - [height][TileContextInterface.height]
 *
 *   Non-positional:
 *  - [color][TileContextInterface.color] - tile fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][TileContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * tile {
 *    x(listOf(1.0, 1.0, 1.5).scaled(..)) // mapping from `time` column to `X` with the default scale
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.tile(block: TileContextMutable.() -> Unit) {
    addLayer(TileContextMutable(this).apply(block), TILE)
}


 */

public inline fun LayerCollectorContext.tile(block: TileContext.() -> Unit) {
    addLayer(TileContext(this).apply(block), TILE)
}