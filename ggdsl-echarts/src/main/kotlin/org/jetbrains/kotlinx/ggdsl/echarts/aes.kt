/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Color
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Symbol
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes

internal val X: AesName = AesName("x")

public data class XAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = X
}

internal val Y: AesName = AesName("y")

public data class YAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = Y
}

public val PlotContext.x: XAes
    get() = XAes(this)

public val PlotContext.y: YAes
    get() = YAes(this)

internal val NAME: AesName = AesName("name")

public data class NameAes(override val context: BindingContext) : NonPositionalAes<String> {
    override val name: AesName = NAME
}

internal val SYMBOL: AesName = AesName("symbol")

public data class SymbolAes(override val context: BindingContext) : MappableNonPositionalAes<Symbol> {
    override val name: AesName = SYMBOL
}


//public val SIZE: AesName = AesName("size")
//
//public data class SizeAes(override val context: BindingContext) : MappableNonPositionalAes<Double> {
//    override val name: AesName = SIZE
//}

internal val COLOR: AesName = AesName("color")

public data class ColorAes(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name: AesName = COLOR
}

//public val ALPHA: AesName = AesName("alpha")
//
//public data class AlphaAes(override val context: BindingContext) : MappableNonPositionalAes<Double> {
//    override val name: AesName = ALPHA
//}

//public val BORDER_SIZE: AesName = AesName("border_size")

//public data class BorderWidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
//    override val name: AesName = BORDER_SIZE
//}
//
//public val BORDER_COLOR: AesName = AesName("border_color")
//
//public data class BorderColorAes(override val context: BindingContext) : NonPositionalAes<Color> {
//    override val name: AesName = BORDER_COLOR
//}
//
//public val WIDTH: AesName = AesName("width")
//
//public data class WidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
//    override val name: AesName = WIDTH
//}
//
//public val SYMBOL: AesName = AesName("symbol")

//public data class SymbolAes(override val context: BindingContext) : MappableNonPositionalAes<Symbol> {
//    override val name: AesName = SYMBOL
//}
//
//public val LINE_TYPE: AesName = AesName("line_type")
//
//public data class LineTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
//    override val name: AesName = LINE_TYPE
//}
