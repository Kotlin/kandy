/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.context

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.*
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMapping
import org.jetbrains.kotlinx.kandy.echarts.settings.LineType
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext
import kotlin.reflect.KProperty


/**
 * Sets background style for [bars][org.jetbrains.kotlinx.kandy.echarts.layers.bars].
 *
 * - [color][BackgroundStyle.color] - background [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [borderColor][BackgroundStyle.borderColor] -
 * background border [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [borderWidth][BackgroundStyle.borderWidth] - background border width.
 * By default `0`.
 * - [borderType][BackgroundStyle.borderType] - border [type][LineType].
 * By default `solid`.
 * - [borderRadius][BackgroundStyle.borderRadius] - background border radius.
 * By default `0`.
 * - [shadowBlur][BackgroundStyle.shadowBlur] - background shadow blur.
 * - [shadowColor][BackgroundStyle.shadowColor] -
 * background shadow [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [alpha][BackgroundStyle.alpha] - background opacity.
 *
 * ```kotlin
 * plot {
 *     bars {
 *         background {
 *             color = Color.GREY
 *             borderColor = Color.BLACK
 *             borderWidth = 1.0
 *             borderType = LineType.DASHED
 *             borderRadius = 1.3
 *             shadowBlur = 10.0
 *             shadowColor = Color.GREEN
 *             alpha = 0.7
 *         }
 *     }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.bars
 */
public inline fun BarContext.background(crossinline block: BackgroundStyle.() -> Unit) {
    BackgroundStyle(this).apply(block)
}

/**
 * Background style settings. All properties of this class are aesthetics.
 *
 * @property color background [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * @property borderColor background border [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * @property borderWidth background border width. By default `0`.
 * @property borderType border [type][LineType]. By default `solid`.
 * @property borderRadius background border radius. By default `0`.
 * @property shadowBlur background shadow blur.
 * @property shadowColor background shadow [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * @property alpha background opacity.
 *
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.bars
 */
/*@PlotDslMarker*/
// TODO(add border setting)
public class BackgroundStyle(private val context: BindingContext) : SelfInvocationContext {

    /* BACKGROUND COLOR*/
    public var color: Color? = null
        set(value) {
            context.addNonPositionalSetting(BACKGROUND_COLOR, value)
        }

    public fun <T> color(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_COLOR, column, params)

    public fun <T> color(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_COLOR, column, params)

    public fun color(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = context.nonPosMapping(BACKGROUND_COLOR, column, params)

    public fun <T> color(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_COLOR, values, name, params)

    public fun <T> color(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_COLOR, values, params)

    /* BACKGROUND BORDER COLOR*/
    public var borderColor: Color? = null
        set(value) {
            context.addNonPositionalSetting(BACKGROUND_BORDER_COLOR, value)
        }

    public fun <T> borderColor(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_BORDER_COLOR, column, params)

    public fun <T> borderColor(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_BORDER_COLOR, column, params)

    public fun borderColor(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = context.nonPosMapping(BACKGROUND_BORDER_COLOR, column, params)

    public fun <T> borderColor(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_BORDER_COLOR, values, name, params)

    public fun <T> borderColor(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_BORDER_COLOR, values, params)

    /* BACKGROUND BORDER WIDTH*/
    public var borderWidth: Color? = null
        set(value) {
            context.addNonPositionalSetting(BACKGROUND_BORDER_WIDTH, value)
        }

    public fun <T> borderWidth(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_BORDER_WIDTH, column, params)

    public fun <T> borderWidth(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_BORDER_WIDTH, column, params)

    public fun borderWidth(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = context.nonPosMapping(BACKGROUND_BORDER_WIDTH, column, params)

    public fun <T> borderWidth(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_BORDER_WIDTH, values, name, params)

    public fun <T> borderWidth(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_BORDER_WIDTH, values, params)

    /* BACKGROUND BORDER TYPE*/
    public var borderType: LineType? = null
        set(value) {
            context.addNonPositionalSetting(BACKGROUND_BORDER_TYPE, value)
        }

    public fun <T> borderType(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMapping(BACKGROUND_BORDER_TYPE, column, params)

    public fun <T> borderType(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMapping(BACKGROUND_BORDER_TYPE, column, params)

    public fun borderType(
        column: String, params: EchartsNonPositionalMappingParameters<*, LineType>.() -> Unit = {}
    ): NonPositionalMapping<*, LineType> = context.nonPosMapping(BACKGROUND_BORDER_TYPE, column, params)

    public fun <T> borderType(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMapping(BACKGROUND_BORDER_TYPE, values, name, params)

    public fun <T> borderType(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMapping(BACKGROUND_BORDER_TYPE, values, params)

    /* BACKGROUND BORDER RADIUS*/
    public var borderRadius: Number? = null
        set(value) {
            context.addNonPositionalSetting(BACKGROUND_BORDER_RADIUS, value)
        }

    public fun <T> borderRadius(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_BORDER_RADIUS, column, params)

    public fun <T> borderRadius(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_BORDER_RADIUS, column, params)

    public fun borderRadius(
        column: String, params: EchartsNonPositionalMappingParameters<*, Number>.() -> Unit = {}
    ): NonPositionalMapping<*, Number> = context.nonPosMapping(BACKGROUND_BORDER_RADIUS, column, params)

    public fun <T> borderRadius(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_BORDER_RADIUS, values, name, params)

    public fun <T> borderRadius(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_BORDER_RADIUS, values, params)


    /* BACKGROUND BORDER SHADOW BLUR*/
    public var shadowBlur: Number? = null
        set(value) {
            context.addNonPositionalSetting(BACKGROUND_SHADOW_BLUR, value)
        }

    public fun <T> shadowBlur(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_SHADOW_BLUR, column, params)

    public fun <T> shadowBlur(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_SHADOW_BLUR, column, params)

    public fun shadowBlur(
        column: String, params: EchartsNonPositionalMappingParameters<*, Number>.() -> Unit = {}
    ): NonPositionalMapping<*, Number> = context.nonPosMapping(BACKGROUND_SHADOW_BLUR, column, params)

    public fun <T> shadowBlur(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_SHADOW_BLUR, values, name, params)

    public fun <T> shadowBlur(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_SHADOW_BLUR, values, params)

    /* BACKGROUND BORDER SHADOW COLOR*/
    public var shadowColor: Color? = null
        set(value) {
            context.addNonPositionalSetting(BACKGROUND_SHADOW_COLOR, value)
        }

    public fun <T> shadowColor(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_SHADOW_COLOR, column, params)

    public fun <T> shadowColor(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_SHADOW_COLOR, column, params)

    public fun shadowColor(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = context.nonPosMapping(BACKGROUND_SHADOW_COLOR, column, params)

    public fun <T> shadowColor(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_SHADOW_COLOR, values, name, params)

    public fun <T> shadowColor(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BACKGROUND_SHADOW_COLOR, values, params)

    /* BACKGROUND BORDER ALPHA*/
    public var alpha: Number? = null
        set(value) {
            context.addNonPositionalSetting(BACKGROUND_ALPHA, value)
        }

    public fun <T> alpha(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_ALPHA, column, params)

    public fun <T> alpha(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_ALPHA, column, params)

    public fun alpha(
        column: String, params: EchartsNonPositionalMappingParameters<*, Number>.() -> Unit = {}
    ): NonPositionalMapping<*, Number> = context.nonPosMapping(BACKGROUND_ALPHA, column, params)

    public fun <T> alpha(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_ALPHA, values, name, params)

    public fun <T> alpha(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BACKGROUND_ALPHA, values, params)
}
