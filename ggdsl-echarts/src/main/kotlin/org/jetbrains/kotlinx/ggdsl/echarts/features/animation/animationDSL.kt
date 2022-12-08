/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.animation

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.echarts.layers.*

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
public fun LayerPlotContext.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationPlotFeature()?.let {
        features[AnimationPlotFeature.FEATURE_NAME] = it
    }
}

public fun LineContextImmutable.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationLayerFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

public fun AreaContextImmutable.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationLayerFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

public fun BarContextImmutable.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationLayerFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

public fun PointContextImmutable.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationLayerFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

public fun PieContextImmutable.animation(block: AnimationPieContext.() -> Unit) {
    AnimationPieContext().apply(block).toAnimationFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

public fun BoxplotContextImmutable.animation(block: AnimationBoxplotCandlestickContext.() -> Unit) {
    AnimationBoxplotCandlestickContext().apply(block).toAnimationFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

public fun CandlestickContextImmutable.animation(block: AnimationBoxplotCandlestickContext.() -> Unit) {
    AnimationBoxplotCandlestickContext().apply(block).toAnimationFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}
