package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
public data class Select(
    val disabled: Boolean? = null,
    val label: Label? = null,
    val labelLine: LabelLine? = null,
    val itemStyle: ItemStyle? = null,
    val lineStyle: LineStyle? = null,
    val areaStyle: AreaStyle? = null,
    val endLabel: Label? = null
)