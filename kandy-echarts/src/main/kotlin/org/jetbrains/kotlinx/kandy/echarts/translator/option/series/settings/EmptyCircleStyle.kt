/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsColor

@Serializable
internal data class EmptyCircleStyle(
    val color: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Int? = null,
    val borderType: String? = null,
    val borderDashOffset: Int? = null,
    val borderCap: String? = null,
    val borderJoin: String? = null,
    val borderMiterLimit: Int? = null,
    val shadowBlur: Int? = null,
    val shadowColor: EchartsColor? = null,
    val shadowOffsetX: Int? = null,
    val shadowOffsetY: Int? = null,
    val opacity: Float? = null
)