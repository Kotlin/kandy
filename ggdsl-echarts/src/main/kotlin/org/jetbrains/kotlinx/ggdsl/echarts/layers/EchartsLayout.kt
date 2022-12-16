/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.Grid
import org.jetbrains.kotlinx.ggdsl.echarts.features.Legend
import org.jetbrains.kotlinx.ggdsl.echarts.features.Title
import org.jetbrains.kotlinx.ggdsl.echarts.features.Tooltip
import org.jetbrains.kotlinx.ggdsl.echarts.features.TextStyle
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

/**
 * Plot layout settings.
 *
 * @property size - plot size.
 * @property title - [title][org.jetbrains.kotlinx.ggdsl.echarts.features.Title] of plot.
 * @property textStyle - [font style][org.jetbrains.kotlinx.ggdsl.echarts.features.TextStyle].
 * @property grid - [grid][org.jetbrains.kotlinx.ggdsl.echarts.features.Grid] settings.
 * @property legend - [legend][org.jetbrains.kotlinx.ggdsl.echarts.features.Legend].
 * @property tooltip - [tooltip][org.jetbrains.kotlinx.ggdsl.echarts.features.Tooltip].
 *
 * @see EChartsLayout
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.Title
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.TextStyle
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.Grid
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.Legend
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.Tooltip
 */
@PlotDslMarker
public class EChartsLayout : PlotFeature {
    public var size: Pair<Int, Int>? = null

    /**
     * @suppress
     */
    override val featureName: FeatureName = FEATURE_NAME

    public var title: Title = Title()

    public var textStyle: TextStyle = TextStyle()

    public var grid: Grid = Grid()

    @PublishedApi
    internal var legend: Legend? = null

    @PublishedApi
    internal var tooltip: Tooltip? = null

    @PublishedApi
    internal companion object {
        val FEATURE_NAME: FeatureName = FeatureName("ECHARTS_LAYOUT")
    }
}