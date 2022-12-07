package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.TextStyle

@Serializable
internal enum class TitleTarget(public val target: String) {
    SELF("self"), BLANK("blank")
}

@Serializable
internal data class Title(
    val id: String? = null,
    val show: Boolean? = null,
    val text: String? = null,
    val link: String? = null,
    val target: TitleTarget? = null,
    val textStyle: TextStyle? = null,
    val subtext: String? = null,
    val sublink: String? = null,
    val subtarget: TitleTarget? = null,
    val subtextStyle: TextStyle? = null,
    val textAlign: String? = null,
    val textVerticalAlign: String? = null,
    val triggerEvent: Boolean? = null,
    val padding: @Contextual Any? = null,  // 4 максимум [5, 10, 5, 10] number, array
    val itemGap: Int? = null,
    val zlevel: Int? = null,
    val z: Int? = null,
    val left: @Contextual Any? = null, // string, number
    val top: @Contextual Any? = null,
    val right: @Contextual Any? = null,
    val bottom: @Contextual Any? = null,
    val backgroundColor: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Int? = null,
    val borderRadius: @Contextual Any? = null, // number, array
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null
)