package org.jetbrains.kotlinx.ggdsl.old

import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.util.symbol.Symbol



val SIZE = MappableNonPositionalAes<Double>("size")
val COLOR = MappableNonPositionalAes<Color>("color")
val ALPHA = MappableNonPositionalAes<Double>("alpha")

val BORDER_WIDTH = NonPositionalAes<Double>("border_size")
val BORDER_COLOR = NonPositionalAes<Color>("border_color")

val WIDTH = NonPositionalAes<Double>("width")

val SYMBOL = MappableNonPositionalAes<Symbol>("symbol")

val LINE_TYPE = NonPositionalAes<LineType>("line_type")