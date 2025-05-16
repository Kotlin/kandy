package org.jetbrains.kotlinx.kandy.echarts.translator.option.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationLayerFeature
import org.jetbrains.kotlinx.kandy.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.DataElementListSerializer
import org.jetbrains.kotlinx.kandy.ir.Layer

internal fun Layer.toBoxplotSeries(name: String?, encode: Encode?, data: List<List<Element?>>?): BoxplotSeries {
    val animation = (features[AnimationLayerFeature.FEATURE_NAME] as? AnimationLayerFeature)

    return BoxplotSeries(
        name = name,
        itemStyle = settings.getItemStyle(),
        encode = encode,
        data = data,
        markPoint = features.getEchartsMarkPoint(),
        markLine = features.getEchartsMarkLine(),
        markArea = features.getEchartsMarkArea(),
        animationDuration = animation?.duration,
        animationEasing = animation?.easing,
        animationDelay = animation?.delay,
    )
}

@Serializable
@SerialName("boxplot")
internal class BoxplotSeries(
    override val id: String? = null,
    override val coordinateSystem: String? = null,
    val xAxisIndex: Int? = null,
    val yAxisIndex: Int? = null,
    override val name: String? = null,
    override val colorBy: String? = null,
    override val legendHoverLink: Boolean? = null,
    val hoverAnimation: Boolean? = null,
    val layout: String? = null,
    val boxWidth: List<SizeUnit>? = null,
    override val itemStyle: ItemStyle? = null,
    override val emphasis: Emphasis? = null,
    override val blur: Blur? = null,
    override val select: Select? = null,
    override val selectedMode: String? = null,
    override val dimensions: List<Dimension>? = null,
    override val encode: Encode? = null,
    override val dataGroupId: String? = null,
    @Serializable(with = DataElementListSerializer::class)
    override val data: List<List<Element?>>? = null,
    override val markPoint: EchartsMarkPoint? = null,
    override val markLine: EchartsMarkLine? = null,
    override val markArea: EchartsMarkArea? = null,
    override val zlevel: Int? = null,
    override val z: Int? = null,
    override val silent: Boolean? = null,
    override val animationDuration: Int? = null,
    override val animationEasing: String? = null,
    override val animationDelay: Int? = null,
    override val universalTransition: UniversalTransition? = null,
    override val tooltip: EchartsTooltip? = null
) : Series()