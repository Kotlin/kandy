/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.internal

import org.jetbrains.kotlinx.kandy.ir.aes.Aes


internal val X: Aes = Aes("x")

@PublishedApi
internal val Y: Aes = Aes("y")

internal val Z: Aes = Aes("z")

internal val COLOR: Aes = Aes("color")

internal val FILL: Aes = Aes("fill")

internal val SHAPE: Aes = Aes("shape")

internal val SIZE: Aes = Aes("size")

internal val WIDTH: Aes = Aes("width")

internal val HEIGHT: Aes = Aes("height")

internal val ALPHA: Aes = Aes("alpha")

internal val LINE_TYPE: Aes = Aes("linetype")

internal val LOWER = Aes("lower")

internal val UPPER = Aes("upper")

internal val MIDDLE = Aes("middle")

internal val Y_MIN = Aes("ymin")

internal val Y_MAX = Aes("ymax")

internal val X_MIN = Aes("xmin")

internal val X_MAX = Aes("xmax")

internal val FATTEN = Aes("fatten")

internal val SLOPE: Aes = Aes("slope")

internal val INTERCEPT: Aes = Aes("intercept")

internal val Y_BEGIN = Aes("y")

internal val Y_END = Aes("yend")

internal val X_BEGIN = Aes("x")

internal val X_END = Aes("xend")

internal val LABEL = Aes("label")

internal val FONT_FACE = Aes("fontface")

internal val FONT_FAMILY = Aes("family")

internal val SLICE = Aes("slice")

internal val EXPLODE = Aes("explode")

internal val HOLE = Aes("hole")

internal val STROKE = Aes("stroke")

internal val STROKE_COLOR = Aes("stroke_color")

internal val X_INTERCEPT: Aes = Aes("xintercept")

internal val Y_INTERCEPT: Aes = Aes("yintercept")

/*

internal val X_END = Aes("xend")

internal val Y_END = Aes("yend")

public val SLOPE: Aes = Aes("slope")

public val INTERCEPT: Aes = Aes("intercept")

internal val COLOR = Aes("color")

public class ColorAes internal constructor(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: Aes = COLOR
}

internal val FILL = Aes("fill")

public class FillAes internal constructor(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: Aes = FILL
}

public val SHAPE: Aes = Aes("shape")

public class ShapeAes internal constructor(override val context: BindingContext) : ScalableNonPositionalAes<Symbol> {
    override val name: Aes = SHAPE
}

internal val LINE_TYPE = Aes("linetype")

public class LineTypeAes internal constructor(override val context: BindingContext) :
    ScalableNonPositionalAes<LineType> {
    override val name: Aes = LINE_TYPE
}

internal val SIZE = Aes("size")

public class SizeAes internal constructor(override val context: BindingContext) : ScalableNonPositionalAes<Double> {
    override val name: Aes = SIZE
}

internal val STROKE = Aes("stroke")

public class StrokeAes internal constructor(override val context: BindingContext) : ScalableNonPositionalAes<Double> {
    override val name: Aes = STROKE
}

internal val WIDTH = Aes("width")

public class WidthAes internal constructor(override val context: BindingContext) : NonScalableNonPositionalAes<Double> {
    override val name: Aes = WIDTH
}

internal val ALPHA = Aes("alpha")

public class AlphaAes internal constructor(override val context: BindingContext) : ScalableNonPositionalAes<Double> {
    override val name: Aes = ALPHA
}

internal val FATTEN = Aes("fatten")

public class FattenAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = FATTEN
}

/*
internal val BINS = Aes("bins")

public class BinsAes internal constructor(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: Aes = BINS
}

internal val BIN_WIDTH = Aes("binwidth")

public class BinWidthAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = BIN_WIDTH
}

internal val BINS_2D = Aes("bins")

public class Bins2DAes internal constructor(override val context: BindingContext) : NonPositionalAes<Pair<Int, Int>> {
    override val name: Aes = BINS_2D
}

internal val BIN_WIDTH_2D = Aes("binwidth")

public class BinWidth2DAes internal constructor(override val context: BindingContext) :
    NonPositionalAes<Pair<Double, Double>> {
    override val name: Aes = BIN_WIDTH_2D
}

internal val CENTER = Aes("center")

public class CenterAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = CENTER
}


internal val BOUNDARY = Aes("boundary")

public class BoundaryAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = BOUNDARY
}

internal val DROP = Aes("drop")

public class DropAes internal constructor(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: Aes = DROP
}

public val OUTLIER_COLOR: Aes = Aes(Option.Geom.BoxplotOutlier.COLOR)

public data class OutlierColorAes(override val context: BindingContext) : NonPositionalAes<Color> {
    override val name: Aes = OUTLIER_COLOR
}

public val OUTLIER_FILL: Aes = Aes(Option.Geom.BoxplotOutlier.FILL)

public data class OutlierFillAes(override val context: BindingContext) : NonPositionalAes<Color> {
    override val name: Aes = OUTLIER_FILL
}

public val OUTLIER_SHAPE: Aes = Aes(Option.Geom.BoxplotOutlier.SHAPE)

public data class OutlierShapeAes(override val context: BindingContext) : NonPositionalAes<Symbol> {
    override val name: Aes = OUTLIER_SHAPE
}

public val OUTLIER_SIZE: Aes = Aes(Option.Geom.BoxplotOutlier.SIZE)

public data class OutlierSizeAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = OUTLIER_SIZE
}

internal val VAR_WIDTH = Aes("varwidth")

public class VarWidthAes internal constructor(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: Aes = VAR_WIDTH
}


public val BAND_WIDTH: Aes = Aes("bw")

public data class BWAes(override val context: BindingContext) : NonPositionalAes<BandWidth> {
    override val name: Aes = BAND_WIDTH
}


public val KERNEL: Aes = Aes("kernel")

public data class KernelAes(override val context: BindingContext) : NonPositionalAes<Kernel> {
    override val name: Aes = KERNEL
}

public val NUMBER: Aes = Aes("n")

public data class NumberAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: Aes = NUMBER
}

public val TRIM: Aes = Aes("trim")

public data class TrimAes(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: Aes = TRIM
}

public val ADJUST: Aes = Aes("adjust")

public data class AdjustAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = ADJUST
}

public val FULL_SCAN_MAX: Aes = Aes("fs_max")

public data class FullScanMaxAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: Aes = FULL_SCAN_MAX
}


public val DISTRIBUTION: Aes = Aes(Option.Stat.QQ.DISTRIBUTION)

public data class DistributionAes(override val context: BindingContext) : NonPositionalAes<String> {
    override val name: Aes = DISTRIBUTION
}

public val D_PARAMS: Aes = Aes(Option.Stat.QQ.DISTRIBUTION_PARAMETERS)

public data class DParamsAes(override val context: BindingContext) : NonPositionalAes<List<Number>> {
    override val name: Aes = D_PARAMS
}

public val SAMPLE: Aes = Aes("sample")

public class SampleAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: Aes = SAMPLE
}

public class SampleDummyAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: Aes = SAMPLE
}

public val QUANTILES: Aes = Aes("quantiles")

public data class QuantilesAes(override val context: BindingContext) : NonPositionalAes<Pair<Double, Double>> {
    override val name: Aes = QUANTILES
}

public val METHOD: Aes = Aes("method")

public data class MethodAes(override val context: BindingContext) : NonPositionalAes<String> {
    override val name: Aes = METHOD
}

public val SE: Aes = Aes("se")

public data class SEAes(override val context: BindingContext) : NonPositionalAes<Boolean> {
    override val name: Aes = SE
}

public val LEVEL: Aes = Aes("level")

public data class LevelAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = LEVEL
}

public val SPAN: Aes = Aes("span")

public data class SpanAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = SPAN
}

public val DEG: Aes = Aes("deg")

public data class DegAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: Aes = DEG
}

public val SEED: Aes = Aes("seed")

public data class SeedAes(override val context: BindingContext) : NonPositionalAes<Long> {
    override val name: Aes = SEED
}

public val MAX_N: Aes = Aes("max_n")

public data class MaxNAes(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name: Aes = MAX_N
}

public val VIOLIN_WIDTH: Aes = Aes("violinwidth")

public data class ViolinWidthAes(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name: Aes = VIOLIN_WIDTH
}

public val VIOLIN_SCALE: Aes = Aes("scale")

public data class ViolinScaleAes(override val context: BindingContext) : NonPositionalAes<String> {
    override val name: Aes = VIOLIN_SCALE
}

public val DRAW_QUANTILES: Aes = Aes("draw_quantiles")

public data class DrawQuantilesAes(override val context: BindingContext) : NonPositionalAes<List<Double>> {
    override val name: Aes = DRAW_QUANTILES
}

 */

internal val LABEL = Aes("label")

public data class LabelAes internal constructor(override val context: BindingContext) :
    ScalableNonPositionalAes<String> {
    override val name: Aes = LABEL
}

internal val FONT_FACE = Aes("fontface")

public data class FontFaceAes internal constructor(override val context: BindingContext) :
    ScalableNonPositionalAes<FontFace> {
    override val name: Aes = FONT_FACE
}

internal val FONT_FAMILY = Aes("family")

public data class FontFamilyAes internal constructor(override val context: BindingContext) :
    ScalableNonPositionalAes<FontFamily> {
    override val name: Aes = FONT_FAMILY
}

internal val ANGLE = Aes("angle")

public data class AngleAes internal constructor(override val context: BindingContext) :
    ScalableNonPositionalAes<Double> {
    override val name: Aes = ANGLE
}

internal val LABEL_FORMAT = Aes(Option.Geom.Text.LABEL_FORMAT)

public data class FormatAes internal constructor(override val context: BindingContext) :
    NonPositionalAes<String> {
    override val name: Aes = LABEL_FORMAT
}


internal val HORIZONTAL_JUSTIFICATION = Aes("hjust")

public data class HorizontalJustificationAes internal constructor(override val context: BindingContext) :
    NonPositionalAes<HorizontalJustification> {
    override val name: Aes = HORIZONTAL_JUSTIFICATION
}

internal val VERTICAL_JUSTIFICATION = Aes("vjust")

public data class VerticalJustificationAes internal constructor(override val context: BindingContext) :
    NonPositionalAes<VerticalJustification> {
    override val name: Aes = VERTICAL_JUSTIFICATION
}

public val X_INTERCEPT: Aes = Aes("xintercept")

public class XInterceptAes(override val context: BindingContext) : NonScalablePositionalAes {
    override val name: Aes = X_INTERCEPT
}

public val Y_INTERCEPT: Aes = Aes("yintercept")

public class YInterceptAes(override val context: BindingContext) : NonScalablePositionalAes {
    override val name: Aes = Y_INTERCEPT
}

 */
