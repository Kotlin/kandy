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
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.util.color.Color

internal fun Map<Aes, Setting>.getItemStyle(): ItemStyle? {
    val color = this.getNPSValue<Color>(COLOR)?.toEchartsColor()
    val downColor = this.getNPSValue<Color>(DOWN_COLOR)?.toEchartsColor()
    val downBorderColor = this.getNPSValue<Color>(DOWN_BORDER_COLOR)?.toEchartsColor()
    val borderColor = this.getNPSValue<Color>(BORDER_COLOR)?.toEchartsColor()
    val borderWidth = this.getNPSValue<Double>(BORDER_WIDTH)
    val borderType = this.getNPSValue<LineType>(BORDER_TYPE)?.value
    val borderRadius = this.getNPSValue<Double>(BORDER_RADIUS)
    val opacity = this.getNPSValue<Double>(ALPHA)
    return ItemStyle(
        color,
        downColor,
        borderColor,
        downBorderColor,
        borderWidth,
        borderType,
        borderRadius,
        opacity = opacity
    ).takeIf { !it.isEmpty() }
}

@Serializable
internal data class ItemStyle(
    val color: EchartsColor? = null,
    val color0: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderColor0: EchartsColor? = null,
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