/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.EchartsTextStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.RangeSerializer
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue

internal fun createInRange(aes: AesName, valuesString: List<Any?>?): Range? {
    if (valuesString.isNullOrEmpty()) return null
    return when (aes) {
        COLOR, LINE_COLOR, AREA_COLOR -> Range(color = valuesString.map { ((it as TypedValue).value as Color).toEchartsColor() })
        SIZE -> Range(symbolSize = valuesString)
        ALPHA, LINE_ALPHA, AREA_ALPHA -> Range(colorAlpha = valuesString)
        SYMBOL -> Range(symbol = valuesString)
        else -> TODO()
    }
}

@Serializable(with = RangeSerializer::class)
internal data class Range(
    val symbol: List<Any?>? = null,
    val symbolSize: List<Any?>? = null,
    val color: List<EchartsColor>? = null,
    val colorAlpha: List<Any?>? = null,
    val opacity: List<Double>? = null,
    val colorLightness: List<Double>? = null,
    val colorSaturation: List<Double>? = null,
    val colorHue: List<Double>? = null
)

@Serializable
internal data class Piece(
    val min: Int? = null,
    val max: Int? = null,
    val label: String? = null,
    val value: String? = null,
    val color: EchartsColor? = null
)


@Serializable
internal data class Controller(val inRange: Range? = null, val outOfRange: Range? = null)

@Serializable
internal data class VMStyle(
    val color: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Int? = null,
    val borderType: String? = null,
    val borderDashOffset: Int? = null,
    val borderCap: String? = null,
    val borderJoin: String? = null,
    val borderMiterLimit: Int? = null,
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Double? = null
)

@Serializable
internal sealed interface VisualMap

@Serializable
@SerialName("continuous")
internal data class ContinuousVisualMap(
    val id: String? = null,
    val min: Double? = null,
    val max: Double? = null,
    val range: List<Int>? = null,
    val calculable: Boolean? = null,
    val realtime: Boolean? = null,
    val inverse: Boolean? = null,
    val precision: Double? = null,
    val itemWidth: Int? = null,
    val itemHeight: Int? = null,
    val align: String? = null,
    val text: List<String>? = null,
    val textGap: Int? = null,
    val show: Boolean? = null,
    val dimension: String? = null,
    val seriesIndex: Int? = null,
    val hoverLink: Boolean? = null,
    val inRange: Range? = null,
    val outOfRange: Range? = null,
    val controller: Controller? = null,
    val zlevel: Int? = null,
    val z: Int? = null,
    val left: Int? = null,
    val top: Int? = null,
    val right: Int? = null,
    val bottom: Int? = null,
    val orient: String? = null,
    val padding: Int? = null, // todo Padding
    val backgroundColor: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Int? = null,
    val color: List<EchartsColor>? = null,
    val textStyle: EchartsTextStyle? = null,
    val formatter: String? = null,
    val handleIcon: String? = null,
    val handleSize: String? = null,
    val handleStyle: VMStyle? = null,
    val indicatorIcon: String? = null,
    val indicatorSize: String? = null,
    val indicatorStyle: VMStyle? = null
) : VisualMap

@Serializable
@SerialName("piecewise")
internal data class PiecewiseVisualMap(
    val id: String? = null,
    val splitNumber: Int? = null,
    val pieces: List<Piece>? = null,
    val categories: List<String>? = null,
    val min: Double? = null,
    val max: Double? = null,
    val minOpen: Boolean? = null,
    val maxOpen: Boolean? = null,
    val selectedMode: String? = null,
    val inverse: Boolean? = null,
    val precision: Double? = null,
    val itemWidth: Int? = null,
    val itemHeight: Int? = null,
    val align: String? = null,
    val text: List<String>? = null,
    val textGap: Int? = null,
    val showLabel: Boolean? = null,
    val itemGap: Int? = null,
    val itemSymbol: String? = null,
    val show: Boolean? = null,
    val dimension: String? = null,
    val seriesIndex: Int? = null,
    val hoverLink: Boolean? = null,
    val inRange: Range? = null,
    val outOfRange: Range? = null,
    val controller: Controller? = null,
    val zlevel: Int? = null,
    val z: Int? = null,
    val left: Int? = null,
    val top: Int? = null,
    val right: Int? = null,
    val bottom: Int? = null,
    val orient: String? = null,
    val padding: Int? = null,
    val backgroundColor: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Int? = null,
    val color: List<EchartsColor>? = null,
    val textStyle: EchartsTextStyle? = null,
    val formatter: String? = null
) : VisualMap