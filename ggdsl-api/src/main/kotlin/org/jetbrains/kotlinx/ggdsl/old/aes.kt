package org.jetbrains.kotlinx.ggdsl.old

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.symbol.Symbol


data class SizeAes(override val context: BindingContext): MappableNonPositionalAes<Double> {
    override val name: String = "size"
}

data class ColorAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name: String = "color"
}

data class AlphaAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name: String = "alpha"
}

data class BorderWidthAes(override val context: BindingContext): NonPositionalAes<Double> {
    override val name: String = "border_size"
}

data class BorderColorAes(override val context: BindingContext): NonPositionalAes<Double> {
    override val name: String = "border_color"
}

data class WidthAes(override val context: BindingContext): NonPositionalAes<Double> {
    override val name: String = "width"
}

data class SymbolAes(override val context: BindingContext): MappableNonPositionalAes<Symbol> {
    override val name: String = "symbol"
}

data class LineTypeAes(override val context: BindingContext): NonPositionalAes<LineType> {
    override val name: String = "line_type"
}
