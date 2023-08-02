/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

/**
 * Non-positional scale is used by mapping to non-positional aesthetic attribute.
 *
 * @property DomainType type of the domain.
 * @property RangeType
 */
public sealed interface NonPositionalScale<DomainType, RangeType> : Scale

/**
 * Non-positional continuous scale.
 *
 * @param DomainType type of the domain.
 * @param RangeType type of the range.
 * @param rangeMin scale range minimum.
 * @param rangeMax scale range maximum.
 * @param domainMin scale domain minimum.
 * @param domainMax scale domain maximum.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 */
public data class NonPositionalContinuousScale<DomainType, RangeType>(
    val domainMin: DomainType?,
    val domainMax: DomainType?,
    val rangeMin: RangeType?,
    val rangeMax: RangeType?,
    override val nullValue: RangeType?,
    override val transform: NonPositionalTransform?,
) : ContinuousScale<RangeType>, NonPositionalScale<DomainType, RangeType>

public sealed interface NonPositionalCategoricalScaleBase<DomainType, RangeType>
    : CategoricalScale, NonPositionalScale<DomainType, RangeType>

/**
 * Non-positional categorical scale.
 *
 * @param DomainType type of the domain.
 * @param domainCategories [List] of the domain categories.
 * @param rangeValues [List] of the range values.
 */
public data class NonPositionalCategoricalScale<DomainType, RangeType>(
    val domainCategories: List<DomainType>?,
    val rangeValues: List<RangeType>?,
) : NonPositionalCategoricalScaleBase<DomainType, RangeType>

/**
 * Custom non-positional scale.
 */
public interface CustomNonPositionalScale<DomainType, RangeType>
    : NonPositionalScale<DomainType, RangeType>, CustomScale

/**
 * Non-positional default scale.
 */
public class NonPositionalDefaultScale<DomainType, RangeType>:
    NonPositionalScale<DomainType, RangeType>, DefaultScale {
    override fun equals(other: Any?): Boolean {
        return other is NonPositionalDefaultScale<*, *>
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

/**
 * Non-positional default categorical scale.
 */
public class NonPositionalDefaultCategoricalScale<DomainType, RangeType>:
    NonPositionalCategoricalScaleBase<DomainType, RangeType>, DefaultScale {
    override fun equals(other: Any?): Boolean {
        return other is NonPositionalDefaultScale<*, *>
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
