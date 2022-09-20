/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.scale

/**
 * Non-positional scale interface. Non-positional scale is used in case
 * of mapping to non-positional aesthetic attribute.
 *
 * @param DomainType the type of the domain.
 * @param RangeType the type of the range.
 */
sealed interface NonPositionalScale<DomainType : Any, RangeType : Any> : Scale

/**
 * Non-positional continuous scale.
 *
 * @param DomainType the type of the domain.
 * @param RangeType the type of the range.
 * @param domainLimits the limits of the domain.
 * @param rangeLimits the limits of the range.
 */
data class NonPositionalContinuousScale<DomainType : Any, RangeType : Any>(
    val domainLimits: Pair<DomainType, DomainType>? = null,
    val rangeLimits: Pair<RangeType, RangeType>? = null,
    override val transform: NonPositionalTransform? = null,
) : ContinuousScale, NonPositionalScale<DomainType, RangeType>

/**
 * Non-positional categorical scale.
 *
 * @param DomainType the type of the domain.
 * @param domainCategories the list of the domain categories.
 * @param rangeValues the list of the range values.
 */
data class NonPositionalCategoricalScale<DomainType : Any, RangeType : Any>(
    val domainCategories: List<DomainType>? = null,
    val rangeValues: List<RangeType>? = null,
) : CategoricalScale, NonPositionalScale<DomainType, RangeType>

interface CustomNonPositionalScale<DomainType : Any, RangeType : Any>
    : NonPositionalScale<DomainType, RangeType>, CustomScale
