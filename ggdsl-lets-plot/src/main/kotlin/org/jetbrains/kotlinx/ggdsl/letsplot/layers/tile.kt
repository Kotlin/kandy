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
internal val TILE: LetsPlotGeom = LetsPlotGeom("tile")


@PlotDslMarker

public class TileContext(parent: LayerCollectorContext) :
    WithBorderLineContext(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)


    public val color: FillAes = FillAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val width: WidthPosAes = WidthPosAes(this)
    public val height: HeightPosAes = HeightPosAes(this)

}

/**
 * Adds a new tile layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * tile {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    color(Color.BLUE) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointRangeContext.x]
 *  - [y][PointRangeContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *  Sub-positional:
 *
 *  - [width][TileContext.width]
 *  - [height][TileContext.height]
 *
 *   Non-positional:
 *  - [color][TileContext.color] - color of the tile filling, of the type [Color], mappable
 *  - [alpha][TileContext.alpha] - this layer alpha, of the type [Double], mappable
 *  - [borderLine.color][BorderLineSubContext.color] - color of the borderline, of the type [Color], mappable.
 *  - [borderLine.width][BorderLineSubContext.width] - width of the borderline, of the type [Double], mappable.
 *  - [borderLine.type][BorderLineSubContext.type] - type of the borderline, of the type [LineType], mappable.

 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][TileContext.data].
 *
 * // TODO move data overriding to args
 *  ```
 *  tile {
 *     borderLine {
 *        color(Color.RED)
 *        type(LineType.DOTTED)
 *     }
 *  }
 *  ```
 *  // TODO refer to bindings?
 */
public
inline
fun LayerCollectorContext.tile(block: TileContext.() -> Unit) {
    addLayer(TileContext(this).apply(block), TILE)
}
