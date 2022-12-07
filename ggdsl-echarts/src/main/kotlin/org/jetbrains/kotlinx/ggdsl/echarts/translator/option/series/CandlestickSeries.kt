package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationLineFeature
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer

internal fun Layer.toCandlestickSeries(name: String?, encode: Encode?): CandlestickSeries {
    val animation = (features[AnimationLineFeature.FEATURE_NAME] as? AnimationLineFeature)

    return CandlestickSeries(
        name = name,
        encode = encode,
        markPoint = features.getEchartsMarkPoint(),
        markLine = features.getEchartsMarkLine(),
        animationDuration = animation?.duration,
        animationEasing = animation?.easing?.name,
        animationDelay = animation?.delay,
    )
}

@Serializable
internal class CandlestickSeries(
    override val type: String = "candlestick",
    override val id: String? = null,
    override val coordinateSystem: CoordinateSystem? = null,
    val xAxisIndex: Int? = null,
    val yAxisIndex: Int? = null,
    override val name: String? = null,
    override val colorBy: String? = null,
    override val legendHoverLink: Boolean? = null,
    val hoverAnimation: Boolean? = null,
    val layout: String? = null,
    val barwidth: Int? = null,
    val barMinwidth: String? = null,
    val barMaxWidth: String? = null,
    override val itemStyle: ItemStyle? = null,
    override val emphasis: Emphasis? = null,
    override val blur: Blur? = null,
    override val select: Select? = null,
    override val selectedMode: String? = null,
    val large: Boolean? = null,
    val largeThreshold: Int? = null,
    val progressive: Int? = null,
    val progressiveThreshold: Int? = null,
    val progressiveChunkMode: String? = null,
    override val dimensions: List<Dimension>? = null,
    override val encode: Encode? = null,
    override val dataGroupId: String? = null,
    override val data: List<List<String>>? = null,
    override val markPoint: EchartsMarkPoint? = null,
    override val markLine: EchartsMarkLine? = null,
    override val markArea: MarkArea? = null,
    val clip: Boolean? = null,
    override val zlevel: Int? = null,
    override val z: Int? = null,
    override val silent: Boolean? = null,
    override val animationDuration: Int? = null,
    override val animationEasing: String? = null,
    override val animationDelay: Int? = null,
    override val universalTransition: UniversalTransition? = null,
    override val tooltip: Tooltip? = null
) : Series()