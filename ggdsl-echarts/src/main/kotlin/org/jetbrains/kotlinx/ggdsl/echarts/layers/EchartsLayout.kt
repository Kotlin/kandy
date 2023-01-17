/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.Grid
import org.jetbrains.kotlinx.ggdsl.echarts.features.Legend
import org.jetbrains.kotlinx.ggdsl.echarts.features.title.Title
import org.jetbrains.kotlinx.ggdsl.echarts.features.Tooltip
import org.jetbrains.kotlinx.ggdsl.echarts.features.text.TextStyle
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.Animation
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationEasing
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

/**
 * Plot layout settings.
 *
 * @property size - plot size.
 * @property title - [title][Title] of plot.
 * @property textStyle - [font style][TextStyle].
 * @property grid - [grid][Grid] settings.
 * @property animation - [animation][Animation] of plot.
 * @property legend - [legend][Legend].
 * @property tooltip - [tooltip][Tooltip].
 *
 * @see EChartsLayout
 * @see Title
 * @see TextStyle
 * @see Grid
 * @see Legend
 * @see Tooltip
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

    /**
     * Animation options settings for [Plot][org.jetbrains.kotlinx.ggdsl.ir.Plot].
     * If a property isn't set or set to null, a default value will be used.
     *
     * * [enable][Animation.enable] - responsible for enabling animation.
     * By default `true`.
     * * [threshold][Animation.threshold] - sets a graphic number threshold for animation.
     * Animation will be disabled when graphic number exceeds a threshold.
     * By default `2000`.
     * * [duration][Animation.duration] - duration of the first animation.
     * By default `1000`.
     * * [easing][Animation.easing] - [easing effect][AnimationEasing] used for the first animation.
     * By default `cubicOut`.
     * * [delay][Animation.delay] - delay before updating the first animation.
     * By default `0`.
     *
     * ```kotlin
     * plot {
     *  animation {
     *      enable = true
     *      threshold = 2000
     *      duration = 1000
     *      easing = AnimationEasing.CUBIC_OUT
     *      delay = 0
     *  }
     * }
     * ```
     *
     * @see LayerPlotContext
     * @see AnimationEasing
     */
    public var animation: Animation = Animation()

    @PublishedApi
    internal var legend: Legend? = null

    @PublishedApi
    internal var tooltip: Tooltip? = null

    @PublishedApi
    internal companion object {
        val FEATURE_NAME: FeatureName = FeatureName("ECHARTS_LAYOUT")
    }
}