@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.echarts.layers.context

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationBoxplotCandlestick
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationEasing
import org.jetbrains.kotlinx.kandy.echarts.layers.CANDLESTICK
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.*
import org.jetbrains.kotlinx.kandy.echarts.scale.*
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.reflect.KProperty

public class CandlestickHandler(parent: LayerCreatorScope) : EchartsLayerBuilder(parent), WithAes {
    override val geom: Geom
        get() = CANDLESTICK

    public fun <T> y(
        open: ColumnReference<T>, close: ColumnReference<T>, low: ColumnReference<T>, high: ColumnReference<T>,
        params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ) {
        bindingHandler.posMapping(Y_OPEN, open, params)
        bindingHandler.posMapping(Y_CLOSE, close, params)
        bindingHandler.posMapping(Y_LOW, low, params)
        bindingHandler.posMapping(Y_HIGH, high, params)
    }

    public fun <T> y(
        open: KProperty<T>, close: KProperty<T>, low: KProperty<T>, high: KProperty<T>,
        params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ) {

        bindingHandler.posMapping(Y_OPEN, open, params)
        bindingHandler.posMapping(Y_CLOSE, close, params)
        bindingHandler.posMapping(Y_LOW, low, params)
        bindingHandler.posMapping(Y_HIGH, high, params)
    }

    public fun y(
        open: String, close: String, low: String, high: String,
        params: EchartsPositionalMappingParametersContinuous<*>.() -> Unit = {}
    ) {
        bindingHandler.posMapping(Y_OPEN, open, params)
        bindingHandler.posMapping(Y_CLOSE, close, params)
        bindingHandler.posMapping(Y_LOW, low, params)
        bindingHandler.posMapping(Y_HIGH, high, params)
    }

    public fun <T> y(
        open: DataColumn<T>, close: DataColumn<T>, low: DataColumn<T>, high: DataColumn<T>,
        params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ) {
        bindingHandler.posMapping(Y_OPEN, open, params)
        bindingHandler.posMapping(Y_CLOSE, close, params)
        bindingHandler.posMapping(Y_LOW, low, params)
        bindingHandler.posMapping(Y_HIGH, high, params)
    }

    public fun <T> x(
        values: DataColumn<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(X, values, params)

    public fun <T> x(
        values: KProperty<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(X, values, params)

    public fun <T> x(
        values: Iterable<T>,
        name: String? = null,
        params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(X, values, name, params)

    public fun x(
        column: String,
        params: EchartsPositionalMappingParametersContinuous<*>.() -> Unit = {}
    ): PositionalMapping<*> =
        bindingHandler.posMapping(X, column, params)

    public fun <T> x(
        column: ColumnReference<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(X, column, params)

    public var upColor: Color? = null
        set(value) {
            bindingHandler.addNonPositionalSetting(COLOR, value)
            field = value
        }

    public fun <T> upColor(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(COLOR, column, params)

    public fun <T> upColor(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(COLOR, column, params)

    public fun upColor(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = bindingHandler.nonPosMappingCat(COLOR, column, params)

    public fun <T> upColor(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(COLOR, values, name, params)

    public fun <T> upColor(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(COLOR, values, params)

    public var downColor: Color? = null
        set(value) {
            bindingHandler.addNonPositionalSetting(DOWN_COLOR, value)
            field = value
        }

    public fun <T> downColor(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(DOWN_COLOR, column, params)

    public fun <T> downColor(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(DOWN_COLOR, column, params)

    public fun downColor(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = bindingHandler.nonPosMappingCat(DOWN_COLOR, column, params)

    public fun <T> downColor(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(DOWN_COLOR, values, name, params)

    public fun <T> downColor(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(DOWN_COLOR, values, params)

    public var upBorderColor: Color? = null
        set(value) {
            bindingHandler.addNonPositionalSetting(BORDER_COLOR, value)
            field = value
        }

    public fun <T> upBorderColor(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(BORDER_COLOR, column, params)

    public fun <T> upBorderColor(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(BORDER_COLOR, column, params)

    public fun upBorderColor(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = bindingHandler.nonPosMappingCat(BORDER_COLOR, column, params)

    public fun <T> upBorderColor(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(BORDER_COLOR, values, name, params)

    public fun <T> upBorderColor(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(BORDER_COLOR, values, params)

    public var downBorderColor: Color? = null
        set(value) {
            bindingHandler.addNonPositionalSetting(DOWN_BORDER_COLOR, value)
            field = value
        }

    public fun <T> downBorderColor(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(DOWN_BORDER_COLOR, column, params)

    public fun <T> downBorderColor(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(DOWN_BORDER_COLOR, column, params)

    public fun downBorderColor(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = bindingHandler.nonPosMappingCat(DOWN_BORDER_COLOR, column, params)

    public fun <T> downBorderColor(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(DOWN_BORDER_COLOR, values, name, params)

    public fun <T> downBorderColor(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = bindingHandler.nonPosMappingCat(DOWN_BORDER_COLOR, values, params)

    /**
     * Animation options settings for [candlestick][org.jetbrains.kotlinx.kandy.echarts.layers.candlestick].
     * If a property isn't set or set to null, a default value will be used.
     *
     * * [duration][AnimationBoxplotCandlestick.duration] - duration of the first animation.
     * By default `1000`.
     * * [easing][AnimationBoxplotCandlestick.easing] -
     * [easing effect][AnimationEasing] used for the first animation.
     * By default `cubicOut`.
     * * [delay][AnimationBoxplotCandlestick.delay] - delay before updating the first animation.
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
     * @see AnimationEasing
     */
    public val animation: AnimationBoxplotCandlestick = AnimationBoxplotCandlestick()
}