package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.features.marks.MarkPoint
import org.jetbrains.kotlinx.ggdsl.echarts.features.marks.MarkPointFeature
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Blur
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Emphasis
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.ItemStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Label
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.DataMarkPointSerializer
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

internal fun Map<FeatureName, LayerFeature>.getEchartsMarkPoint(): EchartsMarkPoint? {
    val dataMarkPoints = (this[MarkPointFeature.FEATURE_NAME] as? MarkPointFeature)?.points?.map {
        DataMarkPoint(it.name, it.type?.type, it.valueMP, it.coord?.toList(), it.x, it.y)
    }

    return EchartsMarkPoint(data = dataMarkPoints).takeIf { dataMarkPoints != null }
}

internal fun MarkPoint.toDataMarkPoint(otherName: String? = null): DataMarkPoint {
    val (x1, xAxis1) = if (x == "max" || x == "min" || x == "average")
        null to x
    else
        x to null
    val (y1, yAxis1) = if (y == "max" || y == "min" || y == "average")
        null to y
    else
        y to null

    return DataMarkPoint(otherName ?: name, type?.type, valueMP, coord?.toList(), x1, y1, xAxis1, yAxis1)
}

@Serializable(with = DataMarkPointSerializer::class)
internal data class DataMarkPoint(
    val name: String? = null,
    val type: String? = null,
    val value: String? = null,
    val coord: List<Number>? = null,
    val x: String? = null,
    val y: String? = null,
    val xAxis: String? = null,
    val yAxis: String? = null,
)

@Serializable
internal data class EchartsMarkPoint(
    val symbol: String? = null,
    val symbolSize: Int? = null,
    val symbolRotate: Int? = null,
    val symbolKeepAspect: Boolean? = null,
    val symbolOffset: Pair<String, String>? = null,
    val silent: Boolean? = null,
    val label: Label? = null,
    val itemStyle: ItemStyle? = null,
    val emphasis: Emphasis? = null,
    val blur: Blur? = null,
    val data: List<DataMarkPoint>? = null,
    val animation: Boolean? = null,
    val animationThreshold: Int? = null,
    val animationDuration: Int? = null,
    val animationEasing: String? = null,
    val animationDelay: Int? = null,
    val animationDurationUpdate: Int? = null,
    val animationEasingUpdate: String? = null,
    val animationDelayUpdate: Int? = null,
)