/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.context

// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.echarts.features.animation.Animation
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationEasing
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.*
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMappingCont
import org.jetbrains.kotlinx.kandy.echarts.settings.AreaPosition
import org.jetbrains.kotlinx.kandy.echarts.settings.Cap
import org.jetbrains.kotlinx.kandy.echarts.settings.LineType
import org.jetbrains.kotlinx.kandy.echarts.settings.Step
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.reflect.KProperty

/**
 * Area settings.
 *
 * @property x mapping data on the x-axis.
 * @property y mapping data on the y-axis.
 * @property color area fill [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * @property position origin [position][org.jetbrains.kotlinx.kandy.echarts.settings.AreaPosition] of area. `auto` by default.
 * @property shadowBlur shadow blur size of the area.
 * @property shadowColor shadow [color][org.jetbrains.kotlinx.kandy.util.color.Color] of area.
 * @property alpha opacity of area.
 * @property lineColor line [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * @property symbol [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] on line. Symbols are not shown by default.
 * @property smooth smooth curve. `false` by default.
 * @property lineAlpha line opacity.
 * @property lineWidth line width. `2` by default.
 * @property lineType [line type][org.jetbrains.kotlinx.kandy.echarts.settings.LineType]. `solid` by default.
 * @property step step line. `false` by default.
 * @property cap [end points][org.jetbrains.kotlinx.kandy.echarts.settings.Cap] of line. `butt` by default.
 * @property lineShadowColor shadow color of line.
 * @property lineShadowBlur shadow blur size of line.
 * @property animation [animation][Animation]
 *
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.area
 * @see org.jetbrains.kotlinx.kandy.util.color.Color
 * @see org.jetbrains.kotlinx.kandy.echarts.settings.AreaPosition
 * @see org.jetbrains.kotlinx.kandy.echarts.settings.Symbol
 * @see org.jetbrains.kotlinx.kandy.echarts.settings.Cap
 * @see org.jetbrains.kotlinx.kandy.echarts.settings.LineType
 * @see Animation
 */
/*@PlotDslMarker*/
public class AreaContext(parent: LayerCollectorContext) : EchartsLayerContext(parent), WithX, WithY, WithColor,
    WithAlpha, WithSymbol {
    public var position: AreaPosition? = null
        set(value) {
            addNonPositionalSetting(AREA_POSITION, value)
            field = value
        }
    public var shadowBlur: Number? = null
        set(value) {
            addNonPositionalSetting(AREA_SHADOW_BLUR, value)
            field = value
        }
    public var shadowColor: Color? = null
        set(value) {
            addNonPositionalSetting(AREA_SHADOW_COLOR, value)
            field = value
        }

    public var lineColor: Color? = null
        set(value) {
            addNonPositionalSetting(LINE_COLOR, value)
            field = value
        }
    public var smooth: Boolean? = null
        set(value) {
            addNonPositionalSetting(SMOOTH, value)
            field = value
        }
    public var lineAlpha: Number? = null
        set(value) {
            addNonPositionalSetting(LINE_ALPHA, value)
            field = value
        }
    public var lineWidth: Number? = null
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
    public var lineShadowColor: Color? = null
        set(value) {
            addNonPositionalSetting(LINE_SHADOW_COLOR, value)
            field = value
        }
    public var lineShadowBlur: Number? = null
        set(value) {
            addNonPositionalSetting(LINE_SHADOW_BLUR, value)
            field = value
        }

    public fun <T> lineAlpha(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMappingCont(LINE_ALPHA, column, params)

    public fun <T> lineAlpha(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMappingCont(LINE_ALPHA, column, params)

    public fun <T> lineAlpha(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMappingCont(LINE_ALPHA, values, name, params)

    public fun lineAlpha(
        column: String, params: EchartsNonPositionalMappingParameters<*, Double>.() -> Unit = {}
    ): NonPositionalMapping<*, Double> = nonPosMappingCont(LINE_ALPHA, column, params)

    public fun <T> lineAlpha(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMappingCont(LINE_ALPHA, values, params)

    public fun <T> lineColor(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = nonPosMappingCont(LINE_COLOR, column, params)

    public fun <T> lineColor(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = nonPosMappingCont(LINE_COLOR, column, params)

    public fun lineColor(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = nonPosMappingCont(LINE_COLOR, column, params)

    public fun <T> lineColor(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = nonPosMappingCont(LINE_COLOR, values, name, params)

    public fun <T> lineColor(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = nonPosMappingCont(LINE_COLOR, values, params)


    /**
     * Animation options settings for [area][org.jetbrains.kotlinx.kandy.echarts.layers.area].
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
     * @see AnimationEasing
     */
    public val animation: Animation = Animation()
}