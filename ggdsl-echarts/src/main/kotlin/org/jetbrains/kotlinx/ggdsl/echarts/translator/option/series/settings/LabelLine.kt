package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
public data class LabelLine(
    val show: Boolean? = null,
    val showAbove: Boolean? = null,
    val length2: Int? = null,
    val smooth: String? = null,
    val minTurnAngle: Int? = null,
    val lineStyle: LineStyle? = null,
)