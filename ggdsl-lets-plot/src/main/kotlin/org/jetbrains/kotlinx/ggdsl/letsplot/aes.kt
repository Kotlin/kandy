package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LetsPlotLineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.LetsPlotSymbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

data class LowerAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "lower"
}
data class UpperAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "upper"
}
data class MiddleAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "middle"
}
data class XMinAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "xmin"
}
data class XMaxAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "xmax"
}
data class YMinAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "ymin"
}
data class YMaxAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "ymax"
}
data class XEndAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "xend"
}
data class YEndAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "yend"
}
data class WidthPosAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "width"
}
data class HeightPosAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: String = "height"
}

data class ColorAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name: String = "color"
}
data class FillAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name: String = "fill"
}
data class ShapeAes(override val context: BindingContext): MappableNonPositionalAes<LetsPlotSymbol> {
    override val name: String = "shape"
}
data class LineTypeAes(override val context: BindingContext): MappableNonPositionalAes<LetsPlotLineType> {
    override val name: String = "linetype"
}

data class SizeAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name: String = "size"
}
data class StrokeAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name: String = "stroke"
}
data class WidthAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name: String = "width"
}
data class AlphaAes(override val context: BindingContext): MappableNonPositionalAes<Color> {
    override val name: String = "alpha"
}


data class FattenAes(override val context: BindingContext): NonPositionalAes<Double> {
    override val name: String = "fatten"
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

