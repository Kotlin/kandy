/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.*
import org.jetbrains.kotlinx.kandy.echarts.settings.LineType
import org.jetbrains.kotlinx.kandy.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.kandy.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.util.color.Color

internal fun Map<AesName, Setting>.getBackgroundStyle(): BackgroundStyle? {
    val color = this.getNPSValue<Color>(BACKGROUND_COLOR)?.toEchartsColor()
    val borderColor = this.getNPSValue<Color>(BACKGROUND_BORDER_COLOR)?.toEchartsColor()
    val borderWidth = this.getNPSValue<Double>(BACKGROUND_BORDER_WIDTH)
    val borderType = this.getNPSValue<LineType>(BACKGROUND_BORDER_TYPE)?.value
    val borderRadius = this.getNPSValue<Double>(BACKGROUND_BORDER_RADIUS)
    val shadowBlur = this.getNPSValue<Double>(BACKGROUND_SHADOW_BLUR)
    val shadowColor = this.getNPSValue<Color>(BACKGROUND_SHADOW_COLOR)?.toEchartsColor()
    val opacity = this.getNPSValue<Double>(BACKGROUND_ALPHA)

    return BackgroundStyle(
        color, borderColor, borderWidth, borderType,
        borderRadius, shadowBlur, shadowColor,
        opacity = opacity
    ).takeIf { !it.isEmpty() }
}

@Serializable
internal data class BackgroundStyle(
    val color: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Double? = null,
    val borderType: StringNumberArray? = null,
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