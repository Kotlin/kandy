/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl

import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.scale.*

/**
 * Creates a new continuous positional scale (with non-nullable domain).
 *
 * @param DomainType scale domain type.
 * @param limits [ClosedRange] defining the scale domain.
 * @param transform the transformation of scale.
 * @return new [PositionalContinuousScale] with given limits.
 */
public fun <DomainType : Comparable<DomainType>> PositionalMappingParametersContinuous<*>.continuous(
    limits: ClosedRange<DomainType>,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType> = PositionalContinuousScale(
    limits.start, limits.endInclusive, null, transform
)

/**
 * Creates a new continuous positional scale (with non-nullable domain).
 *
 * @param DomainType scale domain type.
 * @param limits [ClosedRange] defining the scale domain.
 * @param transform the transformation of scale.
 * @return new [PositionalContinuousScale] with given limits.
 */
public fun <DomainType : Comparable<DomainType>> Scale.Companion.continuousPos(
    limits: ClosedRange<DomainType>,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType> = PositionalContinuousScale(
    limits.start, limits.endInclusive, null, transform
)

/**
 * Creates a new continuous positional scale (with nullable domain).
 *
 * @param DomainType scale domain type.
 * @param limits [ClosedRange] defining the scale domain.
 * @param nullValue value which null is mapped to.
 * @param transform the transformation of scale.
 * @return new [PositionalContinuousScale] with given limits.
 */
public fun <DomainType : Comparable<DomainType>> PositionalMappingParametersContinuous<*>.continuous(
    limits: ClosedRange<DomainType>,
    nullValue: DomainType? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType?> = PositionalContinuousScale(
    limits.start, limits.endInclusive, nullValue, transform
)

/**
 * Creates a new continuous positional scale (with nullable domain).
 *
 * @param DomainType scale domain type.
 * @param limits [ClosedRange] defining the scale domain.
 * @param nullValue value which null is mapped to.
 * @param transform the transformation of scale.
 * @return new [PositionalContinuousScale] with given limits.
 */
public fun <DomainType : Comparable<DomainType>> Scale.Companion.continuousPos(
    limits: ClosedRange<DomainType>,
    nullValue: DomainType? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType?> = PositionalContinuousScale(
    limits.start, limits.endInclusive, nullValue, transform
)

/**
 * Creates a new continuous positional scale (with non-nullable domain).
 *
 * @param DomainType scale domain type.
 * @param min scale domain minimum.
 * @param max scale domain maximum.
 * @param transform the transformation of scale.
 * @return new [PositionalContinuousScale] with given limits.
 */
public fun <DomainType> PositionalMappingParametersContinuous<*>.continuous(
    min: DomainType? = null,
    max: DomainType? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType> = PositionalContinuousScale(min, max, null, transform)

/**
 * Creates a new continuous positional scale (with non-nullable domain).
 *
 * @param DomainType scale domain type.
 * @param min scale domain minimum.
 * @param max scale domain maximum.
 * @param transform the transformation of scale.
 * @return new [PositionalContinuousScale] with given limits.
 */
public fun <DomainType> Scale.Companion.continuousPos(
    min: DomainType? = null,
    max: DomainType? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType> = PositionalContinuousScale(
    min, max, null, transform
)

/**
 * Creates a new continuous positional scale (with nullable domain).
 *
 * @param DomainType scale domain type.
 * @param min scale domain minimum.
 * @param max scale domain maximum.
 * @param nullValue value which null is mapped to.
 * @param transform the transformation of scale.
 * @return new [PositionalContinuousScale] with given limits.
 */
public fun <DomainType> PositionalMappingParametersContinuous<*>.continuous(
    min: DomainType? = null,
    max: DomainType? = null,
    nullValue: DomainType? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType?> = PositionalContinuousScale(
    min, max, nullValue, transform
)

/**
 * Creates a new continuous positional scale (with nullable domain).
 *
 * @param DomainType scale domain type.
 * @param min scale domain minimum.
 * @param max scale domain maximum.
 * @param nullValue value which null is mapped to.
 * @param transform the transformation of scale.
 * @return new [PositionalContinuousScale] with given limits.
 */
public fun <DomainType> Scale.Companion.continuousPos(
    min: DomainType? = null,
    max: DomainType? = null,
    nullValue: DomainType? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType?> = PositionalContinuousScale(
    min, max, nullValue, transform
)

/**
 * Creates a new categorical positional scale.
 *
 * @param DomainType scale domain type.
 * @param categories [List] defining the scale domain.
 * @return new [PositionalCategoricalScale] with given categories.
 */
public fun <DomainType> PositionalMappingParameters<*>.categorical(
    categories: List<DomainType>? = null, // TODO(add varargs)
): PositionalCategoricalScale<DomainType> = PositionalCategoricalScale(categories)

/**
 * Creates a new categorical positional scale.
 *
 * @param DomainType scale domain type.
 * @param categories [List] defining the scale domain.
 * @return new [PositionalCategoricalScale] with given categories.
 */
public fun <DomainType> Scale.Companion.categoricalPos(
    categories: List<DomainType>? = null,
): PositionalCategoricalScale<DomainType> = PositionalCategoricalScale(categories)

/**
 * Creates a new continuous non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param range [ClosedRange] defining the scale range.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 * @return new [NonPositionalContinuousScale] with the given range.
 */
public fun <RangeType : Comparable<RangeType>, DomainType> NonPositionalMappingParametersContinuous<*, *>.continuous(
    range: ClosedRange<RangeType>,
    nullValue: RangeType? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(
        null, null, range.start, range.endInclusive, nullValue, transform
    )

/**
 * Creates a new continuous non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param range [ClosedRange] defining the scale range.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 * @return new [NonPositionalContinuousScale] with the given range.
 */
public fun <RangeType : Comparable<RangeType>, DomainType> Scale.Companion.continuous(
    range: ClosedRange<RangeType>,
    nullValue: RangeType? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(
        null, null, range.start, range.endInclusive, nullValue, transform
    )

/**
 * Creates a new continuous non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param range [ClosedRange] defining the scale range.
 * @param domain [ClosedRange] defining the scale domain.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 * @return new [NonPositionalContinuousScale] with the given domain and range.
 */
public fun <RangeType : Comparable<RangeType>, DomainType : Comparable<DomainType>>
        NonPositionalMappingParametersContinuous<*, *>.continuous(
    range: ClosedRange<RangeType>? = null,
    domain: ClosedRange<DomainType>,
    nullValue: RangeType? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(
        domain.start, domain.endInclusive, range?.start, range?.endInclusive, nullValue, transform
    )

/**
 * Creates a new continuous non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param range [ClosedRange] defining the scale range.
 * @param domain [ClosedRange] defining the scale domain.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 * @return new [NonPositionalContinuousScale] with the given domain and range.
 */
public fun <RangeType : Comparable<RangeType>, DomainType : Comparable<DomainType>>
        Scale.Companion.continuous(
    range: ClosedRange<RangeType>? = null,
    domain: ClosedRange<DomainType>,
    nullValue: RangeType? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(
        domain.start, domain.endInclusive, range?.start, range?.endInclusive, nullValue, transform
    )

/**
 * Creates a new continuous non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param rangeMin scale range minimum.
 * @param rangeMax scale range maximum.
 * @param domainMin scale domain minimum.
 * @param domainMax scale domain maximum.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 * @return new [NonPositionalContinuousScale] with the given domain and range.
 */
public fun <RangeType, DomainType> NonPositionalMappingParametersContinuous<*, *>.continuous(
    rangeMin: RangeType? = null,
    rangeMax: RangeType? = null,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: RangeType? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(
        domainMin, domainMax, rangeMin, rangeMax, nullValue, transform
    )

/**
 * Creates a new continuous non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param rangeMin scale range minimum.
 * @param rangeMax scale range maximum.
 * @param domainMin scale domain minimum.
 * @param domainMax scale domain maximum.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 * @return new [NonPositionalContinuousScale] with the given domain and range.
 */
public fun <RangeType, DomainType> Scale.Companion.continuous(
    rangeMin: RangeType? = null,
    rangeMax: RangeType? = null,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: RangeType? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(
        domainMin, domainMax, rangeMin, rangeMax, nullValue, transform
    )

/**
 * Creates a new categorical non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param range [List] defining the scale range.
 * @param domain [List] defining the scale domain.
 * @return new [NonPositionalCategoricalScale] with given domain and range.
 */
public inline fun <reified RangeType, reified DomainType> NonPositionalMappingParameters<*, *>.categorical(
    range: List<RangeType>? = null,
    domain: List<DomainType>? = null,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(domain, range)

/**
 * Creates a new categorical non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param range [List] defining the scale range.
 * @param domain [List] defining the scale domain.
 * @return new [NonPositionalCategoricalScale] with given domain and range.
 */
public inline fun <reified RangeType, reified DomainType> Scale.Companion.categorical(
    range: List<RangeType>? = null,
    domain: List<DomainType>? = null,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(domain, range)

/**
 * Creates a new categorical non-positional scale.
 *
 * @param DomainType scale domain type.
 * @param RangeType type of the scale range.
 * @param categoriesToValues [List] of pairs of category to corresponding value.
 * @return new [NonPositionalCategoricalScale] by domain-to-range correspondence.
 */
public fun <DomainType, RangeType> NonPositionalMappingParameters<*, *>.categorical(
    vararg categoriesToValues: Pair<DomainType, RangeType>,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(
        categoriesToValues.map { it.first },
        categoriesToValues.map { it.second },
    )

/**
 * Creates a new categorical non-positional scale.
 *
 * @param DomainType scale domain type
 * @param RangeType type of the scale range
 * @param categoriesToValues [List] of pairs of category to corresponding value.
 * @return new [NonPositionalCategoricalScale] by domain-to-range correspondence.
 */
public fun <DomainType, RangeType> Scale.Companion.categorical(
    vararg categoriesToValues: Pair<DomainType, RangeType>,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(
        categoriesToValues.map { it.first },
        categoriesToValues.map { it.second },
    )
