package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.COLOR
import org.jetbrains.kotlinx.ggdsl.echarts.aes.LINE_TYPE
import org.jetbrains.kotlinx.ggdsl.echarts.aes.WIDTH
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Color
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.BaseColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting

internal fun Map<AesName, Setting>.toLineStyle(): LineStyle? {
    val hex = this.getNPSValue<Color>(COLOR)?.hex
    val width = this.getNPSValue<Int>(WIDTH)
    val type = this.getNPSValue<LineType>(LINE_TYPE)?.type

    return if (hex != null || width != null || type != null) {
        LineStyle(color = hex?.let { BaseColor(hex) }, width = width, type = type)
    } else {
        null
    }
}

@Serializable
public data class LineStyle(
    val color: EchartsColor? = null,
    val width: Int? = null,
    val type: String? = null,
    val dashOffset: Int? = null,
    val cap: String? = null,
    val join: String? = null,
    val miterLimit: Int? = null,
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Float? = null
)