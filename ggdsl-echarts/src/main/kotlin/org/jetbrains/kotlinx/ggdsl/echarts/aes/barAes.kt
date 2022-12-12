package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.ggdsl.dsl.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Pixel
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.util.color.Color

internal val BORDER_COLOR: AesName = AesName("border_color")

public data class BorderColorAes(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name: AesName = BORDER_COLOR
}

internal val BORDER_WIDTH: AesName = AesName("border_width")

public data class BorderWidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BORDER_WIDTH
}

internal val BORDER_TYPE: AesName = AesName("border_type")

public data class BorderTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
    override val name: AesName = BORDER_TYPE
}

internal val BORDER_RADIUS: AesName = AesName("border_radius")

public data class BorderRadiusAes(override val context: BindingContext) : NonPositionalAes<Pixel> {
    override val name: AesName = BORDER_RADIUS
}


// _______________________________________Background Bar Style_______________________________________
internal val BACKGROUND_COLOR: AesName = AesName("background_color")

public data class BackgroundColorAes(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name: AesName = BACKGROUND_COLOR
}

internal val BACKGROUND_BORDER_COLOR: AesName = AesName("background_border_color")

public data class BackgroundBorderColorAes(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name: AesName = BACKGROUND_BORDER_COLOR
}

internal val BACKGROUND_BORDER_WIDTH: AesName = AesName("background_border_width")

public data class BackgroundBorderWidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BACKGROUND_BORDER_WIDTH
}


internal val BACKGROUND_BORDER_TYPE: AesName = AesName("background_border_type")

public data class BackgroundBorderTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
    override val name: AesName = BACKGROUND_BORDER_TYPE
}

internal val BACKGROUND_BORDER_RADIUS: AesName = AesName("background_border_radius")

public data class BackgroundBorderRadiusAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BACKGROUND_BORDER_RADIUS
}

internal val BACKGROUND_SHADOW_BLUR: AesName = AesName("background_shadow_blur")

public data class BackgroundShadowBlurAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = BACKGROUND_SHADOW_BLUR
}

internal val BACKGROUND_SHADOW_COLOR: AesName = AesName("background_shadow_color")

public data class BackgroundShadowColorAes(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name: AesName = BACKGROUND_SHADOW_COLOR
}

internal val BACKGROUND_ALPHA: AesName = AesName("background_opacity")

public data class BackgroundAlphaAes(override val context: BindingContext) : NonPositionalAes<Color> {
    override val name: AesName = BACKGROUND_ALPHA
}

