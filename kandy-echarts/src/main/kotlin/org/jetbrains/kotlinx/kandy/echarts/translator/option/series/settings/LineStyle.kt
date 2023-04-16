/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.*
import org.jetbrains.kotlinx.kandy.echarts.settings.Cap
import org.jetbrains.kotlinx.kandy.echarts.settings.LineType
import org.jetbrains.kotlinx.kandy.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.kandy.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.util.color.Color

internal fun Map<AesName, Setting>.getLineStyle(): LineStyle? {
    val color = this.getNPSValue<Color>(LINE_COLOR)?.toEchartsColor()
    val width = this.getNPSValue<Double>(WIDTH)
    val type = this.getNPSValue<LineType>(LINE_TYPE)?.value
    val cap = this.getNPSValue<Cap>(CAP)?.type
    val shadowBlur = this.getNPSValue<Int>(LINE_SHADOW_BLUR)
    val shadowColor = this.getNPSValue<Color>(LINE_SHADOW_COLOR)?.toEchartsColor()
    val opacity = this.getNPSValue<Double>(LINE_ALPHA)

    val lineStyle = LineStyle(
        color = color,
        width = width,
        type = type,
        cap = cap,
        shadowBlur = shadowBlur,
        shadowColor = shadowColor,
        opacity = opacity
    )

    return if (lineStyle.isEmpty()) {
        null
    } else {
        lineStyle
    }
}

@Serializable
internal data class LineStyle(
    val color: EchartsColor? = null,
    val width: Double? = null,
    val type: StringNumberArray? = null,
    val dashOffset: Int? = null,
    val cap: String? = null,
    val join: String? = null,
    val miterLimit: Int? = null,
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Double? = null
) {
    public fun isEmpty(): Boolean =
        color == null && width == null && type == null && dashOffset == null
            && cap == null && join == null && miterLimit == null && shadowBlur == null
            && shadowColor == null && shadowOffsetX == null && shadowOffsetY == null
            && opacity == null
}