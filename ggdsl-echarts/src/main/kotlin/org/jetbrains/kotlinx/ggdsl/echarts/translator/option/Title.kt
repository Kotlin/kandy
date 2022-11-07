package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
public enum class TitleTarget(public val target: String) {
    SELF("self"), BLANK("blank")
}

@Serializable
public enum class TextAlign(public val align: String) {
    AUTO("auto"), LEFT("left"), RIGHT("right"), CENTER("center")
}

@Serializable
public data class Title(
    val id: String? = null,
    val show: Boolean? = null,
    val text: String? = null,
    val link: String? = null,
    val target: TitleTarget? = null,
//    val textStyle: TextStyle? = null,
    val subtext: String? = null,
    val sublink: String? = null,
    val subtarget: TitleTarget? = null,
//    val subtextStyle: TextStyle? = null,
    val textAlign: TextAlign? = null,
    val triggerEvent: Boolean?,
    val padding: @Contextual Any?,  // 4 максимум [5, 10, 5, 10] number, array
    val itemGap: Int?,
    val zlevel: Int?,
    val z: Int?,
    val left: @Contextual Any?, // string, number
    val top: @Contextual Any?,
    val right: @Contextual Any?,
    val bottom: @Contextual Any?,
    val backgroundColor: Color?,
    val borderColor: Color?,
    val borderWidth: Int?,
    val borderRadius: @Contextual Any?, // number, array
    val shadowBlur: Int?,
    val shadowColor: Color?,
    val shadowOffsetX: Int?,
    val shadowOffsetY: Int?
)