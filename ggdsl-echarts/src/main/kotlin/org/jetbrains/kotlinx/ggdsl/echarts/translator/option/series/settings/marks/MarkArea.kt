package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Blur
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Emphasis
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.ItemStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Label

@Serializable
public data class MarkArea(
    val silent: Boolean? = null,
    val label: Label? = null,
    val itemStyle: ItemStyle? = null,
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
    val animationDelayUpdate: Int? = null
)