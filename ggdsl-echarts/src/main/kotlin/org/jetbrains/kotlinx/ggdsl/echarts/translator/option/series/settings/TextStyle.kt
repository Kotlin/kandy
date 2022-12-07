package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor

@Serializable
internal data class TextStyle(
    val color: EchartsColor? = null,
    val fontStyle: String? = null,
    val fontWeight: String? = null,
    val fontFamily: String? = null,
    val fontSize: Int? = null,
    val lineHeight: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    val textBorderColor: EchartsColor? = null,
    val textBorderWidth: Int? = null,
    val textBorderType: String? = null,
    val textBorderDashOffset: Int? = null,
    val textShadowColor: EchartsColor? = null,
    val textShadowBlur: Int? = null,
    val textShadowOffsetX: Int? = null,
    val textShadowOffsetY: Int? = null,
    val overflow: String? = null,
    val ellipsis: String? = null,
//    public val rich: String? = null
)