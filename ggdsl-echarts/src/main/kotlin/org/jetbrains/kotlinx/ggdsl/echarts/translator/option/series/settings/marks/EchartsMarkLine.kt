package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Blur
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Emphasis
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Label
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.LineStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.DataMarkLineSerializer

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