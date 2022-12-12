package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting
import org.jetbrains.kotlinx.ggdsl.util.color.Color

internal fun Map<AesName, Setting>.getItemStyle(): ItemStyle? {
    val color = this.getNPSValue<Color>(COLOR)?.toEchartsColor()
    val borderColor = this.getNPSValue<Color>(BORDER_COLOR)?.toEchartsColor()
    val borderWidth = this.getNPSValue<Double>(BORDER_WIDTH)
    val borderType = this.getNPSValue<LineType>(BORDER_TYPE)?.value
    val borderRadius = this.getNPSValue<Double>(BORDER_RADIUS)
    val opacity = this.getNPSValue<Double>(ALPHA)
    return ItemStyle(
        color,
        borderColor,
        borderWidth,
        borderType,
        borderRadius,
        opacity = opacity
    ).takeIf { !it.isEmpty() }
}

@Serializable
internal data class ItemStyle(
    val color: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Double? = null,
    val borderType: StringNumberArray? = null,
    val borderRadius: Double? = null,
    val borderDashOffset: Int? = null,
    val borderGap: String? = null,
    val borderJoin: String? = null,
    val borderMiterLimit: Int? = null,
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Double? = null,
//    val decal: Decal? = null, // TODO
) {

    internal fun isEmpty(): Boolean = color == null && borderColor == null && borderWidth == null && borderType == null
        && borderDashOffset == null && borderGap == null && borderJoin == null && borderMiterLimit == null
        && shadowBlur == null && shadowColor == null && shadowOffsetX == null && shadowOffsetY == null && opacity == null
}