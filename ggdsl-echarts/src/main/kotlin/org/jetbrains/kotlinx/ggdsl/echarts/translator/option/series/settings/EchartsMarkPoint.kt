package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
public data class DataMarkPoint(
    val name: String? = null,
    val type: String? = null,
    val value: String? = null,
    val coord: List<Double>? = null, // TODO(add Contextual Number (Int and Float))
    val x: String? = null,
    val y: String? = null,
)

@Serializable
public data class EchartsMarkPoint(
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