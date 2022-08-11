package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color

internal val LOWER = AesName("lower")

data class LowerAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = LOWER
}

internal val UPPER = AesName("upper")

data class UpperAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name: AesName = UPPER
}

internal val MIDDLE = AesName("middle")

data class MiddleAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = MIDDLE
}

internal val X_MIN = AesName("xmin")

data class XMinAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = X_MIN
}

internal val X_MAX = AesName("xmax")

data class XMaxAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = X_MAX
}

internal val Y_MIN = AesName("ymin")

data class YMinAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = Y_MIN
}

internal val Y_MAX = AesName("ymax")

data class YMaxAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = Y_MAX
}

internal val X_END = AesName("xend")

data class XEndAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = X_END
}

internal val Y_END = AesName("yend")

data class YEndAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = Y_END
}

internal val WIDTH_POS = AesName("width")

data class WidthPosAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = WIDTH_POS
}

internal val HEIGHT_POS = AesName("height")

data class HeightPosAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = HEIGHT_POS
}

internal val COLOR = AesName("color")

data class ColorAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name = COLOR
}

internal val FILL = AesName("fill")

data class FillAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name = FILL
}

val SHAPE = AesName("shape")

data class ShapeAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Symbol> {
    override val name = SHAPE
}

internal val LINE_TYPE = AesName("linetype")

data class LineTypeAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<LineType> {
    override val name = LINE_TYPE
}

internal val SIZE = AesName("size")

data class SizeAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = SIZE
}

internal val STROKE = AesName("stroke")

data class StrokeAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = STROKE
}

internal val WIDTH = AesName("width")

data class WidthAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = WIDTH
}

internal val ALPHA = AesName("alpha")

data class AlphaAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = ALPHA
}

internal val FATTEN = AesName("fatten")

data class FattenAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = FATTEN
}

// fix to positional non scalable???? or SubPositional

