package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Color

@Serializable
public data class LineStyle(
    val color: Color? = null,
    val width: Int? = null,
    val type: String? = null,
    val dashOffset: Int? = null,
    val cap: String? = null,
    val join: String? = null,
    val miterLimit: Int? = null,
    val shadowBlur: Int? = null,
    val shadowColor: Color? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Float? = null
)