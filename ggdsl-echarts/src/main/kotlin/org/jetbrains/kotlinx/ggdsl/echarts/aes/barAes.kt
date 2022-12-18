/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.ggdsl.dsl.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.ScalableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Pixel
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.util.color.Color

// _______________________________________Border Bar Settings_______________________________________

internal val BORDER_COLOR: AesName = AesName("border_color")

/**
 * Aesthetic attribute for border [color][Color].
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.settings.BorderLayerContext
 */
public data class BorderColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = BORDER_COLOR
}

internal val BORDER_WIDTH: AesName = AesName("border_width")

/**
 * Aesthetic attribute for border width.
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.settings.BorderLayerContext
 */
public data class BorderWidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BORDER_WIDTH
}

internal val BORDER_TYPE: AesName = AesName("border_type")

/**
 * Aesthetic attribute for a border [line type][LineType].
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.settings.BorderLayerContext
 */
public data class BorderTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
    override val name: AesName = BORDER_TYPE
}

internal val BORDER_RADIUS: AesName = AesName("border_radius")


/**
 * Aesthetic attribute for border radius.
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.settings.BorderLayerContext
 */
public data class BorderRadiusAes(override val context: BindingContext) : NonPositionalAes<Pixel> {
    override val name: AesName = BORDER_RADIUS
}


// _______________________________________Background Bar Style_______________________________________
internal val BACKGROUND_COLOR: AesName = AesName("background_color")

/**
 * Aesthetic attribute for background color[Color].
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.BackgroundStyle
 */
public data class BackgroundColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = BACKGROUND_COLOR
}

internal val BACKGROUND_BORDER_COLOR: AesName = AesName("background_border_color")

/**
 * Aesthetic attribute for background border color[Color].
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.BackgroundStyle
 */
public data class BackgroundBorderColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = BACKGROUND_BORDER_COLOR
}

internal val BACKGROUND_BORDER_WIDTH: AesName = AesName("background_border_width")

/**
 * Aesthetic attribute for background border width.
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.BackgroundStyle
 */
public data class BackgroundBorderWidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BACKGROUND_BORDER_WIDTH
}


internal val BACKGROUND_BORDER_TYPE: AesName = AesName("background_border_type")

/**
 * Aesthetic attribute for a background border [line type][LineType].
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.BackgroundStyle
 */
public data class BackgroundBorderTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
    override val name: AesName = BACKGROUND_BORDER_TYPE
}

internal val BACKGROUND_BORDER_RADIUS: AesName = AesName("background_border_radius")


/**
 * Aesthetic attribute for background border radius.
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.BackgroundStyle
 */
public data class BackgroundBorderRadiusAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BACKGROUND_BORDER_RADIUS
}

internal val BACKGROUND_SHADOW_BLUR: AesName = AesName("background_shadow_blur")

/**
 * Aesthetic attribute for background shadow blur.
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.BackgroundStyle
 */
public data class BackgroundShadowBlurAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BACKGROUND_SHADOW_BLUR
}

internal val BACKGROUND_SHADOW_COLOR: AesName = AesName("background_shadow_color")

/**
 * Aesthetic attribute for background shadow [color][Color].
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.BackgroundStyle
 */
public data class BackgroundShadowColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = BACKGROUND_SHADOW_COLOR
}

internal val BACKGROUND_ALPHA: AesName = AesName("background_opacity")

/**
 * Aesthetic attribute for background opacity.
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.features.BackgroundStyle
 */
public data class BackgroundAlphaAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BACKGROUND_ALPHA
}

