/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.echarts.features.marks

import org.jetbrains.kotlinx.kandy.echarts.layers.context.EchartsLayerBuilder

/**
 * Sets mark points on a layer.
 *
 * - [points][MarkPointContext.points] - list of [mark point][MarkPoint]
 *
 * ```kotlin
 * plot {
 *  line {
 *      markPoint {
 *          points = listOf(MarkPoint("avg", MarkType.AVERAGE), MarkPoint(coord = 1.5 to 2.0, value = "2"))
 *      }
 *  }
 * }
 * ```
 *
 * @see MarkPoint
 */
public fun EchartsLayerBuilder.markPoint(block: MarkPointContext.() -> Unit) {
    layerFeatures[MarkPointFeature.FEATURE_NAME] = MarkPointContext().apply(block).toMarkPointFeature()
}

/**
 * Sets mark lines on a layer.
 *
 * - [points][MarkLineContext.lines] - list of [mark line][MarkLine]
 *
 * ```kotlin
 * plot {
 *  line {
 *      markLine {
 *          lines = listOf(
 *              MarkLine("max", MarkType.MAX),
 *              MarkLine("two points", MarkPoint(MarkType.MIN), MarkPoint(5, 3))
 *              )
 *      }
 *  }
 * }
 * ```
 *
 * @see MarkLine
 */
public fun EchartsLayerBuilder.markLine(block: MarkLineContext.() -> Unit) {
    layerFeatures[MarkLineFeature.FEATURE_NAME] = MarkLineContext().apply(block).toMarkLineFeature()
}

/**
 * Sets mark areas on a layer.
 *
 * - [points][MarkAreaContext.areas] - list of [mark area][MarkArea]
 *
 * ```kotlin
 * plot {
 *  line {
 *      markArea {
 *          areas = listOf(MarkArea("area", MarkPoint(MarkType.MIN), MarkPoint(MarkType.MAX)))
 *      }
 *  }
 * }
 * ```
 *
 * @see MarkArea
 */
public fun EchartsLayerBuilder.markArea(block: MarkAreaContext.() -> Unit) {
    layerFeatures[MarkAreaFeature.FEATURE_NAME] = MarkAreaContext().apply(block).toMarkAreaFeature()
}
