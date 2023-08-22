/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.kandy.ir.aes.Aes

//______________________________________LINE AES_____________________________________________

internal val LINE_COLOR: Aes = Aes("line_color")

// smooth parameter
internal val SMOOTH: Aes = Aes("smooth")

// width parameter
internal val WIDTH: Aes = Aes("width")

// opacity parameter for line
internal val LINE_ALPHA: Aes = Aes("line_alpha")

// type of line
internal val LINE_TYPE: Aes = Aes("line_type")

internal val SYMBOL: Aes = Aes("symbol")

internal val LINE_SHADOW_COLOR: Aes = Aes("line_shadow_color")

internal val LINE_SHADOW_BLUR: Aes = Aes("line_shadow_blur")

internal val STEP: Aes = Aes("step")

internal val CAP: Aes = Aes("cap")

//______________________________________AREA AES_____________________________________________

internal val AREA_COLOR: Aes = Aes("area_color")

// origin position of area
internal val AREA_POSITION: Aes = Aes("area_position")

// opacity parameter for area
internal val AREA_ALPHA: Aes = Aes("area_alpha")

internal val AREA_SHADOW_COLOR: Aes = Aes("area_shadow_color")

internal val AREA_SHADOW_BLUR: Aes = Aes("area_shadow_blur")
