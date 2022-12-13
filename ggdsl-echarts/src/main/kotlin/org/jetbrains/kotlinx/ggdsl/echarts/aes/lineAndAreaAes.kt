/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.ggdsl.dsl.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.ScalableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.settings.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.util.color.Color

//______________________________________LINE AES_____________________________________________

internal val LINE_COLOR: AesName = AesName("line_color")

public data class LineColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = LINE_COLOR
}

// smooth parameter
internal val SMOOTH: AesName = AesName("smooth")

public data class SmoothAes(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: AesName = SMOOTH
}

// width parameter
internal val WIDTH: AesName = AesName("width")

public data class WidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = WIDTH
}

// opacity parameter for line
internal val LINE_ALPHA: AesName = AesName("line_alpha")

public data class LineAlphaAes(override val context: BindingContext) : ScalableNonPositionalAes<Double> {
    override val name: AesName = LINE_ALPHA
}

// type of line
internal val LINE_TYPE: AesName = AesName("line_type")

public data class LineTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
    override val name: AesName = LINE_TYPE
}

internal val SYMBOL: AesName = AesName("symbol")

public data class SymbolAes(override val context: BindingContext) : ScalableNonPositionalAes<Symbol> {
    override val name: AesName = SYMBOL
}

internal val LINE_SHADOW_COLOR: AesName = AesName("line_shadow_color")

public data class LineShadowColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = LINE_SHADOW_COLOR
}

internal val LINE_SHADOW_BLUR: AesName = AesName("line_shadow_blur")

public data class LineShadowBlurAes(override val context: BindingContext) : ScalableNonPositionalAes<Int> {
    override val name: AesName = LINE_SHADOW_BLUR
}

internal val STEP: AesName = AesName("step")

public data class StepAes(override val context: BindingContext) : NonPositionalAes<Step> {
    override val name: AesName = STEP
}

internal val CAP: AesName = AesName("cap")

public data class CapAes(override val context: BindingContext) : NonPositionalAes<Cap> {
    override val name: AesName = CAP
}

//______________________________________AREA AES_____________________________________________

internal val AREA_COLOR: AesName = AesName("area_color")

public data class AreaColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = AREA_COLOR
}

// origin position of area
internal val AREA_POSITION: AesName = AesName("area_position")

public data class PositionAes(override val context: BindingContext) : ScalableNonPositionalAes<AreaPosition> {
    override val name: AesName = AREA_POSITION
}

// opacity parameter for area
internal val AREA_ALPHA: AesName = AesName("area_alpha")

public data class AreaAlphaAes(override val context: BindingContext) : ScalableNonPositionalAes<Double> {
    override val name: AesName = AREA_ALPHA
}

internal val AREA_SHADOW_COLOR: AesName = AesName("area_shadow_color")

public data class AreaShadowColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = AREA_SHADOW_COLOR
}

internal val AREA_SHADOW_BLUR: AesName = AesName("area_shadow_blur")

public data class AreaShadowBlurAes(override val context: BindingContext) : ScalableNonPositionalAes<Int> {
    override val name: AesName = AREA_SHADOW_BLUR
}
