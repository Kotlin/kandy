/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom


@PublishedApi
internal val POINT_RANGE: LetsPlotGeom = LetsPlotGeom("pointrange")
/*
/**
 * Adds a new point ranges layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointRangeContextInterface.x]
 *  - [y][PointRangeContextInterface.y]
 *  - [yMin][PointRangeContextInterface.yMin] - lower bound for range.
 *  - [yMax][PointRangeContextInterface.yMax] - upper bound for range.
 *
 *
 *   Non-positional:
 *  - [alpha][PointRangeContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [color][PointRangeContextInterface.color] - line range color, of the type [LineType], mappable.
 *  - [size][PointRangeContextInterface.size] - line range size, of the type [Double], mappable.
 *  - [innerPoint.fatten][InnerPointSubContextInterface.fatten] - multiplicative factor applied to size
 *  of the middle point of type [Double], non-mappable.
 *  - [innerPoint.fillColor][InnerPointSubContextInterface.fillColor] - inner point fill color (for "FILLED" shapes)
 *  of type [Color], mappable.
 *  - [innerPoint.symbol][InnerPointSubContextInterface.symbol] - inner point symbol of the type [Symbol],
 *  mappable.
 *  - [innerLine.type][InnerLineSubContextInterface.type] -line type of the type [LineType], mappable.
 *  (TODO grouping)
 * ```
 * pointRange {
 *    x(time.scaled(..)) // mapping from `time` column to `X` with an explicit scale
 *    yMin(minSpread) // mapping from `minSpread` column to `yMin`
 *
 *    innerPoint {
 *       fillColor(Color.GREEN) // // setting of constant `innerPoint.fillColor` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.pointRange(block: PointRangeContextImmutable.() -> Unit) {
    addLayer(PointRangeContextImmutable(this).apply(block), POINT_RANGE)
}

/**
 * Adds a new point ranges layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointRangeContextInterface.x]
 *  - [y][PointRangeContextInterface.y]
 *  - [yMin][PointRangeContextInterface.yMin] - lower bound for range.
 *  - [yMax][PointRangeContextInterface.yMax] - upper bound for range.
 *
 *
 *   Non-positional:
 *  - [alpha][PointRangeContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [color][PointRangeContextInterface.color] - line range color, of the type [LineType], mappable.
 *  - [size][PointRangeContextInterface.size] - line range size, of the type [Double], mappable.
 *  - [innerPoint.fatten][InnerPointSubContextInterface.fatten] - multiplicative factor applied to size
 *  of the middle point of type [Double], non-mappable.
 *  - [innerPoint.fillColor][InnerPointSubContextInterface.fillColor] - inner point fill color (for "FILLED" shapes)
 *  of type [Color], mappable.
 *  - [innerPoint.symbol][InnerPointSubContextInterface.symbol] - inner point symbol of the type [Symbol],
 *  mappable.
 *  - [innerLine.type][InnerLineSubContextInterface.type] -line type of the type [LineType], mappable.
 *  (TODO grouping)
 * ```
 * pointRange {
 *    x(listOf("A", "B").scaled(..)) // mapping from list to `X` with an explicit scale
 *    yMin(listOf(2, 5)) // mapping from list to `yMin`
 *
 *    innerPoint {
 *       fillColor(Color.GREEN) // // setting of constant `innerPoint.fillColor` value
 *    }
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.pointRange(block: PointRangeContextMutable.() -> Unit) {
    addLayer(PointRangeContextMutable(this).apply(block), POINT_RANGE)
}


 */