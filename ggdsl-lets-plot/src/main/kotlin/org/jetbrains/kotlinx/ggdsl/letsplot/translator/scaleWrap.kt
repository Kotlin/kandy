package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
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
    typeOf<Instant>(), typeOf<LocalDateTime>(), typeOf<LocalDate>()
)

/**
 * TODO
 * 1) Unspecified
 * 2) DateTime
 * 3) trans = (transform as? Transformation)?.name
 */
internal fun Scale.wrap(
    aesName: AesName,
    domainType: KType,
    scaleParameters: ScaleParameters? = null
): org.jetbrains.letsPlot.intern.Scale? {
    return when (this) {
        is PositionalScale<*> -> {
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
                            limits = categories?.values,
                            name = name,
                            breaks = breaks?.values,
                            labels = labels,
                            format = format
                        )

                        Y -> scaleYDiscrete(
                            limits = categories?.values,
                            name = name,
                            breaks = breaks?.values,
                            labels = labels,
                            format = format
                        )

                        else -> TODO("error")
                    }
                }

                is PositionalContinuousScale<*> -> {
                    when (aesName) {
                        X -> if (domainType !in dateTimeTypes) {
                            scaleXContinuous(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.values?.map { it as Number }, // TODO() }
                                labels = labels,
                                trans = (transform as? Transformation)?.name,
                                format = format
                            )
                        } else {
                            scaleXDateTime(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.values,
                                labels = labels,
                                format = format
                            )
                        }

                        Y -> if (domainType !in dateTimeTypes) {
                            scaleYContinuous(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.values?.map { it as Number }, // TODO() }
                                labels = labels,
                                trans = (transform as? Transformation)?.name,
                                format = format
                            )
                        } else {
                            scaleYDateTime(
                                limits = limits.wrap(),
                                name = name,
                                breaks = breaks?.values,
                                labels = labels,
                                format = format
                            )
                        }

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
                        // todo check all
                        SIZE/*, WIDTH, STROKE*/ -> scaleSizeManual(
                            values = rangeValues?.values?.map { it as Number } ?: TODO("default scale size discrete"),
                            limits = domainCategories?.values,
                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType,
                            format = format

                        )

                        COLOR -> {
                            if (rangeValues == null) {
                                scaleColorDiscrete(
                                    limits = domainCategories?.values,
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType,
                                    format = format

                                )
                            } else {
                                scaleColorManual(
                                    limits = domainCategories?.values,
                                    values = rangeValues!!.values.map { (it as Color).wrap() }, //todo
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType,
                                    format = format

                                )
                            }
                        }

                        FILL -> {
                            if (rangeValues == null) {
                                scaleFillDiscrete(
                                    limits = domainCategories?.values,
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType,
                                    format = format

                                )
                            } else {
                                scaleFillManual(
                                    limits = domainCategories?.values,
                                    values = rangeValues!!.values.map { (it as Color).wrap() },
                                    name = name,
                                    breaks = breaks,
                                    labels = labels,
                                    guide = legendType,
                                    format = format

                                )
                            }
                        }
                        // TODO
                        ALPHA -> scaleAlphaManual(
                            limits = domainCategories?.values,
                            values = rangeValues?.values?.map { it as Double } ?: TODO("default scale alpha discrete"),

                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType,
                            format = format

                        )

                        LINE_TYPE -> scaleLinetypeManual(
                            limits = domainCategories?.values,
                            values = rangeValues?.values?.map { (it as LineType).codeNumber }
                                ?: TODO("default scale alpha discrete"),

                            name = name,
                            breaks = breaks,
                            labels = labels,
                            guide = legendType,
                            format = format

                        )

                        SHAPE -> if (rangeValues == null) {
                            scaleShape(
                                limits = domainCategories?.values,
                                name = name,
                                breaks = breaks,
                                labels = labels,
                                guide = legendType,
                                format = format
                            )
                        } else {
                            scaleShapeManual(
                                limits = domainCategories?.values,
                                values = rangeValues!!.values.map { (it as Symbol).shape },

                                name = name,
                                breaks = breaks,
                                labels = labels,
                                guide = legendType,
                                format = format
                            )
                        }

                        else -> TODO()
                    }
                }

                is NonPositionalContinuousScale<*, *> -> {
                    when (aesName) {
                        // todo check all
                        SIZE /*, WIDTH, STROKE */ -> scaleSize(
                            limits = domainLimits.wrap(),
                            range = rangeLimits.wrap(),

                            name = name,
                            breaks = breaks?.map { it as Number },
                            labels = labels,
                            guide = legendType,

                            trans = (transform as Transformation?)?.name,
                            format = format
                        )


                        COLOR -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.first as? Color)?.wrap() to (it?.second as? Color)?.wrap()
                            }
                            val limits = domainLimits.wrap() // todo datetime here

                            scaleColorContinuous(
                                low = lowColor,
                                high = highColor,
                                limits = limits,

                                name = name,
                                breaks = breaks?.map { it as Number },
                                labels = labels,
                                guide = legendType,

                                trans = (transform as Transformation?)?.name,
                                format = format

                            )

                        }

                        FILL -> {
                            val (lowColor, highColor) = rangeLimits.let {
                                (it?.first as? Color)?.wrap() to (it?.second as? Color)?.wrap()
                            }
                            val limits = domainLimits.wrap() // todo datetime here

                            scaleFillContinuous(
                                low = lowColor,
                                high = highColor,
                                limits = limits,

                                name = name,
                                breaks = breaks?.map { it as Number },
                                labels = labels,
                                guide = legendType,

                                trans = (transform as Transformation?)?.name,
                                format = format

                            )

                        }


                        // TODO
                        ALPHA -> scaleAlpha(
                            limits = domainLimits.wrap(),
                            range = rangeLimits.wrap(),

                            name = name,
                            breaks = breaks?.map { it as Number },
                            labels = labels,
                            guide = legendType,

                            trans = (transform as Transformation?)?.name,
                            format = format

                        ) // TODO
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
                            format = format
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
                            format = format
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
                            format = format
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
                            format = format
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
                            format = format
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
                            format = format
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
                            format = format
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
                            format = format
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
                            format = format
                        )

                        FILL -> scaleFillGradientN(
                            rangeColors.map { it.wrap() },
                            name = name,
                            breaks = breaks?.map { it as Number }, // todo
                            labels = labels,
                            guide = legendType,
                            limits = domainLimits.wrap(),
                            trans = transform?.name,
                            format = format
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
                DefaultUnspecifiedScale -> when (aesName) {
                    // todo other types for unspecified categorical???

                    X, Y -> if (domainType == typeOf<String>()) {
                        PositionalCategoricalUnspecifiedScale.wrap(aesName, domainType, scaleParameters)
                    } else {
                        PositionalContinuousUnspecifiedScale().wrap(aesName, domainType, scaleParameters)
                    }

                    COLOR, FILL, SIZE, SHAPE, LINE_TYPE -> if (domainType == typeOf<String>()) {
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
                            NonPositionalCategoricalScale<Any, Any>()
                                .wrap(aesName, domainType, scaleParameters)

                        is NonPositionalContinuousUnspecifiedScale ->
                            NonPositionalContinuousScale<Any, Any>(transform = transform)
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

                    if (domainType in dateTimeTypes) {
                        return when (aesName) {
                            X -> scaleXDateTime(
                                //  limits = limits.toLP(),
                                name = name,
                                breaks = breaks?.values?.map { it as Number }, // TODO() }
                                labels = labels,
                                //   trans = (transform as? Transformation)?.name,
                                format = format
                            )

                            Y -> scaleYDateTime(
                                //limits = categories,
                                name = name,
                                breaks = breaks?.values,
                                labels = labels,
                                format = format
                            )

                            else -> TODO()
                        }
                    }
                    when (this) {
                        PositionalCategoricalUnspecifiedScale -> when (aesName) {
                            X -> scaleXDiscrete(name = name, breaks = breaks?.values, labels = labels, format = format)
                            Y -> scaleYDiscrete(name = name, breaks = breaks?.values, labels = labels, format = format)
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
