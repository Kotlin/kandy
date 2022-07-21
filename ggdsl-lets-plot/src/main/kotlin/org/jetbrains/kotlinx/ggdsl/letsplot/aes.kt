package org.jetbrains.kotlinx.ggdsl.letsplot

import jetbrains.letsPlot.Stat
import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LetsPlotLineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.LetsPlotSymbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

val LOWER = AesName("lower")
data class LowerAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name= LOWER
}

val UPPER = AesName("upper")
data class UpperAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: AesName = UPPER
}

val MIDDLE = AesName("middle")
data class MiddleAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name =MIDDLE
}

val X_MIN = AesName("xmin")
data class XMinAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name = X_MIN
}

val X_MAX = AesName("xmax")
data class XMaxAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name= X_MAX
}

val Y_MIN = AesName("ymin")
data class YMinAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name = Y_MIN
}

val Y_MAX = AesName("ymax")
data class YMaxAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name = Y_MAX
}

val X_END = AesName("xend")
data class XEndAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name= X_END
}

val Y_END = AesName("yend")
data class YEndAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name=Y_END
}

val WIDTH_POS = AesName("width")
data class WidthPosAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name=WIDTH_POS
}

val HEIGHT_POS = AesName("height")
data class HeightPosAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name=HEIGHT_POS
}

val COLOR = AesName("color")
data class ColorAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name = COLOR
}

val FILL = AesName("fill")
data class FillAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name=FILL
}

val SHAPE = AesName("shape")
data class ShapeAes(override val context: BindingContext): MappableNonPositionalAes<LetsPlotSymbol> {
    override val name=SHAPE
}

val LINE_TYPE = AesName("linetype")
data class LineTypeAes(override val context: BindingContext): MappableNonPositionalAes<LetsPlotLineType> {
    override val name=LINE_TYPE
}

val SIZE = AesName("size")
data class SizeAes(override val context: BindingContext): MappableNonPositionalAes<Double> {
    override val name=SIZE
}

val STROKE = AesName("stroke")
data class StrokeAes(override val context: BindingContext): MappableNonPositionalAes<Double> {
    override val name=STROKE
}

val WIDTH = AesName("width")
data class WidthAes(override val context: BindingContext): MappableNonPositionalAes<Double> {
    override val name=WIDTH
}

val ALPHA = AesName("alpha")
data class AlphaAes(override val context: BindingContext): MappableNonPositionalAes<Double> {
    override val name=ALPHA
}

val FATTEN = AesName("fatten")
data class FattenAes(override val context: BindingContext): NonPositionalAes<Double> {
    override val name=FATTEN
}

// fix to positional non scalable???? or SubPositional
/*
val LOWER = NonScalablePositionalAes("lower")
val UPPER = NonScalablePositionalAes("upper")
val MIDDLE = NonScalablePositionalAes("middle")

val X_MIN = NonScalablePositionalAes("xmin")
val X_MAX = NonScalablePositionalAes("xmax")
val Y_MIN = NonScalablePositionalAes("ymin")
val Y_MAX = NonScalablePositionalAes("ymax")

val X_END = NonScalablePositionalAes("xend")
val Y_END = NonScalablePositionalAes("yend")


val FATTEN = NonPositionalAes<Double>("fatten")

val COLOR = MappableNonPositionalAes<Color>("color")
val STROKE = MappableNonPositionalAes<Double>("stroke")
val FILL = MappableNonPositionalAes<Color>("fill")
val LINE_TYPE = MappableNonPositionalAes<LetsPlotLineType>("linetype")
val WIDTH = MappableNonPositionalAes<Double>("width")
val SIZE = MappableNonPositionalAes<Double>("size")
val ALPHA = MappableNonPositionalAes<Double>("alpha")

/*
val FILLED_SYMBOL = MappableNonPositionalAes<LetsPlotSymbol.Filled>("shape")
val UNFILLED_SYMBOL = MappableNonPositionalAes<LetsPlotSymbol.Unfilled>("shape")
 */

val SYMBOL = MappableNonPositionalAes<LetsPlotSymbol>("shape")

val WIDTH_POS = NonScalablePositionalAes("width")
val HEIGHT_POS = NonScalablePositionalAes("height")

 */

