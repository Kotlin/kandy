/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.animation

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.echarts.layers.*

/**
 * Animation options settings for [Plot][org.jetbrains.kotlinx.ggdsl.ir.Plot].
 * If a property isn't set or set to null, a default value will be used.
 *
 * * [enable][AnimationContext.enable] - responsible for enabling animation.
 * By default `true`.
 * * [threshold][AnimationContext.threshold] - sets a graphic number threshold for animation.
 * Animation will be disabled when graphic number exceeds a threshold.
 * By default `2000`.
 * * [duration][AnimationContext.duration] - duration of the first animation.
 * By default `1000`.
 * * [easing][AnimationContext.easing] - [easing effect][AnimationEasing] used for the first animation.
 * By default `cubicOut`.
 * * [delay][AnimationContext.delay] - delay before updating the first animation.
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
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see LayerPlotContext
 * @see AnimationContext
 * @see AnimationEasing
 */
public fun LayerPlotContext.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationPlotFeature()?.let {
        features[AnimationPlotFeature.FEATURE_NAME] = it
    }
}

/**
 * Animation options settings for [line][line].
 * If a property isn't set or set to null, a default value will be used.
 *
 * * [enable][AnimationContext.enable] - responsible for enabling animation.
 * By default `true`.
 * * [threshold][AnimationContext.threshold] - sets a graphic number threshold for animation.
 * Animation will be disabled when graphic number exceeds a threshold.
 * By default `2000`.
 * * [duration][AnimationContext.duration] - duration of the first animation.
 * By default `1000`.
 * * [easing][AnimationContext.easing] - [easing effect][AnimationEasing] used for the first animation.
 * By default `cubicOut`.
 * * [delay][AnimationContext.delay] - delay before updating the first animation.
 * By default `0`.
 *
 * ```kotlin
 * plot {
 *  line {
 *      animation {
 *          enable = true
 *          threshold = 2000
 *          duration = 1000
 *          easing = AnimationEasing.CUBIC_OUT
 *          delay = 0
 *      }
 *  }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see line
 * @see LineContextImmutable
 * @see AnimationContext
 * @see AnimationEasing
 */
public fun LineContextImmutable.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationLayerFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

/**
 * Animation options settings for [area][area].
 * If a property isn't set or set to null, a default value will be used.
 *
 * * [enable][AnimationContext.enable] - responsible for enabling animation.
 * By default `true`.
 * * [threshold][AnimationContext.threshold] - sets a graphic number threshold for animation.
 * Animation will be disabled when graphic number exceeds a threshold.
 * By default `2000`.
 * * [duration][AnimationContext.duration] - duration of the first animation.
 * By default `1000`.
 * * [easing][AnimationContext.easing] - [easing effect][AnimationEasing] used for the first animation.
 * By default `cubicOut`.
 * * [delay][AnimationContext.delay] - delay before updating the first animation.
 * By default `0`.
 *
 * ```kotlin
 * plot {
 *  area {
 *      animation {
 *          enable = true
 *          threshold = 2000
 *          duration = 1000
 *          easing = AnimationEasing.CUBIC_OUT
 *          delay = 0
 *      }
 *  }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see area
 * @see AreaContextImmutable
 * @see AnimationContext
 * @see AnimationEasing
 */
public fun AreaContextImmutable.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationLayerFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

/**
 * Animation options settings for [bars][bars].
 * If a property isn't set or set to null, a default value will be used.
 *
 * * [enable][AnimationContext.enable] - responsible for enabling animation.
 * By default `true`.
 * * [threshold][AnimationContext.threshold] - sets a graphic number threshold for animation.
 * Animation will be disabled when graphic number exceeds a threshold.
 * By default `2000`.
 * * [duration][AnimationContext.duration] - duration of the first animation.
 * By default `1000`.
 * * [easing][AnimationContext.easing] - [easing effect][AnimationEasing] used for the first animation.
 * By default `cubicOut`.
 * * [delay][AnimationContext.delay] - delay before updating the first animation.
 * By default `0`.
 *
 * ```kotlin
 * plot {
 *  bars {
 *      animation {
 *          enable = true
 *          threshold = 2000
 *          duration = 1000
 *          easing = AnimationEasing.CUBIC_OUT
 *          delay = 0
 *      }
 *  }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see bars
 * @see BarContextImmutable
 * @see AnimationContext
 * @see AnimationEasing
 */
public fun BarContextImmutable.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationLayerFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

/**
 * Animation options settings for [points][points].
 * If a property isn't set or set to null, a default value will be used.
 *
 * * [enable][AnimationContext.enable] - responsible for enabling animation.
 * By default `true`.
 * * [threshold][AnimationContext.threshold] - sets a graphic number threshold for animation.
 * Animation will be disabled when graphic number exceeds a threshold.
 * By default `2000`.
 * * [duration][AnimationContext.duration] - duration of the first animation.
 * By default `1000`.
 * * [easing][AnimationContext.easing] - [easing effect][AnimationEasing] used for the first animation.
 * By default `cubicOut`.
 * * [delay][AnimationContext.delay] - delay before updating the first animation.
 * By default `0`.
 *
 * ```kotlin
 * plot {
 *  points {
 *      animation {
 *          enable = true
 *          threshold = 2000
 *          duration = 1000
 *          easing = AnimationEasing.CUBIC_OUT
 *          delay = 0
 *      }
 *  }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see points
 * @see PointContextImmutable
 * @see AnimationContext
 * @see AnimationEasing
 */
public fun PointContextImmutable.animation(block: AnimationContext.() -> Unit) {
    AnimationContext().apply(block).toAnimationLayerFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

/**
 * Animation options settings for [pie][pie].
 * If a property isn't set or set to null, a default value will be used.
 *
 * * [enable][AnimationPieContext.enable] - responsible for enabling animation.
 * By default `true`.
 * * [type][AnimationPieContext.type] - initial [animation type][AnimationType].
 * By default `expansion`.
 * * [threshold][AnimationPieContext.threshold] - sets a graphic number threshold for animation.
 * Animation will be disabled when graphic number exceeds a threshold.
 * By default `2000`.
 * * [duration][AnimationPieContext.duration] - duration of the first animation.
 * By default `1000`.
 * * [easing][AnimationPieContext.easing] - [easing effect][AnimationEasing] used for the first animation.
 * By default `cubicOut`.
 * * [delay][AnimationPieContext.delay] - delay before updating the first animation.
 * By default `0`.
 *
 * ```kotlin
 * plot {
 *  pie {
 *      animation {
 *          enable = true
 *          type = AnimationType.EXPANSION
 *          threshold = 2000
 *          duration = 1000
 *          easing = AnimationEasing.CUBIC_OUT
 *          delay = 0
 *      }
 *  }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see pie
 * @see PieContextImmutable
 * @see AnimationPieContext
 * @see AnimationEasing
 */
public fun PieContextImmutable.animation(block: AnimationPieContext.() -> Unit) {
    AnimationPieContext().apply(block).toAnimationFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

/**
 * Animation options settings for [boxplot][boxplot].
 * If a property isn't set or set to null, a default value will be used.
 *
 * * [duration][AnimationBoxplotCandlestickContext.duration] - duration of the first animation.
 * By default `1000`.
 * * [easing][AnimationBoxplotCandlestickContext.easing] -
 * [easing effect][AnimationEasing] used for the first animation.
 * By default `cubicOut`.
 * * [delay][AnimationBoxplotCandlestickContext.delay] - delay before updating the first animation.
 * By default `0`.
 *
 * ```kotlin
 * plot {
 *  boxplot {
 *      animation {
 *          duration = 1000
 *          easing = AnimationEasing.CUBIC_OUT
 *          delay = 0
 *      }
 *  }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see boxplot
 * @see BoxplotContextImmutable
 * @see AnimationBoxplotCandlestickContext
 * @see AnimationEasing
 */
public fun BoxplotContextImmutable.animation(block: AnimationBoxplotCandlestickContext.() -> Unit) {
    AnimationBoxplotCandlestickContext().apply(block).toAnimationFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}

/**
 * Animation options settings for [candlestick][candlestick].
 * If a property isn't set or set to null, a default value will be used.
 *
 * * [duration][AnimationBoxplotCandlestickContext.duration] - duration of the first animation.
 * By default `1000`.
 * * [easing][AnimationBoxplotCandlestickContext.easing] -
 * [easing effect][AnimationEasing] used for the first animation.
 * By default `cubicOut`.
 * * [delay][AnimationBoxplotCandlestickContext.delay] - delay before updating the first animation.
 * By default `0`.
 *
 * ```kotlin
 * plot {
 *  candlestick {
 *      animation {
 *          duration = 1000
 *          easing = AnimationEasing.CUBIC_OUT
 *          delay = 0
 *      }
 *  }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.ir.Plot
 * @see candlestick
 * @see CandlestickContextImmutable
 * @see AnimationBoxplotCandlestickContext
 * @see AnimationEasing
 */
public fun CandlestickContextImmutable.animation(block: AnimationBoxplotCandlestickContext.() -> Unit) {
    AnimationBoxplotCandlestickContext().apply(block).toAnimationFeature()?.let {
        features[AnimationLayerFeature.FEATURE_NAME] = it
    }
}
