package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Feature
import jetbrains.letsPlot.intern.FeatureList
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonScalablePositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledMapping
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.*
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.*
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.ColorBar
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.DiscreteLegend
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.None
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LetsPlotLineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.LetsPlotSymbol
import org.jetbrains.kotlinx.ggdsl.util.color.StandardColor

internal fun Mapping.wrap(geom: Geom): Pair<String, String> {
    return when (this) {
        is NonScalablePositionalMapping<*> -> aes.name to source.id
        is ScaledMapping<*> -> aes.name to sourceScaled.source.id
    }
}

internal fun NonPositionalSetting<*>.wrap(geom: Geom): Pair<String, Any> {
    return aes.name to wrapValue(value)
}
/*
// TODO
internal fun wrapBinding(aes: Aes, value: Any, geom: Geom): Pair<String, Any> {
    /*
    if (aes == SYMBOL) {
        val ret = "shape" to wrapValue(value) // TODO scaling
        // TODO
        return ret
    }

     */
    return aes.toLPName(geom) to wrapValue(value)
}

 */

// TODO
internal fun wrapValue(value: Any): Any {
    if (value is StandardColor) {
        return value.description
    }
    /*
    if (value is CommonSymbol) {
        return wrapSymbol(value)
    }

     */
    if (value is LetsPlotSymbol) {
        return value.shape
    }
    /*
    if (value is CommonLineType) {
        return value.description
    }

     */
    if (value is LetsPlotLineType) {
        return value.description
    }
    return value
}
/*
internal fun commonSymbolToShape(symbol: CommonSymbol): Int {
    return when (symbol) {
        Symbol.RECTANGLE -> 22
        Symbol.CIRCLE -> 21
        Symbol.TRIANGLE -> 24
        else -> TODO()
    }
}


 */
/*
internal fun wrapSymbol(symbol: Symbol): Int {
    return when (symbol) {
        is LetsPlotSymbol -> symbol.shape
    //    is CommonSymbol -> commonSymbolToShape(symbol)
        else -> TODO()
    }
}

 */



// TODO rewrite
internal fun Geom.toLPGeom(): jetbrains.letsPlot.intern.layer.GeomOptions {
    return when (this) {
        // TODO FILL SHAPES WITH SCALES
        AB_LINE -> jetbrains.letsPlot.Geom.abline()
        AREA -> jetbrains.letsPlot.Geom.area()
        BAR -> jetbrains.letsPlot.Geom.bar()
        BOXPLOT -> jetbrains.letsPlot.Geom.boxplot()
        CROSS_BAR -> jetbrains.letsPlot.Geom.crossbar()
        ERROR_BAR -> jetbrains.letsPlot.Geom.errorbar()
        // TODO line/path
        LINE, PATH -> jetbrains.letsPlot.Geom.path()
        LINE_RANGE -> jetbrains.letsPlot.Geom.linerange()
        POINT -> jetbrains.letsPlot.Geom.point()
        POINT_RANGE -> jetbrains.letsPlot.Geom.pointrange()
        RASTER -> jetbrains.letsPlot.Geom.raster()
        RECT -> jetbrains.letsPlot.Geom.rect()
        RIBBON -> jetbrains.letsPlot.Geom.ribbon()
        SEGMENT -> jetbrains.letsPlot.Geom.segment()
        STEP -> jetbrains.letsPlot.Geom.step()
        TILE -> jetbrains.letsPlot.Geom.tile()
        else -> TODO()
    }
}


internal fun Scale.wrap(
    aes: AesName,
    scaleParameters: ScaleParameters? = null
): jetbrains.letsPlot.intern.Scale? {
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

                            )
                        Y -> scaleYContinuous(
                            limits = limits.toLP(),

                            name = name,
                            breaks = breaks?.map { it as Number }, // TODO() }
                            labels = labels,

                            )
                        else -> TODO()
                    }
                }
                is CustomScale -> TODO()
            }
        }

        is NonPositionalScale<*, *> -> {
            val legend = (scaleParameters as NonPositionalParameters<*, *>?)?.legend

            val name = legend?.name
            val breaks = legend?.breaks
            val labels = legend?.labels
            val legendType = legend?.type?.let {
                when (it) {
                    is None -> null
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
                            values = rangeValues?.map { (it as LetsPlotLineType).codeNumber }
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
                                values = rangeValues!!.map { (it as LetsPlotSymbol).shape },

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
                    is ScaleContinuousColorHue<*> -> scaleColorHue(
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
                    is ScaleContinuousColorGradient2<*> -> scaleColorGradient2(
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
                    is ScaleContinuousColorGradientN<*> -> scaleColorGradientN(
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
                is DefaultScale -> {
                    // TODO
                    return null
                }

                else -> TODO()
            }
        }

        is DefaultScale -> {
            // TODO
            return null
        }
        else -> TODO()
    }

}

// TODO
internal fun Pair<Any, Any>?.toLP() = this?.let { (it.first as Number) to (it.second as Number) }

internal fun Layer.wrap(featureBuffer: MutableList<Feature>) {
    featureBuffer.add(LayerWrapper(this))
    mappings.forEach { (aes, mapping) ->
        if (mapping is ScaledMapping<*>) {
            mapping.sourceScaled.scale.wrap(aes, mapping.scaleParameters)?.let {
                featureBuffer.add(it)
            }
        }
    }
}

internal fun LetsPlotLayout.wrap(featureBuffer: MutableList<Feature>) {
    featureBuffer.add(
        labs(
            title, subtitle, caption
        )
    )
    size?.let {
        featureBuffer.add(ggsize(it.first, it.second))
    }
/*
    title?.let {
        featureBuffer.add(ggtitle(it))
    }
    */
}

fun Plot.toLetsPlot(): jetbrains.letsPlot.intern.Plot {
    val featureBuffer = buildList {
        layers.forEach { it.wrap(this) }
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

