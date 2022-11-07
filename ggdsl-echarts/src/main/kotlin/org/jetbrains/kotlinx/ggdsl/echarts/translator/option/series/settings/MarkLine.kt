package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
public data class MarkLine(
    val silent: Boolean? = null,
    val symbol: String? = null,
    val symbolSize: Int? = null,
    val precision: Int? = null,
    val label: Label? = null,
    val lineStyle: LineStyle? = null,
    val emphasis: Emphasis? = null,
    val blur: Blur? = null,
    val data: List<List<String>>? = null,
    val animation: Boolean? = null,
    val animationThreshold: Int? = null,
    val animationDuration: Int? = null,
    val animationEasing: String? = null,
    val animationDelay: Int? = null,
    val animationDurationUpdate: Int? = null,
    val animationEasingUpdate: String? = null,
    val animationDelayUpdat: Int? = null
)