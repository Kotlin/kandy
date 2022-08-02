package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.scale.*

/**
 * Creates a new default continuous non-positional scale
 *
 * @param transform the transformation of scale
 */
fun continuous(transform: NonPositionalTransform? = null) = NonPositionalContinuousUnspecifiedScale(transform)

/**
 * Creates a new default categorical non-positional scale
 */
fun categorical() = NonPositionalCategoricalUnspecifiedScale

/**
 * Creates a new default continuous positional scale
 *
 * @param transform the transformation of scale
 */
fun continuousPos(transform: PositionalTransform? = null) = PositionalContinuousUnspecifiedScale(transform)

/**
 * Creates a new default categorical positional scale
 */
fun categoricalPos() = PositionalCategoricalUnspecifiedScale

/**
 * Creates a new continuous positional scale
 *
 * @param DomainType type of domain
 * @param limits segment defining the domain
 * @param transform the transformation of scale
 * @return new [PositionalContinuousScale] with given limits
 */
fun <DomainType : Any> continuousPos(
    limits: Pair<DomainType, DomainType>? = null,
    transform: PositionalTransform? = null
) = PositionalContinuousScale(limits, transform)

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
fun <DomainType : Any, RangeType : Any> continuous(
    domainLimits: Pair<DomainType, DomainType>? = null,
    rangeLimits: Pair<RangeType, RangeType>? = null,
    transform: NonPositionalTransform? = null
) = NonPositionalContinuousScale(domainLimits, rangeLimits, transform)

/**
 * Creates a new categorical positional scale
 *
 * @param DomainType type of domain
 * @param categories [List] defining the domain
 * @return new [PositionalCategoricalScale] with given categories
 */
fun <DomainType : Any> categoricalPos(categories: List<DomainType>? = null) =
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
fun <DomainType : Any, RangeType : Any> categorical(
    domainCategories: List<DomainType>? = null,
    rangeValues: List<RangeType>? = null,
) = NonPositionalCategoricalScale(domainCategories, rangeValues)
