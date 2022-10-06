/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.util.color.Color

val X = AesName("x")

data class XAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = X
}

val Y = AesName("y")

data class YAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = Y
}

val PlotContext.x
    get() = XAes(this)

val PlotContext.y
    get() = YAes(this)

val SIZE = AesName("size")

data class SizeAes(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = SIZE
}

val COLOR = AesName("color")

data class ColorAes(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name = COLOR
}

val ALPHA = AesName("alpha")

data class AlphaAes(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = ALPHA
}

val BORDER_SIZE = AesName("border_size")

data class BorderWidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = BORDER_SIZE
}

val BORDER_COLOR = AesName("border_color")

data class BorderColorAes(override val context: BindingContext) : NonPositionalAes<Color> {
    override val name = BORDER_COLOR
}

val WIDTH = AesName("width")

data class WidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = WIDTH
}

val SYMBOL = AesName("symbol")

data class SymbolAes(override val context: BindingContext) : MappableNonPositionalAes<Symbol> {
    override val name = SYMBOL
}

val LINE_TYPE = AesName("line_type")

data class LineTypeAes(override val context: BindingContext) : NonPositionalAes<LineType> {
    override val name = LINE_TYPE
}
