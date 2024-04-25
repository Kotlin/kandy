/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.echarts.features.animation.Animation
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationEasing
import org.jetbrains.kotlinx.kandy.echarts.layers.LINE
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.*
import org.jetbrains.kotlinx.kandy.echarts.settings.Cap
import org.jetbrains.kotlinx.kandy.echarts.settings.LineType
import org.jetbrains.kotlinx.kandy.echarts.settings.Step
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.util.color.Color

/**
 * Line settings.
 *
 * @property x mapping data on the x-axis.
 * @property y mapping data on the y-axis.
 * @property color line [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * @property symbol [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] on line.
 * Symbols aren't shown by default.
 * @property smooth smooth curve. `false` by default.
 * @property alpha line opacity.
 * @property width line width. `2` by default.
 * @property lineType [line type][org.jetbrains.kotlinx.kandy.echarts.settings.LineType]. `solid` by default.
 * @property step step line. `false` by default.
 * @property cap [end points][org.jetbrains.kotlinx.kandy.echarts.settings.Cap] of line. `butt` by default.
 * @property shadowColor shadow color of line.
 * @property shadowBlur shadow blur size of line.
 * @property animation [animation][Animation] of line.
 *
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.line
 * @see org.jetbrains.kotlinx.kandy.util.color.Color
 * @see org.jetbrains.kotlinx.kandy.echarts.settings.Symbol
 * @see org.jetbrains.kotlinx.kandy.echarts.settings.LineType
 * @see org.jetbrains.kotlinx.kandy.echarts.settings.Cap
 * @see Animation
 */
public class LineHandler(parent: LayerCreatorScope) : EchartsLayerContext(parent), WithX, WithY, WithColor,
    WithAlpha, WithSymbol {

    override val geom: Geom
        get() = LINE

    public var smooth: Boolean? = null
        set(value) {
            addNonPositionalSetting(SMOOTH, value)
            field = smooth
        }
    public var width: Number? = null
        set(value) {
            addNonPositionalSetting(WIDTH, value)
            field = value
        }

    public var lineType: LineType? = null
        set(value) {
            addNonPositionalSetting(LINE_TYPE, value)
            field = value
        }

    public var step: Step? = null
        set(value) {
            addNonPositionalSetting(STEP, value)
            field = value
        }

    public var cap: Cap? = null
        set(value) {
            addNonPositionalSetting(CAP, value)
            field = value
        }
    public var shadowColor: Color? = null
        set(value) {
            addNonPositionalSetting(LINE_SHADOW_COLOR, value)
            field = value
        }
    public var shadowBlur: Number? = null
        set(value) {
            addNonPositionalSetting(LINE_SHADOW_BLUR, value)
            field = value
        }

    /**
     * Animation options settings for [line][org.jetbrains.kotlinx.kandy.echarts.layers.line].
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
     * @see AnimationEasing
     */
    public val animation: Animation = Animation()
}