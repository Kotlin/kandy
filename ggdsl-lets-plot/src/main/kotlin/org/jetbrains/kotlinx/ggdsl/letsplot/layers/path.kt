package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.LineContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.LineContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.LineContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val PATH: LetsPlotGeom = LetsPlotGeom("path")

/**
 * Adds a new path layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineContextInterface.x]
 *  - [ y][LineContextInterface.y]
 *
 *   Non-positional:
 *  - [color][LineContextInterface.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][LineContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][LineContextInterface.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][LineContextInterface.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * line {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with some scale
 *    type(LineType.DOTTED) // setting of constant `type` value
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.path(block: LineContextImmutable.() -> Unit) {
    addLayer(LineContextImmutable(this).apply(block), PATH)
}

/**
 * Adds a new path layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineContextInterface.x]
 *  - [ y][LineContextInterface.y]
 *
 *   Non-positional:
 *  - [color][LineContextInterface.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][LineContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [type][LineContextInterface.type] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][LineContextInterface.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * line {
 *    x(listOf(4.2f, 5.5f, 8.9f).scaled(..)) // mapping from list to `X` with some scale
 *    type(LineType.DOTTED) // setting of constant `type` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.path(block: LineContextMutable.() -> Unit) {
    addLayer(LineContextMutable(this).apply(block), PATH)
}
