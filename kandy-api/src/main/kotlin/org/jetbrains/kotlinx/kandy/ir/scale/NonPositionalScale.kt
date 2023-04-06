/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

/**
 * Non-positional scale interface. Non-positional scale is used in case
 * of mapping to non-positional aesthetic attribute.
 *
 * @param DomainType the type of the domain.
 * @param RangeType the type of the range.
 */
public sealed interface NonPositionalScale<DomainType, RangeType> : Scale {
    //public val nullValue: TypedValue?
}

/**
 * Non-positional continuous scale.
 *
 * @param DomainType the type of the domain.
 * @param RangeType the type of the range.
 * @param domainLimits the limits of the domain.
 * @param rangeLimits the limits of the range.
 */
//@Serializable
public data class NonPositionalContinuousScale<DomainType, RangeType>(
    val domainMin: DomainType?,
    val domainMax: DomainType?,
    val rangeMin: RangeType?,
    val rangeMax: RangeType?,
    override val nullValue: RangeType?,
    override val transform: NonPositionalTransform?,
) : ContinuousScale<RangeType>, NonPositionalScale<DomainType, RangeType>

/**
 * Non-positional categorical scale.
 *
 * @param DomainType the type of the domain.
 * @param domainCategories the list of the domain categories.
 * @param rangeValues the list of the range values.
 */
//@Serializable
public data class NonPositionalCategoricalScale<DomainType, RangeType>(
    val domainCategories: List<DomainType>?,
    val rangeValues: List<RangeType>?,
    //override val nullValue: TypedValue?,
) : CategoricalScale, NonPositionalScale<DomainType, RangeType>

public interface CustomNonPositionalScale<DomainType, RangeType>
    : NonPositionalScale<DomainType, RangeType>, CustomScale

//todo!!!
public class NonPositionalDefaultScale<DomainType, RangeType>:
    NonPositionalScale<DomainType, RangeType>, DefaultScale {
    override fun equals(other: Any?): Boolean {
        return other is NonPositionalDefaultScale<*, *>
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
