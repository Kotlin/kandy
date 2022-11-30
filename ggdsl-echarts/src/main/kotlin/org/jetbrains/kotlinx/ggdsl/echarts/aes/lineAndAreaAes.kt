package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.ggdsl.dsl.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Cap
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Step
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.util.color.Color

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

// opacity parameter
internal val ALPHA: AesName = AesName("alpha")

public data class AlphaAes(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name: AesName = ALPHA
}

// type of line
internal val LINE_TYPE: AesName = AesName("line_type")

public data class LineTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
    override val name: AesName = LINE_TYPE
}

internal val SYMBOL: AesName = AesName("symbol")

public data class SymbolAes(override val context: BindingContext) : MappableNonPositionalAes<Symbol> {
    override val name: AesName = SYMBOL
}

internal val SHADOW_COLOR: AesName = AesName("shadow_color")

public data class ShadowColorAes(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name: AesName = SHADOW_COLOR
}

internal val SHADOW_BLUR: AesName = AesName("shadow_blur")

public data class ShadowBlurAes(override val context: BindingContext) : MappableNonPositionalAes<Int> {
    override val name: AesName = SHADOW_BLUR
}

internal val STEP: AesName = AesName("step")

public data class StepAes(override val context: BindingContext) : NonPositionalAes<Step> {
    override val name: AesName = STEP
}

internal val CAP: AesName = AesName("cap")

public data class CapAes(override val context: BindingContext) : NonPositionalAes<Cap> {
    override val name: AesName = CAP
}