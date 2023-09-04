/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features.marks

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature

/**
 * The mark area settings for layers.
 *
 * @property areas list of [mark area][MarkArea]
 *
 * @see MarkArea
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.line
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.area
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.bars
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.points
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.pie
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.boxplot
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.candlestick
 */
public class MarkAreaContext(
    public var areas: List<MarkArea>? = null
) {
    internal fun toMarkAreaFeature(): MarkAreaFeature =
        MarkAreaFeature(areas)
}

internal class MarkAreaFeature(
    var areas: List<MarkArea>? = null
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME: FeatureName = FeatureName("MARK_AREA_FEATURE")
    }
}

/**
 * The mark area. Describes the area mark on a plot.
 *
 * @param name name of the mark area
 * @param point1 the first point defining the mark area
 * @param point2 the second point defining the mark area
 *
 * @see MarkPoint
 */
public class MarkArea constructor(
    public val name: String,
    public val point1: MarkPoint,
    public val point2: MarkPoint
)