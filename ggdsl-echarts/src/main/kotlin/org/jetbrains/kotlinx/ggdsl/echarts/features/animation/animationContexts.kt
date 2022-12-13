/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.animation

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker

@PlotDslMarker
public class AnimationContext(
    public var enable: Boolean? = null,
    public var threshold: Int? = null,
    public var duration: Int? = null,
    public var easing: AnimationEasing? = null,
    public var delay: Int? = null
) {

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

public enum class AnimationType(public val type: String) {
    EXPANSION("expansion"), SCALE("scale")
}

@PlotDslMarker
public class AnimationPieContext(
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

@PlotDslMarker
public class AnimationBoxplotCandlestickContext(
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