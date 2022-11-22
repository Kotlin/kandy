package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.features.marks.MarkLineFeature
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Blur
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Emphasis
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Label
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.LineStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.DataMarkLineSerializer
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

internal fun Map<FeatureName, LayerFeature>.toEchartsMarkLine(): EchartsMarkLine? {
    val dataMarkLines = (this[MarkLineFeature.FEATURE_NAME] as? MarkLineFeature)?.lines?.map {
        if (it.point1 == null) {
            DataMarkLine(it.nameML, it.typeML?.type, it.xAxis, it.yAxis)
        } else {
            val (point1X, point1XAxis) = if (it.point1.x == "max" || it.point1.x == "min" || it.point1.x == "average")
                null to it.point1.x
            else
                it.point1.x to null
            val (point1Y, point1YAxis) = if (it.point1.y == "max" || it.point1.y == "min" || it.point1.y == "average")
                null to it.point1.y
            else
                it.point1.y to null

            val (point2X, point2XAxis) = if (it.point2?.x == "max" || it.point2?.x == "min" || it.point2?.x == "average")
                null to it.point2.x
            else
                it.point2?.x to null
            val (point2Y, point2YAxis) = if (it.point2?.y == "max" || it.point2?.y == "min" || it.point2?.y == "average")
                null to it.point2.y
            else
                it.point2?.y to null

            DataMarkLine(
                listOfPoints =
                listOf(
                    DataMarkPoint(
                        it.nameML,
                        it.point1.type?.type,
                        it.point1.valueMP,
                        it.point1.coord?.toList(),
                        point1X,
                        point1Y,
                        point1XAxis,
                        point1YAxis
                    ),
                    DataMarkPoint(
                        null,
                        it.point2?.type?.type,
                        it.point2?.valueMP,
                        it.point2?.coord?.toList(),
                        point2X,
                        point2Y,
                        point2XAxis,
                        point2YAxis
                    )
                )
            )
        }
    }

    return if (dataMarkLines != null) {
        EchartsMarkLine(data = dataMarkLines)
    } else {
        null
    }
}

@Serializable(with = DataMarkLineSerializer::class)
public data class DataMarkLine(
    val name: String? = null,
    val type: String? = null,
    val xAxis: Double? = null,
    val yAxis: Double? = null,
    val listOfPoints: List<DataMarkPoint>? = null,
)

@Serializable
public data class EchartsMarkLine(
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