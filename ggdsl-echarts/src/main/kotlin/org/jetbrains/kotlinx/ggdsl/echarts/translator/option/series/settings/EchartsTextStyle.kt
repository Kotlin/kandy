/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray

@Serializable
internal data class EchartsTextStyle(
    val color: EchartsColor? = null,
    val fontStyle: String? = null,
    val fontWeight: StringNumberArray? = null,
    val fontFamily: String? = null,
    val fontSize: Int? = null,
    val lineHeight: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    val textBorderColor: EchartsColor? = null,
    val textBorderWidth: Double? = null,
    val textBorderType: StringNumberArray? = null,
    val textBorderDashOffset: Int? = null,
    val textShadowColor: EchartsColor? = null,
    val textShadowBlur: Int? = null,
    val textShadowOffsetX: Int? = null,
    val textShadowOffsetY: Int? = null,
    val overflow: String? = null,
    val ellipsis: String? = null,
//    public val rich: String? = null
)