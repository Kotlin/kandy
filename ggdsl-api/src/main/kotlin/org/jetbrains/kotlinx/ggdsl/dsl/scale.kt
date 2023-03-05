/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.scale.*

/**
 * Creates a new continuous positional scale
 *
 * @param DomainType type of domain
 * @param limits segment defining the domain
 * @param transform the transformation of scale
 * @return new [PositionalContinuousScale] with given limits
 */
public fun <DomainType : Comparable<DomainType>> PositionalMappingParameters<DomainType>.continuous(
    limits: ClosedRange<DomainType>? = null,
    nullValue: DomainType? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType> = PositionalContinuousScale(limits, nullValue, transform)

public fun <DomainType : Comparable<DomainType>> Scale.Companion.continuousPos(
    limits: ClosedRange<DomainType>? = null,
    nullValue: DomainType? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType> = PositionalContinuousScale(limits, nullValue, transform)

/**
 * Creates a new categorical positional scale
 *
 * @param DomainType type of domain
 * @param categories [List] defining the domain
 * @return new [PositionalCategoricalScale] with given categories
 */
public fun <DomainType> PositionalMappingParameters<DomainType>.categorical(
    categories: List<DomainType>? = null,
): PositionalCategoricalScale<DomainType> = PositionalCategoricalScale(categories)

public fun <DomainType> Scale.Companion.categoricalPos(
    categories: List<DomainType>? = null,
): PositionalCategoricalScale<DomainType> = PositionalCategoricalScale(categories)

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
public fun <DomainType : Comparable<DomainType>, RangeType : Comparable<RangeType>> NonPositionalMappingParameters<DomainType, RangeType>.continuous(
    domainLimits: ClosedRange<DomainType>? = null,
    rangeLimits: ClosedRange<RangeType>? = null,
    nullValue: RangeType? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(domainLimits, rangeLimits, nullValue, transform)

public fun <DomainType : Comparable<DomainType>, RangeType : Comparable<RangeType>> Scale.Companion.continuous(
    domainLimits: ClosedRange<DomainType>? = null,
    rangeLimits: ClosedRange<RangeType>? = null,
    nullValue: RangeType? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(domainLimits, rangeLimits, nullValue, transform)

/**
 * Creates a new categorical non-positional scale.
 *
 * @param DomainType type of domain
 * @param RangeType type of range
 * @param domainCategories [List] defining the domain
 * @param rangeValues [List] defining the range
 * @return new [NonPositionalCategoricalScale] with given limits
 */
public inline fun <reified DomainType, reified RangeType> NonPositionalMappingParameters<DomainType, RangeType>.categorical(
    domainCategories: List<DomainType>? = null,
    rangeValues: List<RangeType>? = null,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(domainCategories, rangeValues)

public inline fun <reified DomainType, reified RangeType> Scale.Companion.categorical(
    domainCategories: List<DomainType>? = null,
    rangeValues: List<RangeType>? = null,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(domainCategories, rangeValues)

/**
 * Creates a new categorical non-positional scale.
 *
 * @param DomainType type of domain
 * @param RangeType type of range
 * @param categoriesToValues [List] of pairs of category to corresponding value.
 * @return new [NonPositionalCategoricalScale] with given limits
 */
public fun <DomainType, RangeType> NonPositionalMappingParameters<DomainType, RangeType>.categorical(
    vararg categoriesToValues: Pair<DomainType, RangeType>,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(
        categoriesToValues.map { it.first },
        categoriesToValues.map { it.second },
    )

public fun <DomainType, RangeType> Scale.Companion.categorical(
    vararg categoriesToValues: Pair<DomainType, RangeType>,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(
        categoriesToValues.map { it.first },
        categoriesToValues.map { it.second },
    )
