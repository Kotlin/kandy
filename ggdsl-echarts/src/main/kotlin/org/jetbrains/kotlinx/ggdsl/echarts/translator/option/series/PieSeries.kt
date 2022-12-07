package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationLineFeature
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer

internal fun Layer.toPieSeries(name: String?, encode: Encode?): PieSeries {
    val animation = (features[AnimationLineFeature.FEATURE_NAME] as? AnimationLineFeature)

    return PieSeries(
        name = name,
        encode = encode,
        markPoint = features.getEchartsMarkPoint(),
        markLine = features.getEchartsMarkLine(),
        animation = animation?.enable,
        animationThreshold = animation?.threshold,
        animationDuration = animation?.duration,
        animationEasing = animation?.easing?.name,
        animationDelay = animation?.delay,
    )
}

@Serializable
internal class PieSeries(
    override val type: String = "pie",
    override val id: String? = null,
    override val name: String? = null,
    override val colorBy: String? = null,
    override val legendHoverLink: Boolean? = null,
    override val coordinateSystem: CoordinateSystem? = null,
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
    val left: String? = null,
    val top: String? = null,
    val right: String? = null,
    val bottom: String? = null,
    val width: String? = null,
    val height: String? = null,
    val showEmptyCircle: Boolean? = null,
    val emtyCircleStyle: EmptyCircleStyle? = null,
    val label: Label? = null,
    val labelLine: LabelLine? = null,
    val labelLayout: LabelLayout? = null,
    override val itemStyle: ItemStyle? = null,
    override val emphasis: Emphasis? = null,
    override val blur: Blur? = null,
    override val select: Select? = null,
    val center: Pair<String, String>? = null,
    val radius: Pair<String, String>? = null,
    val seriesLayoutBy: String? = null,
    val datasetIndex: Int? = null,
    override val dimensions: List<Dimension>? = null,
    override val encode: Encode? = null,
    override val dataGroupId: String? = null,
    override val data: List<List<String>>? = null,
    override val markPoint: EchartsMarkPoint? = null,
    override val markLine: EchartsMarkLine? = null,
    override val markArea: MarkArea? = null,
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
    override val tooltip: Tooltip? = null
) : Series()