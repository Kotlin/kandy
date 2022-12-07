package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
internal data class Emphasis(
    val disabled: Boolean? = null,
    val scale: String? = null,
    val focus: String? = null, // TODO (Focus)
    val blurScope: String? = null, // TODO (BlurScope)
    val label: Label? = null,
    val labelLine: LabelLine? = null,
    val itemStyle: ItemStyle? = null,
    val lineStyle: LineStyle? = null,
    val areaStyle: AreaStyle? = null,
    val endLabel: Label? = null
)