/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.EchartsTextStyle

@Serializable
internal data class EchartsTitle(
    val id: String? = null,
    val show: Boolean? = null,
    val text: String? = null,
    val link: String? = null,
    val target: String? = null,
    val textStyle: EchartsTextStyle? = null,
    val subtext: String? = null,
    val sublink: String? = null,
    val subtarget: String? = null,
    val subtextStyle: EchartsTextStyle? = null,
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
    val borderWidth: Double? = null,
    val borderRadius: @Contextual Any? = null, // number, array
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null
) {
    internal fun isEmpty(): Boolean =
        id == null && show == null && text == null && link == null && target == null && textStyle == null
            && subtext == null && sublink == null && subtarget == null && subtextStyle == null && textAlign == null
            && textVerticalAlign == null && triggerEvent == null && padding == null && itemGap == null && zlevel == null
            && z == null && left == null && top == null && right == null && bottom == null && backgroundColor == null
            && borderColor == null && borderWidth == null && borderRadius == null && shadowBlur == null
            && shadowColor == null && shadowOffsetX == null && shadowOffsetY == null
}