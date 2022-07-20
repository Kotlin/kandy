package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LetsPlotLineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.LetsPlotSymbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

// fix to positional non scalable???? or SubPositional
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


/*
// todo
val MAPPABLE_BORDER_COLOR = MappableNonPositionalAes<Color>("border_color")
val MAPPABLE_LINE_TYPE = MappableNonPositionalAes<LineType>("line_type")



 */
