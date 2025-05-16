package org.jetbrains.kotlinx.kandy.echarts.translator.option.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.SYMBOL
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationLayerFeature
import org.jetbrains.kotlinx.kandy.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol
import org.jetbrains.kotlinx.kandy.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.DataElementListSerializer
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.IntListSerializer
import org.jetbrains.kotlinx.kandy.ir.Layer

internal fun Layer.toPointSeries(name: String?, encode: Encode?, data: List<List<Element?>>?): ScatterSeries {
    val symbol: Symbol? = settings.getNPSValue<Symbol>(SYMBOL)
    val animation: AnimationLayerFeature? = (features[AnimationLayerFeature.FEATURE_NAME] as? AnimationLayerFeature)
    val size = symbol?.getSize()
//    val size: Measurement? = settings.getNPSValue<Double>(SIZE)?.let { singleOf(it) } ?: symbol?.getSize() TODO(fix size)

    return ScatterSeries(
        name = name,
        symbol = symbol?.name,
        symbolSize = size,
        symbolRotate = symbol?.rotate,
        label = features.getLabel(),
        itemStyle = settings.getItemStyle(),
        encode = encode,
        data = data,
        markPoint = features.getEchartsMarkPoint(),
        markLine = features.getEchartsMarkLine(),
        markArea = features.getEchartsMarkArea(),
        animation = animation?.enable,
        animationThreshold = animation?.threshold,
        animationDuration = animation?.duration,
        animationEasing = animation?.easing,
        animationDelay = animation?.delay,
    )
}

@Serializable
@SerialName("scatter")
internal class ScatterSeries(
    override val id: String? = null,
    override val name: String? = null,
    override val colorBy: String? = null,
    override val coordinateSystem: String? = null,
    val xAxisIndex: Int? = null,
    val yAxisIndex: Int? = null,
    val polarIndex: Int? = null,
    val geoIndex: Int? = null,
    val calendarIndex: Int? = null,
    override val legendHoverLink: Boolean? = null,
    val symbol: String? = null,
    @Serializable(with = IntListSerializer::class)
    val symbolSize: List<Int>? = null,
    val symbolRotate: Int? = null,
    val symbolKeepAspect: Boolean? = null,
    val symbolOffset: List<SizeUnit>? = null,
    val large: Boolean? = null,
    val largeThreshold: Int? = null,
    val cursor: String? = null,
    val label: Label? = null,
    val labelLine: LabelLine? = null,
    val labelLayout: LabelLayout? = null,
    override val itemStyle: ItemStyle? = null,
    override val emphasis: Emphasis? = null,
    override val blur: Blur? = null,
    override val select: Select? = null,
    override val selectedMode: String? = null,
    val progressive: Int? = null,
    val progressiveThreshold: Int? = null,
    override val dimensions: List<Dimension>? = null,
    override val encode: Encode? = null,
    val seriesLayoutBy: String? = null,
    val datasetIndex: Int? = null,
    override val dataGroupId: String? = null,
    @Serializable(with = DataElementListSerializer::class)
    override val data: List<List<Element?>>? = null,
    override val markPoint: EchartsMarkPoint? = null,
    override val markLine: EchartsMarkLine? = null,
    override val markArea: EchartsMarkArea? = null,
    val clip: Boolean? = null,
    override val zlevel: Int? = null,
    override val z: Int? = null,
    override val silent: Boolean? = null,
    val animation: Boolean? = null,
    val animationThreshold: Int? = null,
    override val animationDuration: Int? = null,
    override val animationEasing: String? = null,
    override val animationDelay: Int? = null,
    val animationDurationUpdate: Int? = null,
    val animationEasingUpdate: String? = null,
    val animationDelayUpdate: Int? = null,
    override val universalTransition: UniversalTransition? = null,
    override val tooltip: EchartsTooltip? = null
) : Series()