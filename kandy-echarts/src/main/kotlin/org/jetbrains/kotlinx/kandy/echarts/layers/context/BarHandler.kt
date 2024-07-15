/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.echarts.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.echarts.features.animation.Animation
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationEasing
import org.jetbrains.kotlinx.kandy.echarts.layers.BAR
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.WithAlpha
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.WithColor
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.WithX
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.WithY
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

/**
 * Bar settings.
 *
 * @property x mapping data on the x-axis.
 * @property y mapping data on the y-axis.
 * @property color bars [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * @property alpha bars opacity.
 * @property animation [animation][Animation]
 *
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.bars
 * @see org.jetbrains.kotlinx.kandy.util.color.Color
 * @see Animation
 */
public interface BarBuilderInterface : WithX, WithY, WithColor, WithAlpha

public class BarHandler(parent: LayerCreatorScope) : EchartsLayerBuilder(parent), BarBuilderInterface {
    override val geom: Geom
        get() = BAR

    /**
     * Animation options settings for [bars][org.jetbrains.kotlinx.kandy.echarts.layers.bars].
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
     * @see AnimationEasing
     */
    public val animation: Animation = Animation()
}