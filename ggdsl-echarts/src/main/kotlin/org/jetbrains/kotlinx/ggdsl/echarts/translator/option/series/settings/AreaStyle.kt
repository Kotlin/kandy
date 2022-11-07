package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Color

@Serializable
public data class AreaStyle(
    val color: Color? = null,
    val origin: String? = null,
    val shadowBlur: Int? = null,
    val shadowColor: Color? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Float? = null
)