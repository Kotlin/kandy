package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting
import org.jetbrains.kotlinx.ggdsl.util.color.Color

internal fun Map<AesName, Setting>.getBackgroundStyle(): BackgroundStyle? {
    val color = this.getNPSValue<Color>(BACKGROUND_COLOR)?.toEchartsColor()
    val borderColor = this.getNPSValue<Color>(BACKGROUND_BORDER_COLOR)?.toEchartsColor()
    val borderWidth = this.getNPSValue<Double>(BACKGROUND_BORDER_WIDTH)
    val borderType = this.getNPSValue<LineType>(BACKGROUND_BORDER_TYPE)?.type
    val borderRadius = this.getNPSValue<Double>(BACKGROUND_BORDER_RADIUS)
    val shadowBlur = this.getNPSValue<Double>(BACKGROUND_SHADOW_BLUR)
    val shadowColor = this.getNPSValue<Color>(BACKGROUND_SHADOW_COLOR)?.toEchartsColor()
    val opacity = this.getNPSValue<Double>(BACKGROUND_ALPHA)

    return BackgroundStyle(
        color, borderColor, borderWidth, borderType,
        borderRadius, shadowBlur, shadowColor,
        opacity = opacity
    ).takeIf { !it.isEmpty() }
//    return BackgroundStyle()
}

@Serializable
public data class BackgroundStyle(
    val color: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Double? = null,
    val borderType: String? = null,
    val borderRadius: Double? = null,
    val shadowBlur: Double? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Double? = null
) {
    internal fun isEmpty(): Boolean = color == null && borderColor == null && borderWidth == null && borderType == null
        && borderRadius == null && shadowBlur == null && shadowColor == null && shadowOffsetX == null
        && shadowOffsetY == null && opacity == null
}