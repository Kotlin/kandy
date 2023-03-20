package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import jetbrains.datalore.plot.base.Aes
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.*
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.letsPlot.scale.*
import kotlin.reflect.KType
import kotlin.reflect.typeOf

internal val dateTimeTypes = setOf(
    typeOf<Instant>(), typeOf<LocalDateTime>(), typeOf<LocalDate>(),
    typeOf<Instant?>(), typeOf<LocalDateTime?>(), typeOf<LocalDate?>()
)

internal val discreteAes = setOf(
    SHAPE, LINE_TYPE
)

internal fun List<*>?.wrap() = this?.filterNotNull()
internal val timeTypes = setOf(
    typeOf<LocalTime>(), typeOf<LocalTime?>(),
)

internal fun Mapping.wrapScale(domainType: KType, groupKeys: List<String>?): org.jetbrains.letsPlot.intern.Scale? {
    return parameters?.scale?.wrap(
        aes, domainType,
        (parameters as? LetsPlotPositionalMappingParameters<*>)?.axis
            ?: (parameters as? LetsPlotNonPositionalMappingParameters<*, *>)?.legend,
        groupKeys?.contains(columnID) ?: false
    )
}

/**
 * TODO datetime
 */
internal fun Scale.wrap(
    aesName: AesName,
    domainType: KType,
    scaleParameters: ScaleParameters?,
    isGroupKey: Boolean,
): org.jetbrains.letsPlot.intern.Scale {
    return when (this) {

        is PositionalScale<*> -> {
            val naValue = if (this is ContinuousScale<*>) {
                wrapValue(nullValue)
            } else {
                null //(this as PositionalCategoricalScale<*>).c
            }
            val axis = scaleParameters as? Axis<*>?

            val name = axis?.name
            val breaks = axis?.breaks
            val labels = axis?.labels
            val format = axis?.format

            // todo discrete datetime
            /*
            if (domainType in dateTimeTypes) {
                return when (aesName) {
                    X -> scaleXDateTime(
                        //  limits = limits.toLP(),
                        name = name,
                        breaks = breaks?.map { it as Number }, // TODO() }
                        labels = labels,
                        //   trans = (transform as? Transformation)?.name,
                        format = format
                    )

                    Y -> scaleYDateTime(
                        //limits = categories,
                        name = name,
                        breaks = breaks,
                        labels = labels,
                        format = format
                    )

                    else -> TODO()
                }
            }

             */

            when (this) {
                is PositionalCategoricalScale<*> -> {
                    when (aesName) {
                        X -> scaleXDiscrete(
                            limits = categories?.wrap(),
                            name = name,
                            breaks = breaks?.wrap(),
                            labels = labels,
                            format = format,
                            // naValue = nullValue as? Number
                        )

                        Y -> scaleYDiscrete(
                            limits = categories?.wrap(),
                            name = name,
                            breaks = breaks?.wrap(),
                            labels = labels,
                            format = format,
                            //  naValue = nullValue as? Number
                        )

                        else -> TODO("error")
                    }
                }

                is PositionalContinuousScale<*> -> {
                    when (aesName) {
                        X -> if (domainType in dateTimeTypes) {
                            scaleXDateTime(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.filterNotNull(), // todo
                                labels = labels,
                                format = format
                            )
                        } else if (domainType in timeTypes) {
                            scaleXTime(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.filterNotNull(), // todo
                                labels = labels,
                                // format = format
                            )
                        } else {
                            scaleXContinuous(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.map { it as Number }, // TODO() }
                                labels = labels,
                                trans = (transform as? Transformation)?.name,
                                format = format,
                                naValue = naValue as? Number
                            )
                        }

                        Y -> if (domainType in dateTimeTypes) {
                            scaleYDateTime(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                format = format,
                                naValue = naValue
                            )
                        } else if (domainType in timeTypes) {
                            scaleYTime(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.filterNotNull(), // todo
                                labels = labels,
                                // format = format
                            )
                        } else {
                            scaleYContinuous(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.map { it as Number }, // TODO() }
                                labels = labels,
                                trans = (transform as? Transformation)?.name,
                                format = format,
                                naValue = naValue as? Number
                            )
                        }

                        else -> TODO()
                    }
                }

                is PositionalDefaultScale<*> -> if (domainType.isCategoricalType() || isGroupKey) {
                    PositionalCategoricalScale<String>(null).wrap(aesName, domainType, scaleParameters, isGroupKey)
                } else {
                    PositionalContinuousScale<Double>(null, null, null).wrap(
                        aesName,
                        domainType,
                        scaleParameters,
                        isGroupKey
                    )
                }
            }
        }

        is NonPositionalScale<*, *> -> {
            val naValue = if (this is ContinuousScale<*>) {
                wrapValue(nullValue)
            } else {
                // todo custom scales
                (this as? NonPositionalCategoricalScale<*, *>)?.domainCategories?.indexOf(null)?.let {
                    if (it == -1) {
                        null
                    } else rangeValues?.get(it)?.let {
                        wrapValue(it)
                    }
                }
                //null //(this as PositionalCategoricalScale<*>).c
            }
            //val naValue = nullValue?.wrap()
            val legend = scaleParameters as? Legend<*, *>?

            val name = legend?.name
            val breaks = legend?.breaks
            val labels = legend?.labels
            val format = legend?.format
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
                is NonPositionalDefaultScale<*, *> -> if (domainType.isCategoricalType() || aesName in discreteAes || isGroupKey) {
                    NonPositionalCategoricalScale<String, String>(null, null).wrap(
                        aesName,
                        domainType,
                        scaleParameters,
                        isGroupKey
                    )
                } else {
                    NonPositionalContinuousScale<Double, Double>(null, null, null, null).wrap(
                        aesName,
                        domainType,
                        scaleParameters,
                        isGroupKey
                    )
                }

                is NonPositionalCategoricalScale<*, *> -> {
                    when (aesName) {
                        SIZE -> if (rangeValues != null) {
                            scaleSizeManual(
                                values = rangeValues!!.map { it as Number },
                                limits = domainCategories?.wrap(),
                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue as? Number
                            )
                        } else {
                            org.jetbrains.letsPlot.intern.Scale(
                                Aes.SIZE,
                                limits = domainCategories?.wrap(),
                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue as? Number
                            )
                        }

                        COLOR -> {
                            if (rangeValues == null) {
                                scaleColorDiscrete(
                                    limits = domainCategories?.wrap(),
                                    name = name,
                                    breaks = breaks?.wrap(),
                                    labels = labels,
                                    guide = legendType,
                                    format = format,
                                    naValue = naValue
                                )
                            } else {
                                scaleColorManual(
                                    limits = domainCategories?.wrap(),
                                    values = rangeValues!!.map { (it as Color).wrap() }, //todo
                                    name = name,
                                    breaks = breaks?.wrap(),
                                    labels = labels,
                                    guide = legendType,
                                    format = format,
                                    naValue = naValue
                                )
                            }
                        }

                        FILL -> {
                            if (rangeValues == null) {
                                scaleFillDiscrete(
                                    limits = domainCategories?.wrap(),
                                    name = name,
                                    breaks = breaks?.wrap(),
                                    labels = labels,
                                    guide = legendType,
                                    format = format,
                                    naValue = naValue
                                )
                            } else {
                                scaleFillManual(
                                    limits = domainCategories?.wrap(),
                                    values = rangeValues!!.map { (it as Color).wrap() },
                                    name = name,
                                    breaks = breaks?.wrap(),
                                    labels = labels,
                                    guide = legendType,
                                    format = format,
                                    naValue = naValue
                                )
                            }
                        }
                        // TODO

                        ALPHA -> if (rangeValues != null) {
                            scaleAlphaManual(
                                limits = domainCategories?.wrap(),
                                values = rangeValues!!.map { it as Double },
                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue as? Number
                            )
                        } else {
                            org.jetbrains.letsPlot.intern.Scale(
                                Aes.ALPHA,
                                limits = domainCategories?.wrap(),
                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                        }

                        LINE_TYPE -> if (rangeValues != null) {
                            scaleLinetypeManual(
                                limits = domainCategories?.wrap(),
                                values = rangeValues!!.map { (it as LineType).codeNumber },
                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                            // TODO
                        } else {
                            org.jetbrains.letsPlot.intern.Scale(
                                Aes.LINETYPE,
                                limits = domainCategories?.wrap(),
                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                        }

                        SHAPE -> if (rangeValues == null) {
                            scaleShape(
                                limits = domainCategories?.wrap(),
                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                        } else {
                            scaleShapeManual(
                                limits = domainCategories?.wrap(),
                                values = rangeValues!!.map { (it as Symbol).shape },

                                name = name,
                                breaks = breaks?.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                        }

                        else -> TODO()
                    }
                }

                is NonPositionalContinuousScale<*, *> -> {
                    when (aesName) {

                        SIZE -> scaleSize(
                            limits = domainLimits.wrap(),
                            range = rangeLimits.wrap(),
                            name = name,
                            breaks = breaks?.map { it as Number },
                            labels = labels,
                            guide = legendType,
                            trans = (transform as Transformation?)?.name,
                            format = format,
                            naValue = naValue as? Number
                        )


                        COLOR -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.start as? Color)?.wrap() to (it?.endInclusive as? Color)?.wrap()
                            }
                            val limits = domainLimits.wrap() // todo datetime support here

                            scaleColorContinuous(
                                low = lowColor,
                                high = highColor,
                                limits = limits,
                                name = name,
                                breaks = breaks?.map { it as Number },
                                labels = labels,
                                guide = legendType,
                                trans = (transform as Transformation?)?.name,
                                format = format,
                                naValue = naValue

                            )

                        }

                        FILL -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.start as? Color)?.wrap() to (it?.endInclusive as? Color)?.wrap()
                            }
                            val limits = domainLimits.wrap() //todo datetime support here

                            scaleFillContinuous(
                                low = lowColor,
                                high = highColor,
                                limits = limits,
                                name = name,
                                breaks = breaks?.map { it as Number },
                                labels = labels,
                                guide = legendType,
                                trans = (transform as Transformation?)?.name,
                                format = format,
                                naValue = naValue
                            )

                        }

                        ALPHA -> scaleAlpha(
                            limits = domainLimits.wrap(),
                            range = rangeLimits.wrap(),
                            name = name,
                            breaks = breaks?.map { it as Number },
                            labels = labels,
                            guide = legendType,
                            trans = (transform as Transformation?)?.name,
                            format = format,
                            naValue = naValue as? Number
                        )
                        // TODO  SYMBOL -> TODO("cant apply contunuous scale")
                        else -> TODO()
                    }
                }

                is CustomScale -> when (this) {
                    is ScaleColorGrey<*> -> when (aesName) {
                        COLOR -> scaleColorGrey(
                            paletteRange?.first,
                            paletteRange?.second,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        FILL -> scaleFillGrey(
                            paletteRange?.first,
                            paletteRange?.second,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        else -> TODO()
                    }

                    is ScaleColorHue<*> -> when (aesName) {
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
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
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
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        else -> TODO()
                    }

                    is ScaleColorBrewer<*> -> when (aesName) {
                        COLOR -> scaleColorBrewer(
                            type = type?.name,
                            palette = type?.palette?.name,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = limits,
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        FILL -> scaleFillBrewer(
                            type = type?.name,
                            palette = type?.palette?.name,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = limits,
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        else -> TODO()
                    }

                    is ScaleContinuousColorGradient2<*> -> when (aesName) {
                        COLOR -> scaleColorGradient2(
                            low.wrap(),
                            mid.wrap(),
                            high.wrap(),
                            midpoint,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        FILL -> scaleFillGradient2(
                            low.wrap(),
                            mid.wrap(),
                            high.wrap(),
                            midpoint,
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        else -> TODO()
                    }

                    is ScaleContinuousColorGradientN<*> -> when (aesName) {
                        COLOR -> scaleColorGradientN(
                            rangeColors.map { it.wrap() },
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        FILL -> scaleFillGradientN(
                            rangeColors.map { it.wrap() },
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format,
                            naValue = naValue
                        )

                        else -> TODO()
                    }

                    else -> TODO()
                }

                else -> TODO()
            }
        }

        else -> TODO("error")
    }

}

internal val categoricalTypes = listOf(typeOf<String>(), typeOf<Boolean>(), typeOf<Char>())

// todo
internal fun KType.isCategoricalType(): Boolean {
    return this in categoricalTypes
}
