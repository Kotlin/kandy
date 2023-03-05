/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom

@PublishedApi
internal val RASTER: LetsPlotGeom = LetsPlotGeom("raster")
/*
/**
 * Adds a new raster layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][RasterContextInterface.x]
 *  - [y][RasterContextInterface.y]
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][RasterContextInterface.color] - raster fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][RasterContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * raster {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with an explicit scale
 *    y(midSpread) // mapping from `midSpread` column to `y`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.raster(block: RasterContextImmutable.() -> Unit) {
    addLayer(RasterContextImmutable(this).apply(block), RASTER)
}

/**
 * Adds a new raster layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][RasterContextInterface.x]
 *  - [y][RasterContextInterface.y]
 *
 *  // TODO y
 *
 *   Non-positional:
 *  - [color][RasterContextInterface.color] - raster fill color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][RasterContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [borderLine.type][BorderLineContextImmutable.type] - borderline type, of the type [LineType], mappable.
 *  (TODO grouping)
 *  - [borderLine.width][BorderLineContextImmutable.width] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 *  - [borderLine.color][BorderLineContextImmutable.color] - borderline width, of the type [Double], mappable.
 *  (TODO grouping)
 * ```
 * raster {
 *    x(listOf(3, 4, 5).scaled(..)) // mapping from list to `X` with an explicit scale
 *    y(listOf(20, 24, 25)) // mapping from list to `y`
 *
 *    borderLine {
 *       width(2.5) // // setting of constant `borderLine.width` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.raster(block: RasterContextMutable.() -> Unit) {
    addLayer(RasterContextMutable(this).apply(block), RASTER)
}



 */