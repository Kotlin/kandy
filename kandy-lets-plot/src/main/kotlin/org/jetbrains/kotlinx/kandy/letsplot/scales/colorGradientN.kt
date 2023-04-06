/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales

import org.jetbrains.kotlinx.kandy.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.kandy.util.color.Color

// todo categorical and nullable

public data class ScaleContinuousColorGradientN<DomainType>(
    val domainLimits: Pair<DomainType?, DomainType?>?,
    val rangeColors: List<Color>,
    override val nullValue: Color?,
    override val transform: Transformation?
) : ContinuousScale<Color>, CustomNonPositionalScale<DomainType, Color>

/**
 * Creates smooth color gradient between multiple colors.
 *
 * @param DomainType type of domain
 * @param gradientColors gradient color [List].
 * @param domain [ClosedRange] defining the scale domain.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 *
 * @return new continuous color scale.
 */
public fun <DomainType : Comparable<DomainType>> continuousColorGradientN(
    gradientColors: List<Color>,
    domain: ClosedRange<DomainType>,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGradientN<DomainType> = ScaleContinuousColorGradientN(
    domain.let {
        it.start to it.endInclusive
    }, gradientColors, nullValue, transform
)

/**
 * Creates smooth color gradient between multiple colors.
 *
 * @param DomainType type of domain
 * @param gradientColors gradient color [List].
 * @param domainMin scale domain minimum.
 * @param domainMax scale domain maximum.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 *
 * @return new continuous color scale.
 */
public fun <DomainType> continuousColorGradientN(
    gradientColors: List<Color>,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGradientN<DomainType> = ScaleContinuousColorGradientN(
    domainMin to domainMax, gradientColors, nullValue, transform
)

public data class ScaleContinuousColorGradient2<DomainType>(
    val domainLimits: Pair<DomainType?, DomainType?>?,
    val low: Color,
    val mid: Color,
    val high: Color,
    val midpoint: Double,
    override val nullValue: Color?,
    override val transform: Transformation?
) : ContinuousScale<Color>, CustomNonPositionalScale<DomainType, Color>

/**
 * Creates diverging color gradient (low-mid-high) for color aesthetic.
 *
 * @param DomainType type of domain
 * @param low color, scale range minimum.
 * @param mid color, corresponding to the midpoint.
 * @param high color, scale range maximum.
 * @param midpoint point on scale domain which is mapped to [mid] color.
 * @param domain segment defining the domain
 * @param transform the transformation of scale
 * @return new continuous color scale.
 */
public fun <DomainType : Comparable<DomainType>> continuousColorGradient2(
    low: Color,
    mid: Color,
    high: Color,
    midpoint: Double,
    domain: ClosedRange<DomainType>,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGradient2<DomainType> = ScaleContinuousColorGradient2(
    domain.let {
        it.start to it.endInclusive
    }, low, mid, high, midpoint, nullValue, transform
)

/**
 * Creates diverging color gradient (low-mid-high) for color aesthetic.
 *
 * @param DomainType type of domain
 * @param low color, scale range minimum.
 * @param mid color, corresponding to the midpoint.
 * @param high color, scale range maximum.
 * @param midpoint point on scale domain which is mapped to [mid] color.
 * @param domainMin scale domain minimum.
 * @param domainMax scale domain maximum.
 * @param transform the transformation of scale
 * @return new continuous color scale.
 */
public fun <DomainType> continuousColorGradient2(
    low: Color,
    mid: Color,
    high: Color,
    midpoint: Double,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGradient2<DomainType> = ScaleContinuousColorGradient2(
    domainMin to domainMax, low, mid, high, midpoint, nullValue, transform
)
