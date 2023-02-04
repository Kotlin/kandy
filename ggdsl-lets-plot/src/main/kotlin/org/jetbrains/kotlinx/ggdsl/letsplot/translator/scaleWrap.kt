package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import jetbrains.datalore.plot.base.Aes
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.*
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.ColorBar
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.DiscreteLegend
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.None
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

internal fun List<*>?.wrap() = this?.filterNotNull()
internal val timeTypes = setOf(
    typeOf<LocalTime>(),  typeOf<LocalTime?>(),
)

/**
 * TODO datetime
 */
internal fun Scale.wrap(
    aesName: AesName,
    domainType: KType,
    scaleParameters: ScaleParameters? = null,
    isGroupKey: Boolean = false,
): org.jetbrains.letsPlot.intern.Scale? {
    return when (this) {
        is PositionalScale<*> -> {
            val nullValue = if(this is ContinuousScale) {
                nullValue
            } else {
                null //(this as PositionalCategoricalScale<*>).c
            }
            val naValue = nullValue?.wrap()
            val axis = (scaleParameters as PositionalParameters<*>?)?.axis

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
                            limits = categories?.values.wrap(),
                            name = name,
                            breaks = breaks?.values.wrap(),
                            labels = labels,
                            format = format,
                            naValue = naValue as? Number
                        )

                        Y -> scaleYDiscrete(
                            limits = categories?.values.wrap(),
                            name = name,
                            breaks = breaks?.values.wrap(),
                            labels = labels,
                            format = format,
                            naValue = naValue as? Number
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
                                breaks = breaks?.values?.filterNotNull(), // todo
                                labels = labels,
                                format = format
                            )
                        } else if (domainType in timeTypes) {
                            scaleXTime(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.values?.filterNotNull(), // todo
                                labels = labels,
                               // format = format
                            )
                        } else {
                            scaleXContinuous(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.values?.map { it as Number }, // TODO() }
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
                                breaks = breaks?.values.wrap(),
                                labels = labels,
                                format = format,
                                naValue = naValue
                            )
                        } else if (domainType in timeTypes) {
                            scaleYTime(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.values?.filterNotNull(), // todo
                                labels = labels,
                                // format = format
                            )
                        } else {
                            scaleYContinuous(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.values?.map { it as Number }, // TODO() }
                                labels = labels,
                                trans = (transform as? Transformation)?.name,
                                format = format,
                                naValue = naValue as? Number
                            )
                        }

                        else -> TODO()
                    }
                }

                // is CustomScale -> TODO()
            }
        }

        is NonPositionalScale<*, *> -> {
            val naValue = if(this is ContinuousScale) {
                nullValue?.wrap()
            } else {
                (this as NonPositionalCategoricalScale<*, *>).domainCategories?.values?.indexOf(null)?.let {
                    rangeValues?.values?.get(it)?.let {
                        wrapValue(it)
                    }
                }
                //null //(this as PositionalCategoricalScale<*>).c
            }
            //val naValue = nullValue?.wrap()
            val legend = (scaleParameters as NonPositionalParameters<*, *>?)?.legend

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
                is NonPositionalCategoricalScale<*, *> -> {
                    when (aesName) {
                        SIZE -> if (rangeValues != null) {scaleSizeManual(
                            values = rangeValues!!.values.map { it as Number },
                            limits = domainCategories?.values.wrap(),
                            name = name,
                            breaks = breaks?.values.wrap(),
                            labels = labels,
                            guide = legendType,
                            format = format,
                            naValue = naValue as? Number
                        )} else {
                            org.jetbrains.letsPlot.intern.Scale(
                                Aes.SIZE,
                                limits = domainCategories?.values.wrap(),
                                name = name,
                                breaks = breaks?.values.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue as? Number
                            )
                        }

                        COLOR -> {
                            if (rangeValues == null) {
                                scaleColorDiscrete(
                                    limits = domainCategories?.values.wrap(),
                                    name = name,
                                    breaks = breaks?.values.wrap(),
                                    labels = labels,
                                    guide = legendType,
                                    format = format,
                                    naValue = naValue
                                )
                            } else {
                                scaleColorManual(
                                    limits = domainCategories?.values.wrap(),
                                    values = rangeValues!!.values.map { (it as Color).wrap() }, //todo
                                    name = name,
                                    breaks = breaks?.values.wrap(),
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
                                    limits = domainCategories?.values.wrap(),
                                    name = name,
                                    breaks = breaks?.values.wrap(),
                                    labels = labels,
                                    guide = legendType,
                                    format = format,
                                    naValue = naValue
                                )
                            } else {
                                scaleFillManual(
                                    limits = domainCategories?.values.wrap(),
                                    values = rangeValues!!.values.map { (it as Color).wrap() },
                                    name = name,
                                    breaks = breaks?.values.wrap(),
                                    labels = labels,
                                    guide = legendType,
                                    format = format,
                                    naValue = naValue
                                )
                            }
                        }
                        // TODO

                        ALPHA -> if (rangeValues != null) { scaleAlphaManual(
                            limits = domainCategories?.values.wrap(),
                            values = rangeValues!!.values.map { it as Double },
                            name = name,
                            breaks = breaks?.values.wrap(),
                            labels = labels,
                            guide = legendType,
                            format = format,
                            naValue = naValue as? Number
                        )} else {
                            org.jetbrains.letsPlot.intern.Scale(
                                Aes.ALPHA,
                                limits = domainCategories?.values.wrap(),
                                name = name,
                                breaks = breaks?.values.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                        }

                        LINE_TYPE -> if (rangeValues != null) {
                            scaleLinetypeManual(
                                limits = domainCategories?.values.wrap(),
                                values = rangeValues!!.values.map { (it as LineType).codeNumber },
                                name = name,
                                breaks = breaks?.values.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                            // TODO
                        } else {
                            org.jetbrains.letsPlot.intern.Scale(
                                Aes.LINETYPE,
                                limits = domainCategories?.values.wrap(),
                                name = name,
                                breaks = breaks?.values.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                        }

                        SHAPE -> if (rangeValues == null) {
                            scaleShape(
                                limits = domainCategories?.values.wrap(),
                                name = name,
                                breaks = breaks?.values.wrap(),
                                labels = labels,
                                guide = legendType,
                                format = format,
                                naValue = naValue
                            )
                        } else {
                            scaleShapeManual(
                                limits = domainCategories?.values.wrap(),
                                values = rangeValues!!.values.map { (it as Symbol).shape },

                                name = name,
                                breaks = breaks?.values.wrap(),
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
                            breaks = breaks?.values?.map { it as Number },
                            labels = labels,
                            guide = legendType,
                            trans = (transform as Transformation?)?.name,
                            format = format,
                            naValue = naValue as? Number
                        )


                        COLOR -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.first?.value as? Color)?.wrap() to (it?.second?.value as? Color)?.wrap()
                            }
                            val limits = domainLimits.wrap() // todo datetime support here

                            scaleColorContinuous(
                                low = lowColor,
                                high = highColor,
                                limits = limits,
                                name = name,
                                breaks = breaks?.values?.map { it as Number },
                                labels = labels,
                                guide = legendType,
                                trans = (transform as Transformation?)?.name,
                                format = format,
                                naValue = naValue

                            )

                        }

                        FILL -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.first?.value as? Color)?.wrap() to (it?.second?.value as? Color)?.wrap()
                            }
                            val limits = domainLimits.wrap() //todo datetime support here

                            scaleFillContinuous(
                                low = lowColor,
                                high = highColor,
                                limits = limits,
                                name = name,
                                breaks = breaks?.values?.map { it as Number },
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
                            breaks = breaks?.values?.map { it as Number },
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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
                            breaks = breaks?.values?.map { it as Number }, // todo
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

        is UnspecifiedScale -> {
            when (this) {
                DefaultUnspecifiedScale -> when (aesName) {

                    X, Y -> if (domainType.isCategoricalType() || isGroupKey) {
                        PositionalCategoricalUnspecifiedScale.wrap(aesName, domainType, scaleParameters)
                    } else {
                        PositionalContinuousUnspecifiedScale().wrap(aesName, domainType, scaleParameters)
                    }

                    COLOR, FILL, SIZE, SHAPE, LINE_TYPE -> if (domainType.isCategoricalType() || isGroupKey) {
                        NonPositionalCategoricalUnspecifiedScale.wrap(aesName, domainType, scaleParameters)
                    } else {
                        NonPositionalContinuousUnspecifiedScale().wrap(aesName, domainType, scaleParameters)
                    }

                    else -> null

                }
                // TODO!!!
                is NonPositionalUnspecifiedScale -> {
                    when (this) {
                        NonPositionalCategoricalUnspecifiedScale ->
                            NonPositionalCategoricalScale<Any, Any>(null, null,)
                                .wrap(aesName, domainType, scaleParameters)

                        is NonPositionalContinuousUnspecifiedScale ->
                            NonPositionalContinuousScale<Any, Any>(null, null, null, transform = transform)
                                .wrap(aesName, domainType, scaleParameters)
                    }
                }

                // TODO
                is PositionalUnspecifiedScale -> {
                    val axis = (scaleParameters as PositionalParameters<*>?)?.axis

                    val name = axis?.name
                    val breaks = axis?.breaks
                    val labels = axis?.labels
                    val format = axis?.format

                    // todo discrete datetime?
                    if (domainType in dateTimeTypes) {
                        return when (aesName) {
                            X -> scaleXDateTime(
                                //  limits = limits.toLP(),
                                name = name,
                                breaks = breaks?.values?.map { it as Number }, // TODO() }
                                labels = labels,
                                //   trans = (transform as? Transformation)?.name,
                                format = format,
                            )

                            Y -> scaleYDateTime(
                                //limits = categories,
                                name = name,
                                breaks = breaks?.values.wrap(),
                                labels = labels,
                                format = format,
                            )

                            else -> TODO()
                        }
                    } else if (domainType in timeTypes) {
                        return when (aesName) {
                            X -> scaleXTime(
                                //  limits = limits.toLP(),
                                name = name,
                                breaks = breaks?.values?.map { it as Number }, // TODO() }
                                labels = labels,
                                //   trans = (transform as? Transformation)?.name,
                                //  format = format
                            )

                            Y -> scaleYTime(
                                //limits = categories,
                                name = name,
                                breaks = breaks?.values.wrap(),
                                labels = labels,
                                //   format = format
                            )

                            else -> TODO()
                        }
                    }
                    when (this) {
                        PositionalCategoricalUnspecifiedScale -> when (aesName) {
                            X -> scaleXDiscrete(name = name, breaks = breaks?.values.wrap(), labels = labels, format = format)
                            Y -> scaleYDiscrete(name = name, breaks = breaks?.values.wrap(), labels = labels, format = format)
                            else -> TODO()
                        }

                        is PositionalContinuousUnspecifiedScale -> when (aesName) {
                            X -> scaleXContinuous(
                                name = name,
                                breaks = breaks?.values?.map { it as Number },
                                labels = labels,
                                trans = (transform as? Transformation)?.name,
                                format = format
                            )

                            Y -> scaleYContinuous(
                                name = name,
                                breaks = breaks?.values?.map { it as Number },
                                labels = labels,
                                trans = (transform as? Transformation)?.name,
                                format = format
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

internal val categoricalTypes = listOf(typeOf<String>(), typeOf<Boolean>(), typeOf<Char>())

// todo
internal fun KType.isCategoricalType(): Boolean {
    return this in categoricalTypes
}
