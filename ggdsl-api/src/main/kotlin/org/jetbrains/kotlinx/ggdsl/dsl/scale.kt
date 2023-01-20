/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.internal.typed
import org.jetbrains.kotlinx.ggdsl.dsl.internal.typedList
import org.jetbrains.kotlinx.ggdsl.ir.scale.*

/**
 * Creates a new unspecified (i.e. without specifying the type and parameters;
 * they will be defined automatically) continuous non-positional scale.
 *
 * @param transform the transformation of scale
 */
public fun continuous(transform: NonPositionalTransform? = null): NonPositionalContinuousUnspecifiedScale =
    NonPositionalContinuousUnspecifiedScale(transform)

/**
 * Creates a new unspecified (i.e. without specifying the type and parameters;
 * * they will be defined automatically) categorical non-positional scale
 */
public fun categorical(): NonPositionalCategoricalUnspecifiedScale = NonPositionalCategoricalUnspecifiedScale

/**
 * Creates a new unspecified (i.e. without specifying the type and parameters;
 * they will be defined automatically) continuous positional scale
 *
 * @param transform the transformation of scale
 */
public fun continuousPos(transform: PositionalTransform? = null): PositionalContinuousUnspecifiedScale =
    PositionalContinuousUnspecifiedScale(transform)

/**
 * Creates a new unspecified (i.e. without specifying the type and parameters;
 * they will be defined automatically) categorical positional scale
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
public inline fun <reified DomainType> continuousPos(
    limits: Pair<DomainType & Any, DomainType & Any>? = null,
    transform: PositionalTransform? = null
): PositionalContinuousScale<DomainType> = PositionalContinuousScale(limits?.let {
    it.first.typed() to it.second.typed()
}, transform)

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
public inline fun <reified DomainType, reified RangeType> continuous(
    domainLimits: Pair<DomainType & Any, DomainType & Any>? = null,
    rangeLimits: Pair<RangeType & Any, RangeType & Any>? = null,
    transform: NonPositionalTransform? = null
): NonPositionalContinuousScale<DomainType, RangeType> =
    NonPositionalContinuousScale(domainLimits?.let {
        it.first.typed() to it.second.typed()
    }, rangeLimits?.let {
        it.first.typed() to it.second.typed()
    }, transform)

/**
 * Creates a new categorical positional scale
 *
 * @param DomainType type of domain
 * @param categories [List] defining the domain
 * @return new [PositionalCategoricalScale] with given categories
 */
public inline fun <reified DomainType> categoricalPos(categories: List<DomainType & Any>? = null)
        : PositionalCategoricalScale<DomainType> =
    PositionalCategoricalScale(categories?.typedList())

/**
 * Creates a new categorical non-positional scale.
 *
 * @param DomainType type of domain
 * @param RangeType type of range
 * @param domainCategories [List] defining the domain
 * @param rangeValues [List] defining the range
 * @return new [NonPositionalCategoricalScale] with given limits
 */
public inline fun <reified DomainType, reified RangeType> categorical(
    domainCategories: List<DomainType & Any>? = null,
    rangeValues: List<RangeType & Any>? = null,
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(domainCategories?.typedList(), rangeValues?.typedList())

/**
 * Creates a new categorical non-positional scale.
 *
 * @param DomainType type of domain
 * @param RangeType type of range
 * @param categoriesToValues [List] of pairs of category to corresponding value.
 * @return new [NonPositionalCategoricalScale] with given limits
 */
public inline fun <reified DomainType, reified RangeType> categorical(
    categoriesToValues: List<Pair<DomainType & Any, RangeType & Any>>
): NonPositionalCategoricalScale<DomainType, RangeType> =
    NonPositionalCategoricalScale(
        categoriesToValues.map { it.first }.typedList(),
        categoriesToValues.map { it.second }.typedList()
    )
