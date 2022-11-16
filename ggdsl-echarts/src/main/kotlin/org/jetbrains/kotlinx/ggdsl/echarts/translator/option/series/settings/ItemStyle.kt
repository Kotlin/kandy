package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor


@Serializable
public data class ItemStyle(
    val color: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Int? = null,
    val borderType: String? = null,
    val borderDashOffset: Int? = null,
    val borderGap: String? = null,
    val borderJoin: String? = null,
    val borderMiterLimit: Int? = null,
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Float? = null,
//    val decal: Decal? = null, // TODO
)