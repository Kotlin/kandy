package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.ggdsl.dsl.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName

// smooth parameter
internal val SMOOTH: AesName = AesName("smooth")

public data class SmoothAes(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: AesName = SMOOTH
}

// width parameter
internal val WIDTH: AesName = AesName("width")

public data class WidthAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: AesName = WIDTH
}

// alpha parameter // TODO(ItemStyle)
//internal val ALPHA: AesName = AesName("alpha")
//
//public data class AlphaAes(override val context: BindingContext) : MappableNonPositionalAes<Double> {
//    override val name: AesName = ALPHA
//}

internal val LINE_TYPE: AesName = AesName("line_type")

public data class LineTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
    override val name: AesName = LINE_TYPE
}