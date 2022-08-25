package org.jetbrains.kotlinx.ggdsl.letsplot

import jetbrains.datalore.plot.config.Option
import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.label.HorizontalJustification
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.label.VerticalJustification
import org.jetbrains.kotlinx.ggdsl.letsplot.util.font.FontFace
import org.jetbrains.kotlinx.ggdsl.letsplot.util.font.FontFamily
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color

val X = AesName("x")

class XAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = X
}

val Y = AesName("y")

class YAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = Y
}

val PlotContext.x
    get() = XAes(this)

val PlotContext.y
    get() = YAes(this)

val Z = AesName("z")

class ZAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = Z
}

internal val LOWER = AesName("lower")

class LowerAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = LOWER
}

internal val UPPER = AesName("upper")

class UpperAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name: AesName = UPPER
}

internal val MIDDLE = AesName("middle")

class MiddleAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = MIDDLE
}

internal val X_MIN = AesName("xmin")

class XMinAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = X_MIN
}

internal val X_MAX = AesName("xmax")

class XMaxAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = X_MAX
}

internal val Y_MIN = AesName("ymin")

class YMinAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = Y_MIN
}

internal val Y_MAX = AesName("ymax")

class YMaxAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = Y_MAX
}

internal val X_END = AesName("xend")

class XEndAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = X_END
}

internal val Y_END = AesName("yend")

class YEndAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = Y_END
}

internal val WIDTH_POS = AesName("width")

class WidthPosAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = WIDTH_POS
}

internal val HEIGHT_POS = AesName("height")

class HeightPosAes internal constructor(override val context: BindingContext) : NonScalablePositionalAes {
    override val name = HEIGHT_POS
}

internal val COLOR = AesName("color")

class ColorAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name = COLOR
}

internal val FILL = AesName("fill")

class FillAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Color> {
    override val name = FILL
}

val SHAPE = AesName("shape")

class ShapeAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Symbol> {
    override val name = SHAPE
}

internal val LINE_TYPE = AesName("linetype")

class LineTypeAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<LineType> {
    override val name = LINE_TYPE
}

internal val SIZE = AesName("size")

class SizeAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = SIZE
}

internal val STROKE = AesName("stroke")

class StrokeAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = STROKE
}

internal val WIDTH = AesName("width")

class WidthAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = WIDTH
}

internal val ALPHA = AesName("alpha")

class AlphaAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<Double> {
    override val name = ALPHA
}

internal val FATTEN = AesName("fatten")

class FattenAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = FATTEN
}

internal val BINS = AesName("bins")

class BinsAes internal constructor(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name = BINS
}

internal val BIN_WIDTH = AesName("binwidth")

class BinWidthAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = BIN_WIDTH
}

internal val BINS_2D = AesName("bins")

class Bins2DAes internal constructor(override val context: BindingContext) : NonPositionalAes<Pair<Int, Int>> {
    override val name = BINS_2D
}

internal val BIN_WIDTH_2D = AesName("binwidth")

class BinWidth2DAes internal constructor(override val context: BindingContext) :
    NonPositionalAes<Pair<Double, Double>> {
    override val name = BIN_WIDTH_2D
}

internal val CENTER = AesName("center")

class CenterAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = CENTER
}


internal val BOUNDARY = AesName("boundary")

class BoundaryAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = BOUNDARY
}

internal val DROP = AesName("drop")

class DropAes internal constructor(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name = DROP
}

val OUTLIER_COLOR = AesName(Option.Geom.BoxplotOutlier.COLOR)

data class OutlierColorAes(override val context: BindingContext) : NonPositionalAes<Color> {
    override val name: AesName = OUTLIER_COLOR
}

val OUTLIER_FILL = AesName(Option.Geom.BoxplotOutlier.FILL)

data class OutlierFillAes(override val context: BindingContext) : NonPositionalAes<Color> {
    override val name: AesName = OUTLIER_FILL
}

val OUTLIER_SHAPE = AesName(Option.Geom.BoxplotOutlier.SHAPE)

data class OutlierShapeAes(override val context: BindingContext) : NonPositionalAes<Symbol> {
    override val name: AesName = OUTLIER_SHAPE
}

val OUTLIER_SIZE = AesName(Option.Geom.BoxplotOutlier.SIZE)

data class OutlierSizeAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = OUTLIER_SIZE
}

internal val VAR_WIDTH = AesName("varwidth")

class VarWidthAes internal constructor(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name = VAR_WIDTH
}


val BAND_WIDTH = AesName("bw")

data class BWAes(override val context: BindingContext) : NonPositionalAes<BandWidth> {
    override val name: AesName = BAND_WIDTH
}


val KERNEL = AesName("kernel")

data class KernelAes(override val context: BindingContext) : NonPositionalAes<Kernel> {
    override val name: AesName = KERNEL
}

val NUMBER = AesName("n")

data class NumberAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: AesName = NUMBER
}

val TRIM = AesName("trim")

data class TrimAes(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: AesName = TRIM
}

val ADJUST = AesName("adjust")

data class AdjustAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = ADJUST
}

val FULL_SCAN_MAX = AesName("fs_max")

data class FullScanMaxAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: AesName = FULL_SCAN_MAX
}


val DISTRIBUTION = AesName(Option.Stat.QQ.DISTRIBUTION)

data class DistributionAes(override val context: BindingContext) : NonPositionalAes<String> {
    override val name: AesName = DISTRIBUTION
}

val D_PARAMS = AesName(Option.Stat.QQ.DISTRIBUTION_PARAMETERS)

data class DParamsAes(override val context: BindingContext) : NonPositionalAes<List<Number>> {
    override val name: AesName = D_PARAMS
}

val SAMPLE = AesName("sample")

class SampleAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = SAMPLE
}

val QUANTILES = AesName("quantiles")

data class QuantilesAes(override val context: BindingContext) : NonPositionalAes<Pair<Double, Double>> {
    override val name: AesName = QUANTILES
}

val METHOD = AesName("method")

data class MethodAes(override val context: BindingContext) : NonPositionalAes<String> {
    override val name: AesName = METHOD
}

val SE = AesName("se")

data class SEAes(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: AesName = SE
}

val LEVEL = AesName("level")

data class LevelAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = LEVEL
}

val SPAN = AesName("span")

data class SpanAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = SPAN
}

val DEG = AesName("deg")

data class DegAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: AesName = DEG
}

val SEED = AesName("seed")

data class SeedAes(override val context: BindingContext) : NonPositionalAes<Long> {
    override val name: AesName = SEED
}

val MAX_N = AesName("max_n")

data class MaxNAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: AesName = MAX_N
}

val VIOLIN_WIDTH = AesName("violinwidth")

data class ViolinWidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: AesName = VIOLIN_WIDTH
}

val VIOLIN_SCALE = AesName("scale")

data class ViolinScaleAes(override val context: BindingContext) : NonPositionalAes<String> {
    override val name: AesName = VIOLIN_SCALE
}

val DRAW_QUANTILES = AesName("draw_quantiles")

data class DrawQuantilesAes(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: AesName = DRAW_QUANTILES
}

internal val LABEL = AesName("label")

data class LabelAes internal constructor(override val context: BindingContext) : MappableNonPositionalAes<String> {
    override val name = LABEL
}

internal val FONT_FACE = AesName("fontface")

data class FontFaceAes internal constructor(override val context: BindingContext) :
    MappableNonPositionalAes<FontFace> {
    override val name = FONT_FACE
}

internal val FONT_FAMILY = AesName("family")

data class FontFamilyAes internal constructor(override val context: BindingContext) :
    MappableNonPositionalAes<FontFamily> {
    override val name = FONT_FAMILY
}

internal val ANGLE = AesName("angle")

data class AngleAes internal constructor(override val context: BindingContext) :
    MappableNonPositionalAes<Double> {
    override val name = ANGLE
}

internal val LABEL_FORMAT = AesName(Option.Geom.Text.LABEL_FORMAT)

data class FormatAes internal constructor(override val context: BindingContext) :
    NonPositionalAes<String> {
    override val name = LABEL_FORMAT
}


internal val HORIZONTAL_JUSTIFICATION = AesName("hjust")

data class HorizontalJustificationAes internal constructor(override val context: BindingContext) :
    NonPositionalAes<HorizontalJustification> {
    override val name = HORIZONTAL_JUSTIFICATION
}

internal val VERTICAL_JUSTIFICATION = AesName("vjust")

data class VerticalJustificationAes internal constructor(override val context: BindingContext) :
    NonPositionalAes<VerticalJustification> {
    override val name = VERTICAL_JUSTIFICATION
}

val X_INTERCEPT = AesName("xintercept")
// TODO
class XInterceptAes(override val context: BindingContext) : NonScalablePositionalAes {
    override val name: AesName = X_INTERCEPT
}

val Y_INTERCEPT = AesName("yintercept")

// TODO
class YInterceptAes(override val context: BindingContext) : NonScalablePositionalAes {
    override val name: AesName = Y_INTERCEPT
}
