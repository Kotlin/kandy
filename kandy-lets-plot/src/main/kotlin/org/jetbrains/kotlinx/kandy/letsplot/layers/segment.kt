/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.SegmentContext

@PublishedApi
internal val SEGMENT: LetsPlotGeom = LetsPlotGeom("segment")
/*
/**
 * Adds a new segment layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [xBegin][SegmentContextInterface.xBegin]
 *  - [yBegin][SegmentContextInterface.yBegin]
 *  - [xEnd][SegmentContextInterface.xEnd]
 *  - [yEnd][SegmentContextInterface.yEnd]
 *
 *   Non-positional:
 *  - [color][SegmentContextInterface.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][SegmentContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [lineType][SegmentContextInterface.lineType] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][SegmentContextInterface.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * segment {
 *    xBegin(sTime.scaled(..)) // mapping from `sTime` column to `xBegin` with some scale
 *    lineType(LineType.DOTTED) // setting of constant `type` value
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.segment(block: SegmentContextImmutable.() -> Unit) {
    addLayer(SegmentContextImmutable(this).apply(block), SEGMENT)
}

/**
 * Adds a new segment layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [xBegin][SegmentContextInterface.xBegin]
 *  - [yBegin][SegmentContextInterface.yBegin]
 *  - [xEnd][SegmentContextInterface.xEnd]
 *  - [yEnd][SegmentContextInterface.yEnd]
 *
 *   Non-positional:
 *  - [color][SegmentContextInterface.color] - line color, of the type [Color], mappable. (TODO grouping)
 *  - [alpha][SegmentContextInterface.alpha] - layer alpha, of the type [Double], mappable. (TODO grouping)
 *  - [lineType][SegmentContextInterface.lineType] - line type, of the type [LineType], mappable. (TODO grouping)
 *  - [width][SegmentContextInterface.width] - line width, of the type [Double], mappable. (TODO grouping)
 *
 * ```
 * segment {
 *    xBegin(listOf(1.0, 1.0, 1.5)) // mapping from list to `xBegin`
 *    lineType(LineType.DOTTED) // setting of constant `type` value
 * }
 * ```
 */



 */

public inline fun LayerCollectorContext.segment(block: SegmentContext.() -> Unit) {
    addLayer(SegmentContext(this).apply(block), SEGMENT)
}
