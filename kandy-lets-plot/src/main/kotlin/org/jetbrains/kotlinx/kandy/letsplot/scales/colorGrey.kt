/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales

import org.jetbrains.kotlinx.kandy.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.kandy.util.color.Color

/**
 * Sequential grey continuous color scale for color aesthetic.
 * The palette is computed using HSV (hue, saturation, value) color model.
 *
 * @param DomainType type of domain
 * @param paletteRange grey values range (between [0, 1]).
 * @param domain [ClosedRange] defining the scale domain.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 *
 * @return new continuous color scale.
 */
public inline fun <reified DomainType : Comparable<DomainType>> continuousColorGrey(
    paletteRange: ClosedRange<Double>? = null,
    domain: ClosedRange<DomainType>,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGrey<DomainType> = ScaleContinuousColorGrey(
    paletteRange?.let {
        it.start to it.endInclusive
    }, domain.let {
        it.start to it.endInclusive
    }, nullValue, transform
)

/**
 * Sequential grey color continuous scale for color aesthetic.
 * The palette is computed using HSV (hue, saturation, value) color model.
 *
 * @param DomainType type of domain
 * @param paletteRange grey values range (between [0, 1]).
 * @param domainMin scale domain minimum.
 * @param domainMax scale domain maximum.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 *
 * @return new continuous color scale.
 */
public inline fun <reified DomainType : Comparable<DomainType>> continuousColorGrey(
    paletteRange: ClosedRange<Double>? = null,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGrey<DomainType> = ScaleContinuousColorGrey(
    paletteRange?.let {
        it.start to it.endInclusive
    }, domainMin to domainMax, nullValue, transform
)

/**
 * Sequential grey color categorical scale for color aesthetic.
 * The palette is computed using HSV (hue, saturation, value) color model.
 *
 * @param DomainType type of domain
 * @param paletteRange grey values range (between [0, 1]).
 *
 * @return new categorical color scale.
 */
public inline fun <reified DomainType> categoricalColorGrey(
    paletteRange: Pair<Double, Double>? = null,
): ScaleCategoricalColorGrey<DomainType> = ScaleCategoricalColorGrey<DomainType>(
    paletteRange,
)

public sealed interface ScaleColorGrey<DomainType> {
    public val paletteRange: Pair<Double, Double>?
    public val domainLimits: Pair<DomainType?, DomainType?>?
    public val transform: Transformation?
}

public data class ScaleContinuousColorGrey<DomainType> @PublishedApi internal constructor(
    override val paletteRange: Pair<Double, Double>?,
    override val domainLimits: Pair<DomainType?, DomainType?>,
    override val nullValue: Color?,
    override val transform: Transformation?,
) : ContinuousScale<Color>, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType>

public data class ScaleCategoricalColorGrey<DomainType> @PublishedApi internal constructor(
    override val paletteRange: Pair<Double, Double>?,
    //override val nullValue: TypedValue?
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType> {
    override val domainLimits: Pair<DomainType & Any, DomainType & Any>?
        get() = null
    override val transform: Transformation?
        get() = null
}
