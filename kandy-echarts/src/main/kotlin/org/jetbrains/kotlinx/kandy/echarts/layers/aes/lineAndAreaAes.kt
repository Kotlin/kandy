/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.kandy.ir.aes.AesName

//______________________________________LINE AES_____________________________________________

internal val LINE_COLOR: AesName = AesName("line_color")

// smooth parameter
internal val SMOOTH: AesName = AesName("smooth")

// width parameter
internal val WIDTH: AesName = AesName("width")

// opacity parameter for line
internal val LINE_ALPHA: AesName = AesName("line_alpha")

// type of line
internal val LINE_TYPE: AesName = AesName("line_type")

internal val SYMBOL: AesName = AesName("symbol")

internal val LINE_SHADOW_COLOR: AesName = AesName("line_shadow_color")

internal val LINE_SHADOW_BLUR: AesName = AesName("line_shadow_blur")

internal val STEP: AesName = AesName("step")

internal val CAP: AesName = AesName("cap")

//______________________________________AREA AES_____________________________________________

internal val AREA_COLOR: AesName = AesName("area_color")

// origin position of area
internal val AREA_POSITION: AesName = AesName("area_position")

// opacity parameter for area
internal val AREA_ALPHA: AesName = AesName("area_alpha")

internal val AREA_SHADOW_COLOR: AesName = AesName("area_shadow_color")

internal val AREA_SHADOW_BLUR: AesName = AesName("area_shadow_blur")
