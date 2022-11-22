package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.COLOR
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Color
import org.jetbrains.kotlinx.ggdsl.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.BaseColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting

internal fun Map<AesName, Setting>.toAreaStyle(): AreaStyle {
    val hex = this.getNPSValue<Color>(COLOR)?.hex

    return AreaStyle(color = hex?.let { BaseColor(it) })
}

@Serializable
public data class AreaStyle(
    val color: EchartsColor? = null,
    val origin: String? = null,
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Float? = null
)