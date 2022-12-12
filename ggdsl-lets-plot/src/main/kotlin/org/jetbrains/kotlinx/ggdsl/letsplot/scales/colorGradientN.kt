/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.typedPair
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue

@Serializable
public data class ScaleContinuousColorGradientN<DomainType : Any>(
    val domainLimits: Pair<TypedValue, TypedValue>? = null,
    val rangeColors: List<Color>,
    override val transform: Transformation? = null
) : ContinuousScale, CustomNonPositionalScale<DomainType, Color>

/**
 * Creates smooth color gradient between multiple colors.
 *
 * @param DomainType type of domain
 * @param domainLimits segment defining the domain
 * @param transform the transformation of scale
 * @return new [ContinuousScale]/[CustomNonPositionalScale] with given limits
 */
public inline fun <reified DomainType : Any> continuousColorGradientN(
    rangeColors: List<Color>,
    domainLimits: Pair<DomainType, DomainType>? = null,
    transform: Transformation? = null
): ScaleContinuousColorGradientN<DomainType> = ScaleContinuousColorGradientN(
    domainLimits?.typedPair(), rangeColors, transform
)

@Serializable
public data class ScaleContinuousColorGradient2<DomainType : Any>(
    val domainLimits: Pair<TypedValue, TypedValue>? = null,
    val low: Color,
    val mid: Color,
    val high: Color,
    val midpoint: Double,
    override val transform: Transformation? = null
) : ContinuousScale, CustomNonPositionalScale<DomainType, Color>

/**
 * Creates diverging color gradient (low-mid-high) for color aesthetic.
 *
 * TODO
 * @param DomainType type of domain
 * @param domainLimits segment defining the domain
 * @param transform the transformation of scale
 * @return new [ContinuousScale]/[CustomNonPositionalScale] with given limits
 */
public inline fun <reified DomainType : Any> continuousColorGradient2(
    low: Color,
    mid: Color,
    high: Color,
    midpoint: Double,
    domainLimits: Pair<DomainType, DomainType>? = null,
    transform: Transformation? = null
): ScaleContinuousColorGradient2<DomainType> = ScaleContinuousColorGradient2(
    domainLimits?.typedPair(), low, mid, high, midpoint, transform
)
