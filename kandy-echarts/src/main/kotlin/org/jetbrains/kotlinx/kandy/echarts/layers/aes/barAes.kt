/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.kandy.ir.aes.Aes

// _______________________________________Border Bar Settings_______________________________________

internal val BORDER_COLOR: Aes = Aes("border_color")

internal val BORDER_WIDTH: Aes = Aes("border_width")

internal val BORDER_TYPE: Aes = Aes("border_type")

internal val BORDER_RADIUS: Aes = Aes("border_radius")

// _______________________________________Background Bar Style_______________________________________
internal val BACKGROUND_COLOR: Aes = Aes("background_color")

internal val BACKGROUND_BORDER_COLOR: Aes = Aes("background_border_color")

internal val BACKGROUND_BORDER_WIDTH: Aes = Aes("background_border_width")

internal val BACKGROUND_BORDER_TYPE: Aes = Aes("background_border_type")

internal val BACKGROUND_BORDER_RADIUS: Aes = Aes("background_border_radius")

internal val BACKGROUND_SHADOW_BLUR: Aes = Aes("background_shadow_blur")

internal val BACKGROUND_SHADOW_COLOR: Aes = Aes("background_shadow_color")

internal val BACKGROUND_ALPHA: Aes = Aes("background_opacity")
