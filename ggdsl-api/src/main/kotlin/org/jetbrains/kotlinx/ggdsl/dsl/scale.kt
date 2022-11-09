/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.scale.*

/**
 * Creates a new default continuous non-positional scale
 *
 * @param transform the transformation of scale
 */
public fun continuous(transform: NonPositionalTransform? = null): NonPositionalContinuousUnspecifiedScale =
    NonPositionalContinuousUnspecifiedScale(transform)

/**
 * Creates a new default categorical non-positional scale
 */
public fun categorical(): NonPositionalCategoricalUnspecifiedScale = NonPositionalCategoricalUnspecifiedScale

/**
 * Creates a new default continuous positional scale
 *
 * @param transform the transformation of scale
 */
public fun continuousPos(transform: PositionalTransform? = null): PositionalContinuousUnspecifiedScale =
    PositionalContinuousUnspecifiedScale(transform)

/**
 * Creates a new default categorical positional scale
 */
public fun categoricalPos(): PositionalCategoricalUnspecifiedScale =
    PositionalCategoricalUnspecifiedScale

/**
 * Creates a new continuous positional scale
 *
 * @param DomainType type of domain
 * @param limits segment defining the domain
 * @param transform the transformation of scale
 * @return new [PositionalContinuousScale] with given limits
 */
public fun <DomainType : Any> continuousPos(
    limits: Pair<DomainType, DomainType>? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType> = PositionalContinuousScale(limits, transform)

/**
 * Creates a new continuous non-positional scale
 *
 * @param DomainType type of domain
 * @param RangeType type of range
 * @param domainLimits segment defining the domain
 * @param rangeLimits segment defining the range
 * @param transform the transformation of scale
 * @return new [NonPositionalContinuousScale] with given limits
 */
public fun <DomainType : Any, RangeType : Any> continuous(
    domainLimits: Pair<DomainType, DomainType>? = null,
    rangeLimits: Pair<RangeType, RangeType>? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(domainLimits, rangeLimits, transform)

/**
 * Creates a new categorical positional scale
 *
 * @param DomainType type of domain
 * @param categories [List] defining the domain
 * @return new [PositionalCategoricalScale] with given categories
 */
public fun <DomainType : Any> categoricalPos(categories: List<DomainType>? = null)
        : PositionalCategoricalScale<DomainType> =
    PositionalCategoricalScale(categories)

/**
 * Creates a new categorical non-positional scale
 *
 * @param DomainType type of domain
 * @param RangeType type of range
 * @param domainCategories [List] defining the domain
 * @param rangeValues [List] defining the range
 * @return new [NonPositionalCategoricalScale] with given limits
 */
public fun <DomainType : Any, RangeType : Any> categorical(
    domainCategories: List<DomainType>? = null,
    rangeValues: List<RangeType>? = null,
): NonPositionalCategoricalScale<DomainType, RangeType> = NonPositionalCategoricalScale(domainCategories, rangeValues)
