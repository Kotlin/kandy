package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*
import org.jetbrains.kotlinx.ggdsl.echarts.aes.ALPHA
import org.jetbrains.kotlinx.ggdsl.echarts.aes.COLOR
import org.jetbrains.kotlinx.ggdsl.echarts.aes.LINE_ALPHA
import org.jetbrains.kotlinx.ggdsl.echarts.aes.SIZE
import org.jetbrains.kotlinx.ggdsl.echarts.aes.SYMBOL
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.TextStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.RangeSerializer
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.util.color.Color


// TODO(move to pallets and theme settings)
private val colors = listOf("#5470c6", "#91cc75", "#fac858", "#ee6666", "#73c0de", "#3ba272", "#fc8452", "#9a60b4", "#ea7ccc")
private val sizes = listOf(20.0, 28.0, 36.0, 44.0, 52.0, 60.0, 68.0)
private val alphas = listOf(0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5)
private val symbols = listOf("circle", "rect", "triangle", "diamond", "roundRect", "pin", "arrow")


internal fun createInRange(aes: AesName, valuesString: List<Any>?, size: Int = -1, isContinuous: Boolean = false): Range {
    return when (aes) {
        COLOR ->
            Range(color = valuesString?.map { (it as Color).toEchartsColor() } ?: List(size) { BaseColor(colors[it]) })
        SIZE -> Range(symbolSize = valuesString ?: if (isContinuous) listOf(20.0, 60.0) else sizes.take(size))
        ALPHA, LINE_ALPHA, AREA_ALPHA -> Range(colorAlpha = valuesString ?: if (isContinuous) listOf(20.0, 60.0) else alphas.take(size))
        SYMBOL -> Range(symbol = valuesString ?: symbols.take(size))
        else -> TODO()
    }
}

@Serializable(with = RangeSerializer::class)
public data class Range(
    val symbol: List<Any>? = null,
    val symbolSize: List<Any>? = null,
    val color: List<EchartsColor>? = null,
    val colorAlpha: List<Any>? = null,
    val opacity: List<Double>? = null,
    val colorLightness: List<Double>? = null,
    val colorSaturation: List<Double>? = null,
    val colorHue: List<Double>? = null
)

@Serializable
public data class Piece(
    val min: Int? = null,
    val max: Int? = null,
    val label: String? = null,
    val value: String? = null,
    val color: EchartsColor? = null
)


@Serializable
public data class Controller(val inRange: Range? = null, val outOfRange: Range? = null)

@Serializable
public data class VMStyle(
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
public sealed interface VisualMap

@Serializable
@SerialName("continuous")
public data class ContinuousVisualMap(
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
    val textStyle: TextStyle? = null,
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
public data class PiecewiseVisualMap(
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
    val textStyle: TextStyle? = null,
    val formatter: String? = null
) : VisualMap