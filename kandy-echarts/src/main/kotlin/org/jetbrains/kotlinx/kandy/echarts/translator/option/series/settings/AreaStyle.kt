/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.*
import org.jetbrains.kotlinx.kandy.echarts.settings.AreaPosition
import org.jetbrains.kotlinx.kandy.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.kandy.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.util.color.Color

internal fun Map<Aes, Setting>.getAreaStyle(): AreaStyle {
    val color = this.getNPSValue<Color>(AREA_COLOR)?.toEchartsColor()
    val origin = this.getNPSValue<AreaPosition>(AREA_POSITION)?.position
    val shadowBlur = this.getNPSValue<Int>(AREA_SHADOW_BLUR)
    val shadowColor = this.getNPSValue<Color>(AREA_SHADOW_COLOR)?.toEchartsColor()
    val opacity = this.getNPSValue<Double>(AREA_ALPHA)

    return AreaStyle(color, origin, shadowBlur, shadowColor, opacity = opacity)
}

@Serializable
internal data class AreaStyle(
    val color: EchartsColor? = null,
    val origin: StringNumberArray? = null,
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Double? = null
)