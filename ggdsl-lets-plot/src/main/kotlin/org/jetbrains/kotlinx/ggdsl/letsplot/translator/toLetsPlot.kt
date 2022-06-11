package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.*
import org.jetbrains.kotlinx.ggdsl.ir.Geom
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.feature.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.util.color.*
import org.jetbrains.kotlinx.ggdsl.util.symbol.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.*
import org.jetbrains.kotlinx.ggdsl.letsplot.position.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LetsPlotLineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.LetsPlotSymbol
import org.jetbrains.kotlinx.ggdsl.util.linetype.CommonLineType
import jetbrains.letsPlot.*
import jetbrains.letsPlot.intern.Feature
import jetbrains.letsPlot.intern.FeatureList
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.scale.*


internal fun LayerFeature?.wrap(): PosOptions? {
    return when(this) {
        is Position.Identity -> return Pos.identity
        is Position.Stack -> return Pos.stack
        is Position.Dodge -> return positionDodge(width)
        is Position.Jitter -> return positionJitter(width, height)
        is Position.Nudge -> return positionNudge(x, y)
        is Position.JitterDodge -> positionJitterDodge(dodgeWidth, jitterWidth, jitterHeight)
        else -> null
    }
}

internal fun Mapping.wrap(geom: Geom): Pair<String, String> {
   return when(this) {
        is NonScalablePositionalMapping<*> -> aes.toLPName(geom) to source.id
        is ScaledMapping<*> -> aes.toLPName(geom) to sourceScaled.source.id
    }
   // return aes.toLPName(geom) to source.id
}

internal fun NonPositionalSetting<*>.wrap(geom: Geom): Pair<String, Any>{
    return aes.toLPName(geom) to wrapValue(value)
}

// TODO
internal fun wrapBinding(aes: Aes, value: Any, geom: Geom): Pair<String, Any> {
    if (aes == SYMBOL) {
        val ret = "shape" to wrapValue(value) // TODO scaling
       // TODO
        return ret
    }
    return aes.toLPName(geom) to wrapValue(value)
}

// TODO
internal fun wrapValue(value: Any): Any{
    if (value is StandardColor) {
        return value.description
    }
    if (value is CommonSymbol) {
        return wrapSymbol(value)
    }
    if (value is LetsPlotSymbol) {
        return value.shape
    }
    if (value is CommonLineType) {
        return value.description
    }
    if (value is LetsPlotLineType) {
        return value.description
    }
    return value
}

internal fun commonSymbolToShape(symbol: CommonSymbol): Int {
    return when(symbol){
        Symbol.RECTANGLE -> 22
        Symbol.CIRCLE -> 21
        Symbol.TRIANGLE -> 24
        else -> TODO()
    }
}

internal fun wrapSymbol(symbol: Symbol): Int {
    return when(symbol){
        is LetsPlotSymbol -> symbol.shape
        is CommonSymbol -> commonSymbolToShape(symbol)
        else -> TODO()
    }
}

val fillGeoms = setOf(
    Geom.BAR,
    Geom.POINT,
    BOXPLOT,
    AREA,
    CROSSBAR,
    // TODO
    POINT_RANGE,
)
// TODO
internal fun Aes.toLPName(geom: Geom): String {
    if (this == SYMBOL) {
        return "shape"
    }
    if (this == LINE_TYPE){
        return "linetype"
    }
    if (geom in fillGeoms && this == COLOR) {
        return "fill"
    }

    if (this == BORDER_WIDTH) {
        if (geom == BOXPLOT) {
            return "width"
        }
        if (geom == AREA || geom == CROSSBAR) {
            return "size"
        }
        return "stroke"
    }
    if (this == BORDER_COLOR || this == MAPPABLE_BORDER_COLOR) {
        return "color"
    }
    if (geom == Geom.LINE && this == WIDTH) {
        return "size"
    }
    return name
}

// TODO rewrite
internal fun Geom?.toLPGeom(defaultShape: Boolean = true): jetbrains.letsPlot.intern.layer.GeomOptions {
    return when (this!!) {
        Geom.POINT -> {
            if (defaultShape) {
                jetbrains.letsPlot.Geom.point(shape = 21)
            } else {
                jetbrains.letsPlot.Geom.point()
            }
        }
        Geom.BAR -> jetbrains.letsPlot.Geom.bar()
        Geom.LINE -> jetbrains.letsPlot.Geom.path()
        BOXPLOT -> jetbrains.letsPlot.Geom.boxplot()
        AREA -> jetbrains.letsPlot.Geom.area()
        ERRORBAR -> jetbrains.letsPlot.Geom.errorbar()
        CROSSBAR -> jetbrains.letsPlot.Geom.crossbar()
        LINE_RANGE-> jetbrains.letsPlot.Geom.linerange()
        POINT_RANGE -> jetbrains.letsPlot.Geom.pointrange()
        else -> TODO()
    }
}


internal fun Scale.wrap(aes: Aes, geom: Geom): jetbrains.letsPlot.intern.Scale? {
    // TODO depends on geom
    return when (this) {
        is PositionalScale<*> -> {
            // TODO
            /*
            val name = axis?.name
            val breaks = axis?.breaks
            val labels = axis?.labels
             */
            when(this) {
                is PositionalCategoricalScale<*> -> {
                    when (aes) {
                        X -> scaleXDiscrete(
                            limits = categories,
               //             name = name,
               //             breaks = breaks,
                //            labels = labels,
                        )
                        Y -> scaleYDiscrete(
                            limits = categories,
                            /*
                            name = name,
                            breaks = breaks,
                            labels = labels,
                             */
                        )
                        else -> TODO("error")
                    }
                }
                is PositionalContinuousScale<*> -> {
                    when (aes) {
                        X -> scaleXContinuous(
                            limits = limits.toLP(),
                            /*
                            name = name,
                            breaks = breaks?.map {  it as Number}, // TODO() }
                            labels = labels,
                             */
                        )
                        Y -> scaleYContinuous(
                            limits = limits.toLP(),
                            /*
                            name = name,
                            breaks = breaks?.map {  it as Number}, // TODO() }
                            labels = labels,
                             */
                        )
                        else -> TODO()
                    }
                }
            }
        }

        is NonPositionalScale<*, *> -> {
            /*
            val legend = legend as? LetsPlotLegend<*, *>
            val name = legend?.name
            val breaks = legend?.breaks
            val labels = legend?.labels
            val legendType = legend?.legendType?.let {
                when(it) {
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

             */

            when(this) {
                is NonPositionalCategoricalScale<*, *> -> {
                    when (aes) {
                        SIZE -> scaleSizeManual(
                            values = rangeValues?.map { it as Number } ?:
                            TODO("default scale size discrete"),
                            /*
                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType
                             */
                        )
                        // todo lp aes
                        COLOR -> {
                            if (geom !in fillGeoms) {
                                return scaleColorManual(
                                    values = rangeValues!!.map { (it as StandardColor).description },
                                )
                            }
                            return if (rangeValues?.isEmpty() == true) {
                                scaleFillDiscrete(
                                    /*
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType

                                     */
                                )
                            } else {
                                scaleFillManual(
                                    values = rangeValues!!.map { (it as StandardColor).description },
                                    /*    name = name,
                                        breaks = breaks,
                                        labels = labels,
                                        guide = legendType

                                     */
                                )
                            }
                        }
                        // TODO
                        ALPHA -> scaleAlphaManual(
                            values = rangeValues?.map { it as Double } ?:
                            TODO("default scale size discrete"),
                            /*
                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType

                             */
                        ) // TODO
                        SYMBOL -> scaleShapeManual(
                            values = rangeValues?.map { wrapSymbol(it as Symbol) } ?:
                            TODO("default scale size discrete"),
                            /*
                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType

                             */
                        )
                        MAPPABLE_BORDER_COLOR -> scaleColorManual(
                            values = rangeValues?.map { wrapValue(it as Color) } ?:
                            TODO("default scale size discrete"),
                        )
                        else -> TODO()
                    }
                }
                is NonPositionalContinuousScale<*, *> -> {
                    when (aes) {
                        SIZE -> scaleSize(
                            limits = domainLimits.toLP(),
                            range = rangeLimits.toLP(),

/*
                            name = name,
                            breaks = breaks?.map {
                                it as Number },
                            labels = labels,
                            guide = legendType

 */
                        )



                        COLOR, MAPPABLE_BORDER_COLOR -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.first as? StandardColor)?.description to (it?.second as? StandardColor)?.description
                            }
                            val limits = domainLimits.toLP() // todo datetime here
                            if (aes == COLOR && geom in fillGeoms){
                                scaleFillContinuous(
                                    low = lowColor,
                                    high = highColor,
                                    limits = limits,
                                    /*
                                    name = name,
                                    breaks = breaks?.map { it as Number },
                                    labels = labels,
                                    guide = legendType

                                     */
                                )
                            } else {
                                scaleColorContinuous(
                                    low = lowColor,
                                    high = highColor,
                                    limits = limits,
                                    /*
                                    name = name,
                                    breaks = breaks?.map { it as Number },
                                    labels = labels,
                                    guide = legendType

                                     */
                                )
                            }
                        }   // TODO
                        ALPHA -> scaleAlpha(
                            limits = domainLimits.toLP(),
                            range = rangeLimits.toLP(),
                            /*
                            name = name,
                            breaks = breaks?.map { it as Number },
                            labels = labels,
                            guide = legendType

                             */
                        ) // TODO
                        SYMBOL -> TODO("cant apply contunuous scale")
                        else -> TODO()
                    }
                }
                is DefaultScale -> {
                    // TODO add default recognition for guides???
                    return null
                }
                else -> TODO()
            }
        }

        is DefaultScale -> {
            // TODO add default recognition for guides???
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
            mapping.sourceScaled.scale.wrap(aes, geom)?.let {
                featureBuffer.add(it)
            }
        }
    }
    //return featureBuffer
}

internal fun Layout.wrap(featureBuffer: MutableList<Feature>) {
    size?.let {
        featureBuffer.add(ggsize(it.first, it.second))
    }
    title?.let {
        featureBuffer.add(ggtitle(it))
    }
}

fun Plot.toLestPlot(): jetbrains.letsPlot.intern.Plot {
    val featureBuffer = buildList {
        layers.forEach { it.wrap(this) }
        features.forEach { it.value.wrap(this) }
        layout.wrap(this)
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

