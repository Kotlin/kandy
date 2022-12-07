package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks

import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.descriptors.listSerialDescriptor
import org.jetbrains.kotlinx.ggdsl.echarts.features.marks.MarkAreaFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.marks.MarkLineFeature
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Blur
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Emphasis
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Label
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.LineStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.DataMarkLineSerializer
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

internal fun Map<FeatureName, LayerFeature>.getEchartsMarkLine(): EchartsMarkLine? {
    val dataMarkLines = (this[MarkLineFeature.FEATURE_NAME] as? MarkLineFeature)?.lines?.map {
        if (it.point1 == null) {
            DataMarkLine(it.nameML, it.typeML?.type, it.xAxis, it.yAxis)
        } else {
            DataMarkLine(listOfPoints = listOf(it.point1.toDataMarkPoint(it.nameML), it.point2!!.toDataMarkPoint()))
        }
    }

    return dataMarkLines?.let { EchartsMarkLine(data = it) }
}

@Serializable(with = DataMarkLineSerializer::class)
internal data class DataMarkLine(
    val name: String? = null,
    val type: String? = null,
    val xAxis: Double? = null,
    val yAxis: Double? = null,
    val listOfPoints: List<DataMarkPoint>? = null,
)

@Serializable
internal data class EchartsMarkLine(
    val silent: Boolean? = null,
    val symbol: String? = null,
    val symbolSize: Int? = null,
    val precision: Int? = null,
    val label: Label? = null,
    val lineStyle: LineStyle? = null,
    val emphasis: Emphasis? = null,
    val blur: Blur? = null,
    val data: List<DataMarkLine>? = null,
    val animation: Boolean? = null,
    val animationThreshold: Int? = null,
    val animationDuration: Int? = null,
    val animationEasing: String? = null,
    val animationDelay: Int? = null,
    val animationDurationUpdate: Int? = null,
    val animationEasingUpdate: String? = null,
    val animationDelayUpdat: Int? = null
)