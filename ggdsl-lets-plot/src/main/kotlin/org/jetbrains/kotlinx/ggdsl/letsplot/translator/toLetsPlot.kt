package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.label.TEXT
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.*
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.ColorBar
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.DiscreteLegend
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.None
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.SimpleValueWrapper
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.StandardColor
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.asDiscrete
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.*
import kotlin.reflect.KType
import kotlin.reflect.typeOf

internal fun Mapping.wrap(): Pair<String, Any> {
    return when (this) {
        is NonScalablePositionalMapping<*> -> aes.name to source.id
        is ScaledMapping<*> -> when(this.sourceScaled.scale) {
            is CategoricalScale -> aes.name to asDiscrete(
                sourceScaled.source.id,
                order = (scaleParameters as? PositionalParameters<*>)?.orderBy?.order,
                orderBy = (scaleParameters as? PositionalParameters<*>)?.orderBy?.name
            )
            else -> aes.name to sourceScaled.source.id
        }
    }
}

internal fun Setting.wrap(): Pair<String, Any> {
    return when (this) {
        is NonPositionalSetting<*> -> aes.name to wrapValue(value)
        is PositionalSetting<*> -> aes.name to wrapValue(value)
    }
}

// TODO
internal fun wrapValue(value: Any): Any {
    if (value is Enum<*>) {
        return value.toString()
    }
    if (value is SimpleValueWrapper) {
        return value.value
    }
    if (value is StandardColor) {
        return value.description
    }

    if (value is Symbol) {
        return value.shape
    }
    if (value is LineType) {
        return value.description
    }
    return value
}

internal fun Geom.toStat(): org.jetbrains.letsPlot.intern.layer.StatOptions {
    return when (this) {
        BIN_2D -> Stat.bin2D()
        BOXPLOT_STAT -> Stat.boxplot()
        CONTOUR -> Stat.contour()
        CONTOUR_FILLED -> Stat.contourFilled()
        DENSITY -> Stat.density()
        DENSITY_2D -> Stat.density2D()
        DENSITY_2D_FILLED -> Stat.density2DFilled()
        FREQPOLY -> Stat.bin()
        HISTOGRAM -> Stat.bin()
        QQ -> Stat.qq()
        QQ2 -> Stat.qq2()
        QQ2_LINE -> Stat.qq2Line()
        QQ_LINE -> Stat.qqLine()
        SMOOTH -> Stat.smooth()
        VIOLIN -> Stat.yDensity()
        else -> Stat.identity
    }
}

internal fun Geom.toLPGeom(): GeomOptions {
    return when (this) {
        AB_LINE -> org.jetbrains.letsPlot.Geom.abline()
        AREA -> org.jetbrains.letsPlot.Geom.area()
        BAR -> org.jetbrains.letsPlot.Geom.bar()
        BIN_2D -> org.jetbrains.letsPlot.Geom.tile()
        BOXPLOT -> org.jetbrains.letsPlot.Geom.boxplot()
        BOXPLOT_STAT -> org.jetbrains.letsPlot.Geom.boxplot()
        CONTOUR -> GeomOptions(GeomKind.CONTOUR)
        CONTOUR_FILLED -> GeomOptions(GeomKind.CONTOURF)
        CROSS_BAR -> org.jetbrains.letsPlot.Geom.crossbar()
        DENSITY -> GeomOptions(GeomKind.DENSITY)
        DENSITY_2D -> GeomOptions(GeomKind.DENSITY2D)
        DENSITY_2D_FILLED -> GeomOptions(GeomKind.DENSITY2DF)
        ERROR_BAR -> org.jetbrains.letsPlot.Geom.errorbar()
        FREQPOLY -> GeomOptions(GeomKind.FREQPOLY)
        HISTOGRAM -> org.jetbrains.letsPlot.Geom.histogram()
        H_LINE -> org.jetbrains.letsPlot.Geom.hline()
        LINE -> org.jetbrains.letsPlot.Geom.line()
        PATH -> org.jetbrains.letsPlot.Geom.path()
        LINE_RANGE -> org.jetbrains.letsPlot.Geom.linerange()
        POINT -> org.jetbrains.letsPlot.Geom.point()
        POINT_RANGE -> org.jetbrains.letsPlot.Geom.pointrange()
        QQ -> GeomOptions(GeomKind.Q_Q)
        QQ2 -> GeomOptions(GeomKind.Q_Q_2)
        QQ_LINE -> GeomOptions(GeomKind.Q_Q_LINE)
        QQ2_LINE -> GeomOptions(GeomKind.Q_Q_2_LINE)
        RASTER -> org.jetbrains.letsPlot.Geom.raster()
        RECT -> org.jetbrains.letsPlot.Geom.rect()
        RIBBON -> org.jetbrains.letsPlot.Geom.ribbon()
        SEGMENT -> org.jetbrains.letsPlot.Geom.segment()
        SMOOTH -> GeomOptions(GeomKind.SMOOTH)
        STEP -> org.jetbrains.letsPlot.Geom.step()
        TILE -> org.jetbrains.letsPlot.Geom.tile()
        TEXT -> org.jetbrains.letsPlot.Geom.text()
        VIOLIN -> GeomOptions(GeomKind.VIOLIN)
        V_LINE -> org.jetbrains.letsPlot.Geom.vline()
        else -> TODO()
    }
}


/**
 * TODO
 * 1) Unspecified
 * 2) DateTime
 * 3) trans = (transform as? Transformation)?.name
 */
internal fun Scale.wrap(
    aes: AesName,
    domainType: KType,
    scaleParameters: ScaleParameters? = null
): org.jetbrains.letsPlot.intern.Scale? {
    return when (this) {
        is PositionalScale<*> -> {
            val axis = (scaleParameters as PositionalParameters<*>?)?.axis

            val name = axis?.name
            val breaks = axis?.breaks
            val labels = axis?.labels

            when (this) {
                is PositionalCategoricalScale<*> -> {
                    when (aes) {
                        X -> scaleXDiscrete(
                            limits = categories,
                            name = name,
                            breaks = breaks,
                            labels = labels,
                        )

                        Y -> scaleYDiscrete(
                            limits = categories,
                            name = name,
                            breaks = breaks,
                            labels = labels,
                        )

                        else -> TODO("error")
                    }
                }

                is PositionalContinuousScale<*> -> {
                    when (aes) {
                        X -> scaleXContinuous(
                            limits = limits.toLP(),

                            name = name,
                            breaks = breaks?.map { it as Number }, // TODO() }
                            labels = labels,
                            trans = (transform as? Transformation)?.name
                            )

                        Y -> scaleYContinuous(
                            limits = limits.toLP(),

                            name = name,
                            breaks = breaks?.map { it as Number }, // TODO() }
                            labels = labels,
                            trans = (transform as? Transformation)?.name
                        )

                        else -> TODO()
                    }
                }

               // is CustomScale -> TODO()
            }
        }

        is NonPositionalScale<*, *> -> {
            val legend = (scaleParameters as NonPositionalParameters<*, *>?)?.legend

            val name = legend?.name
            val breaks = legend?.breaks
            val labels = legend?.labels
            val legendType = legend?.type?.let {
                when (it) {
                    is None -> "none"
                    is ColorBar -> guideColorbar(
                        barHeight = it.barHeight,
                        barWidth = it.barWidth,
                        nbin = it.nBin
                    )

                    is DiscreteLegend -> guideLegend(
                        nrow = it.nRow,
                        ncol = it.nCol,
                        byRow = it.byRow
                    )
                }
            }

            when (this) {
                is NonPositionalCategoricalScale<*, *> -> {
                    when (aes) {
                        // todo check all
                        SIZE, WIDTH, STROKE -> scaleSizeManual(
                            values = rangeValues?.map { it as Number } ?: TODO("default scale size discrete"),
                            limits = domainCategories,
                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType

                        )

                        COLOR -> {
                            if (rangeValues == null) {
                                scaleColorDiscrete(
                                    limits = domainCategories,
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType

                                )
                            } else {
                                scaleColorManual(
                                    limits = domainCategories,
                                    values = rangeValues!!.map { (it as StandardColor).description },
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType

                                )
                            }
                        }

                        FILL -> {
                            if (rangeValues == null) {
                                scaleFillDiscrete(
                                    limits = domainCategories,
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType

                                )
                            } else {
                                scaleFillManual(
                                    limits = domainCategories,
                                    values = rangeValues!!.map { (it as StandardColor).description },
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType

                                )
                            }
                        }
                        // TODO
                        ALPHA -> scaleAlphaManual(
                            limits = domainCategories,
                            values = rangeValues?.map { it as Double } ?: TODO("default scale alpha discrete"),

                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType

                        )

                        LINE_TYPE -> scaleLinetypeManual(
                            limits = domainCategories,
                            values = rangeValues?.map { (it as LineType).codeNumber }
                                ?: TODO("default scale alpha discrete"),

                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType

                        )

                        SHAPE -> if (rangeValues == null) {
                            scaleShape(
                                limits = domainCategories,

                                name = name,
                                breaks = breaks,
                                labels = labels,
                                guide = legendType
                            )
                        } else {
                            scaleShapeManual(
                                limits = domainCategories,
                                values = rangeValues!!.map { (it as Symbol).shape },

                                name = name,
                                breaks = breaks,
                                labels = labels,
                                guide = legendType
                            )
                        }
                        /*
                        FILLED_SYMBOL -> if (rangeValues == null) {
                            TODO()
                        } else {
                            scaleShapeManual(
                                limits = domainCategories,
                                values = rangeValues!!.map { (it as LetsPlotSymbol).shape },

                                name = name,
                                breaks = breaks,
                                labels = labels,
                                guide = legendType
                            )

                        }

                        UNFILLED_SYMBOL -> if (rangeValues == null) {
                            scaleShape(
                                limits = domainCategories,

                                name = name,
                                breaks = breaks,
                                labels = labels,
                                guide = legendType
                            )
                        } else {
                            scaleShapeManual(
                                limits = domainCategories,
                                values = rangeValues!!.map { (it as LetsPlotSymbol).shape },

                                name = name,
                                breaks = breaks,
                                labels = labels,
                                guide = legendType
                            )

                        }

                         */


                        else -> TODO()
                    }
                }

                is NonPositionalContinuousScale<*, *> -> {
                    when (aes) {
                        // todo check all
                        SIZE, WIDTH, STROKE -> scaleSize(
                            limits = domainLimits.toLP(),
                            range = rangeLimits.toLP(),

                            name = name,
                            breaks = breaks?.map { it as Number },
                            labels = labels,
                            guide = legendType,

                            trans = (transform as Transformation?)?.name
                        )


                        COLOR -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.first as? StandardColor)?.description to (it?.second as? StandardColor)?.description
                            }
                            val limits = domainLimits.toLP() // todo datetime here

                            scaleColorContinuous(
                                low = lowColor,
                                high = highColor,
                                limits = limits,

                                name = name,
                                breaks = breaks?.map { it as Number },
                                labels = labels,
                                guide = legendType,

                                trans = (transform as Transformation?)?.name

                            )

                        }

                        FILL -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.first as? StandardColor)?.description to (it?.second as? StandardColor)?.description
                            }
                            val limits = domainLimits.toLP() // todo datetime here

                            scaleFillContinuous(
                                low = lowColor,
                                high = highColor,
                                limits = limits,

                                name = name,
                                breaks = breaks?.map { it as Number },
                                labels = labels,
                                guide = legendType,

                                trans = (transform as Transformation?)?.name

                            )

                        }


                        // TODO
                        ALPHA -> scaleAlpha(
                            limits = domainLimits.toLP(),
                            range = rangeLimits.toLP(),

                            name = name,
                            breaks = breaks?.map { it as Number },
                            labels = labels,
                            guide = legendType,

                            trans = (transform as Transformation?)?.name

                        ) // TODO
                        // TODO  SYMBOL -> TODO("cant apply contunuous scale")
                        else -> TODO()
                    }
                }

                is CustomScale -> when (this) {
                    is ScaleColorGrey<*> -> when(aes) {
                        COLOR -> scaleColorGrey(
                            paletteRange?.first,
                            paletteRange?.second,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.toLP(),
                            trans = transform?.name
                        )
                        FILL -> scaleFillGrey(
                            paletteRange?.first,
                            paletteRange?.second,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.toLP(),
                            trans = transform?.name
                        )
                        else -> TODO()
                    }

                    is ScaleColorHue<*> -> when (aes) {
                        COLOR -> scaleColorHue(
                            huesRange,
                            chroma,
                            luminance,
                            hueStart,
                            direction?.value,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.toLP(),
                            trans = transform?.name
                        )

                        FILL -> scaleFillHue(
                            huesRange,
                            chroma,
                            luminance,
                            hueStart,
                            direction?.value,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.toLP(),
                            trans = transform?.name
                        )

                        else -> TODO()
                    }

                    is ScaleColorBrewer<*> -> when (aes) {
                        COLOR -> scaleColorBrewer(
                            type = type?.name,
                            palette = type?.palette?.name,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = limits,
                            trans = transform?.name
                        )

                        FILL -> scaleFillBrewer(
                            type = type?.name,
                            palette = type?.palette?.name,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = limits,
                            trans = transform?.name
                        )

                        else -> TODO()
                    }

                    is ScaleContinuousColorGradient2<*> -> when (aes) {
                        COLOR -> scaleColorGradient2(
                            (low as StandardColor).description,
                            (mid as StandardColor).description,
                            (high as StandardColor).description,
                            midpoint,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.toLP(),
                            trans = transform?.name
                        )

                        FILL -> scaleFillGradient2(
                            (low as StandardColor).description,
                            (mid as StandardColor).description,
                            (high as StandardColor).description,
                            midpoint,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.toLP(),
                            trans = transform?.name
                        )

                        else -> TODO()
                    }

                    is ScaleContinuousColorGradientN<*> -> when (aes) {
                        COLOR -> scaleColorGradientN(
                            rangeColors.map { (it as StandardColor).description },
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.toLP(),
                            trans = transform?.name
                        )

                        FILL -> scaleFillGradientN(
                            rangeColors.map { (it as StandardColor).description },
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.toLP(),
                            trans = transform?.name
                        )

                        else -> TODO()
                    }

                    else -> TODO()
                }

                else -> TODO()
            }
        }

        // TODO
        is UnspecifiedScale -> {
            when (this) {
                DefaultUnspecifiedScale -> when(aes) {
                    // todo other types for unspecified categorical???
                    X, Y -> if (domainType == typeOf<String>()){
                        PositionalCategoricalUnspecifiedScale.wrap(aes, domainType, scaleParameters)
                    } else {
                        PositionalContinuousUnspecifiedScale().wrap(aes, domainType, scaleParameters)
                    }
                    else -> if (domainType == typeOf<String>()){
                        NonPositionalCategoricalUnspecifiedScale.wrap(aes, domainType, scaleParameters)
                    } else {
                        NonPositionalContinuousUnspecifiedScale().wrap(aes, domainType, scaleParameters)
                    }

                }
                // TODO!!!
                is NonPositionalUnspecifiedScale -> {
                    val legend = (scaleParameters as NonPositionalParameters<*, *>?)?.legend

                    val name = legend?.name
                    val breaks = legend?.breaks
                    val labels = legend?.labels
                    val legendType = legend?.type?.let {
                        when (it) {
                            is None -> "none"
                            is ColorBar -> guideColorbar(
                                barHeight = it.barHeight,
                                barWidth = it.barWidth,
                                nbin = it.nBin
                            )

                            is DiscreteLegend -> guideLegend(
                                nrow = it.nRow,
                                ncol = it.nCol,
                                byRow = it.byRow
                            )
                        }
                    }
                    when (this) {
                        NonPositionalCategoricalUnspecifiedScale -> null
                        is NonPositionalContinuousUnspecifiedScale -> null
                    }
                }

                is PositionalUnspecifiedScale -> {
                    val axis = (scaleParameters as PositionalParameters<*>?)?.axis

                    val name = axis?.name
                    val breaks = axis?.breaks
                    val labels = axis?.labels
                    when (this) {
                        PositionalCategoricalUnspecifiedScale -> when (aes) {
                            X -> scaleXDiscrete(name = name, breaks = breaks, labels = labels,)
                            Y -> scaleYDiscrete(name = name, breaks = breaks, labels = labels)
                            else -> TODO()
                        }

                        is PositionalContinuousUnspecifiedScale -> when (aes) {
                            X -> scaleXContinuous(
                                name = name,
                                breaks = breaks?.map { it as Number },
                                labels = labels,
                                trans = (transform as? Transformation)?.name
                            )

                            Y -> scaleYContinuous(
                                name = name,
                                breaks = breaks?.map { it as Number },
                                labels = labels,
                                trans = (transform as? Transformation)?.name
                            )

                            else -> TODO()
                        }
                    }
                }
            }
        }

        else -> TODO("error")
    }

}

internal fun FreeScale.wrap(featureBuffer: MutableList<Feature>)  {
    when(this) {
        is FreePositionalScale<*> -> featureBuffer.add(scale.wrap(aes, domainType, scaleParameters)!!) // TODO
        else -> TODO()
    }
}

// TODO
internal fun Pair<Any, Any>?.toLP() = this?.let { (it.first as Number) to (it.second as Number) }

internal fun Layer.wrap(featureBuffer: MutableList<Feature>) {
    featureBuffer.add(LayerWrapper(this))
    mappings.forEach { (aes, mapping) ->
        if (mapping is ScaledMapping<*>) {
            mapping.sourceScaled.scale.wrap(aes, mapping.domainType, mapping.scaleParameters)?.let {
                featureBuffer.add(it)
            }
        }
    }
}

internal fun LetsPlotLayout.wrap(featureBuffer: MutableList<Feature>) {
    val labs = labs(
        title, subtitle, caption, xAxisLabel, yAxisLabel
    )
    featureBuffer.addAll(labs.elements)
    size?.let {
        featureBuffer.add(ggsize(it.first, it.second))
    }
    theme?.let {
        featureBuffer.add(it.wrap())
    }
    customTheme?.let {
        featureBuffer.add(it.wrap())
    }
}

fun Plot.toLetsPlot(): org.jetbrains.letsPlot.intern.Plot {
    val featureBuffer = buildList {
        layers.forEach { it.wrap(this) }
        freeScales.forEach { it.value.wrap(this) }
        features.forEach { it.value.wrap(this) }
        (layout as? LetsPlotLayout)?.wrap(this) // todo
    }
    return letsPlot(dataset) + FeatureList(featureBuffer)
    //  var plotBuffer = letsPlot(dataset)
    /*
    var plotBuffer = layers.fold(letsPlot(dataset)) { _buffer, layer ->
        var buffer = _buffer + LayerWrapper(layer)
        layer.mappings.forEach { (aes, mapping) ->
            if (mapping is ScaledMapping<*>) {
                mapping.sourceScaled.scale.wrap(aes, layer.geom)?.let { buffer += it }
            }
        }
        buffer
    }
    for ((featureName, feature) in features) {
        when (featureName) {
            FACET_GRID_FEATURE -> {
                plotBuffer += (feature as FacetGridFeature).wrap()
            }
        }
    }
    return plotBuffer +
            labs(title = layout.title) +
            with(layout.size) { ggsize(first, second) } // todo layout

     */
}

