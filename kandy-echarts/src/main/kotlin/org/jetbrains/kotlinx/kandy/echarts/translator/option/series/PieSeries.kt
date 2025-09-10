package org.jetbrains.kotlinx.kandy.echarts.translator.option.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationLayerFeature
import org.jetbrains.kotlinx.kandy.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.ir.Layer

internal fun Layer.toPieSeries(name: String?, encode: Encode?, data: List<Map<String, Element?>>?): PieSeries {
    val animation = (features[AnimationLayerFeature.FEATURE_NAME] as? AnimationLayerFeature)

    return PieSeries(
        name = name,
        label = features.getLabel(),
        itemStyle = settings.getItemStyle(),
        encode = encode,
        data = data,
        markPoint = features.getEchartsMarkPoint(),
        markLine = features.getEchartsMarkLine(),
        markArea = features.getEchartsMarkArea(),
        animationType = animation?.type,
        animation = animation?.enable,
        animationThreshold = animation?.threshold,
        animationDuration = animation?.duration,
        animationEasing = animation?.easing,
        animationDelay = animation?.delay,
    )
}

@Serializable
@SerialName("pie")
internal class PieSeries(
    override val id: String? = null,
    override val name: String? = null,
    override val colorBy: String? = null,
    override val legendHoverLink: Boolean? = null,
    override val coordinateSystem: String? = null,
    val geoIndex: Int? = null,
    val calendarIndex: Int? = null,
    override val selectedMode: String? = null,
    val selectedOffset: Int? = null,
    val clockwise: Boolean? = null,
    val startAngle: Int? = null,
    val minAngle: Int? = null,
    val minShowLabelAngle: Int? = null,
    val roseType: String? = null,
    val avoidLabelOverlap: Boolean? = null,
    val stillShowZeroSum: Boolean? = null,
    val percentPrecision: Int? = null,
    val cursor: String? = null,
    override val zlevel: Int? = null,
    override val z: Int? = null,
    val left: SizeUnit? = null,
    val top: SizeUnit? = null,
    val right: SizeUnit? = null,
    val bottom: SizeUnit? = null,
    val width: SizeUnit? = null,
    val height: SizeUnit? = null,
    val showEmptyCircle: Boolean? = null,
    val emtyCircleStyle: EmptyCircleStyle? = null,
    val label: Label? = null,
    val labelLine: LabelLine? = null,
    val labelLayout: LabelLayout? = null,
    override val itemStyle: ItemStyle? = null,
    override val emphasis: Emphasis? = null,
    override val blur: Blur? = null,
    override val select: Select? = null,
    val center: List<SizeUnit>? = null,
    val radius: List<SizeUnit>? = null,
    val seriesLayoutBy: String? = null,
    val datasetIndex: Int? = null,
    override val dimensions: List<Dimension>? = null,
    override val encode: Encode? = null,
    override val dataGroupId: String? = null,
    override val data: List<Map<String, Element?>>? = null,
    override val markPoint: EchartsMarkPoint? = null,
    override val markLine: EchartsMarkLine? = null,
    override val markArea: EchartsMarkArea? = null,
    override val silent: Boolean? = null,
    val animationType: String? = null,
    val animationTypeUpdate: String? = null,
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