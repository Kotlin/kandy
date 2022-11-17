package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor

@Serializable
public data class TextStyle(
    public val color: EchartsColor? = null,
    public val fontStyle: String? = null,
    public val fontWeight: String? = null,
    public val fontFamily: String? = null,
    public val fontSize: Int? = null,
    public val lineHeight: Int? = null,
    public val width: Int? = null,
    public val height: Int? = null,
    public val textBorderColor: EchartsColor? = null,
    public val textBorderWidth: Int? = null,
    public val textBorderType: String? = null,
    public val textBorderDashOffset: Int? = null,
    public val textShadowColor: EchartsColor? = null,
    public val textShadowBlur: Int? = null,
    public val textShadowOffsetX: Int? = null,
    public val textShadowOffsetY: Int? = null,
    public val overflow: String? = null,
    public val ellipsis: String? = null,
//    public val rich: String? = null
)