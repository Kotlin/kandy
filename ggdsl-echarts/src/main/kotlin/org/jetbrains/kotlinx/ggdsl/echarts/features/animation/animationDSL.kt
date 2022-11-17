/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.animation

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.echarts.layers.LineContext

/**
 * Animation options settings.
 *
 * - [enable][AnimationFeature.enable] - whether to enable animation.
 * - [threshold][AnimationFeature.threshold] - whether to set a graphic number threshold to animation.
 * Animation will be turned off when a graphic number is larger than threshold.
 * - [duration][AnimationFeature.duration] - the duration of the first animation.
 * - [easing][AnimationFeature.easing] -the easing method used for the first animation.
 * - [delay][AnimationFeature.delay] - the delay before updating the first animation.
 */
public fun LayerPlotContext.animation(block: AnimationPlotFeature.() -> Unit) {
    features[AnimationPlotFeature.FEATURE_NAME] = AnimationPlotFeature().apply(block)
}


public fun LineContext.animation(block: AnimationLineFeature.() -> Unit) {
    features[AnimationLineFeature.FEATURE_NAME] = AnimationLineFeature().apply(block)
}
