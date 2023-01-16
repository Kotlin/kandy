/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.animation

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationType.EXPANSION
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationType.SCALE
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

/**
 * Animation settings for [Plot][org.jetbrains.kotlinx.ggdsl.ir.Plot] and layers.
 *
 * @property enable responsible for enabling animation. By default `true`.
 * @property threshold sets a graphic number threshold for animation.
 * Animation will be disabled when graphic number exceeds a threshold.
 * By default `2000`.
 * @property duration duration of the first animation. By default `1000`.
 * @property easing [easing effect][AnimationEasing] used for the first animation. By default `cubicOut`.
 * @property delay delay before updating the first animation. By default `0`.
 *
 * @see AnimationEasing
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.AreaContextImmutable
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.LineContextImmutable
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.PointContextImmutable
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
 */
@PlotDslMarker
public class Animation(
    public var enable: Boolean? = null,
    public var threshold: Int? = null,
    public var duration: Int? = null,
    public var easing: AnimationEasing? = null,
    public var delay: Int? = null
) : SelfInvocationContext {
    internal fun isEmpty(): Boolean =
        enable == null && threshold == null && duration == null && easing == null && delay == null

    internal fun isNotEmpty(): Boolean = !isEmpty()

    internal fun toAnimationPlotFeature(): AnimationPlotFeature? =
        if (isNotEmpty())
            AnimationPlotFeature(enable, threshold, duration, easing?.name, delay)
        else
            null

    internal fun toAnimationLayerFeature(): AnimationLayerFeature? =
        if (isNotEmpty())
            AnimationLayerFeature(
                enable, threshold = threshold, duration = duration, easing = easing?.name, delay = delay
            )
        else
            null
}

/**
 * Animation type used in pie plot.
 *
 * @property EXPANSION Default expansion animation.
 * @property SCALE scale animation. Can use it with `easing = AnimationEasing.CUBIC_OUT` to have popup effect.
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.pie
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.animation.animation
 * @see AnimationPie
 */
public enum class AnimationType(public val type: String) {
    EXPANSION("expansion"), SCALE("scale")
}

/**
 * Animation settings for [Pie][org.jetbrains.kotlinx.ggdsl.echarts.layers.PieContextImmutable] plot.
 *
 * @property enable responsible for enabling animation. By default `true`.
 * @property type initial [animation type][AnimationType]. By default `expansion`.
 * @property threshold sets a graphic number threshold for animation.
 * Animation will be disabled when graphic number exceeds a threshold.
 * By default `2000`.
 * @property duration Duration of the first animation. By default `1000`.
 * @property easing [easing effect][AnimationEasing] used for the first animation. By default `cubicOut`.
 * @property delay delay before updating the first animation. By default `0`.
 *
 * @see AnimationType
 * @see AnimationEasing
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.PieContextImmutable
 */
@PlotDslMarker
public class AnimationPie(
    public var enable: Boolean? = null,
    public var type: AnimationType? = null,
    public var threshold: Int? = null,
    public var duration: Int? = null,
    public var easing: AnimationEasing? = null,
    public var delay: Int? = null
) {

    internal fun isEmpty(): Boolean =
        enable == null && type == null && threshold == null && duration == null && easing == null && delay == null

    internal fun isNotEmpty(): Boolean = !isEmpty()

    internal fun toAnimationFeature(): AnimationLayerFeature? =
        if (isNotEmpty())
            AnimationLayerFeature(enable, type?.type, threshold, duration, easing?.name, delay)
        else
            null
}

/**
 * Animation settings for [Boxplot][org.jetbrains.kotlinx.ggdsl.echarts.layers.BoxplotContextImmutable]
 * and [Candlestick][org.jetbrains.kotlinx.ggdsl.echarts.layers.CandlestickContextImmutable].
 *
 * @property duration Duration of the first animation. By default `1000`.
 * @property easing [easing effect][AnimationEasing] used for the first animation. By default `cubicOut`.
 * @property delay delay before updating the first animation. By default `0`.
 *
 * @see AnimationEasing
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.BoxplotContextImmutable
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.CandlestickContextImmutable
 */
@PlotDslMarker
public class AnimationBoxplotCandlestick(
    public var duration: Int? = null,
    public var easing: AnimationEasing? = null,
    public var delay: Int? = null
) {
    internal fun isEmpty(): Boolean =
        duration == null && easing == null && delay == null

    internal fun isNotEmpty(): Boolean = !isEmpty()

    internal fun toAnimationFeature(): AnimationLayerFeature? =
        if (isNotEmpty())
            AnimationLayerFeature(duration = duration, easing = easing?.name, delay = delay)
        else
            null
}