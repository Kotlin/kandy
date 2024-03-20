/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

import org.jetbrains.kotlinx.kandy.ir.aes.Aes

/**
 * Represents a non-positional [scale][Scale],
 * which maps data values to [aesthetic attributes][Aes] other than spatial positions.
 *
 * Unlike positional scales that determine the spatial placement of data points on the `x` and `y` axes,
 * non-positional scales influence other visual elements such as color, size, or shape.
 *
 * @property DomainType the type of data domain.
 * @property RangeType the type of aesthetic range to which the data values are mapped.
 */
public sealed interface NonPositionalScale<DomainType, RangeType> : Scale

/**
 * Represents a non-positional continuous [scale][Scale].
 * It maps continuous data values to [aesthetic attributes][Aes] other than spatial positions, such as color gradients.
 *
 * @property DomainType the type of data domain.
 * @property RangeType the type of aesthetic range to which the data values are mapped.
 * @property domainMin the minimum value of the data domain.
 * @property domainMax the maximum value of the data domain.
 * @property rangeMin the minimum value of the aesthetic range.
 * @property rangeMax the maximum value of the aesthetic range.
 * @property nullValue the default aesthetic value to use when data is absent or `null`.
 * @property transform an optional transformation applied to the data values before mapping.
 */
public data class NonPositionalContinuousScale<DomainType, RangeType>(
    val domainMin: DomainType?,
    val domainMax: DomainType?,
    val rangeMin: RangeType?,
    val rangeMax: RangeType?,
    override val nullValue: RangeType?,
    override val transform: NonPositionalTransform?,
) : ContinuousScale<RangeType>, NonPositionalScale<DomainType, RangeType>

/**
 * Base interface for non-positional [categorical scales][CategoricalScale].
 * These scales map discrete, finite data values to specific [aesthetic attributes][Aes].
 *
 * @property DomainType the type of data domain.
 * @property RangeType the type of aesthetic range to which the data values are mapped.
 */
public sealed interface NonPositionalCategoricalScaleBase<DomainType, RangeType>
    : CategoricalScale, NonPositionalScale<DomainType, RangeType>

/**
 * Represents a non-positional [categorical scale][CategoricalScale], mapping distinct categories within the data to
 * specific [aesthetic attributes][Aes] other than spatial positions.
 *
 * @property DomainType the type of data domain.
 * @property domainCategories list of unique data categories.
 * @property rangeValues list of aesthetic values corresponding to the domain categories.
 */
public data class NonPositionalCategoricalScale<DomainType, RangeType>(
    val domainCategories: List<DomainType>?,
    val rangeValues: List<RangeType>?,
) : NonPositionalCategoricalScaleBase<DomainType, RangeType>

/**
 * Represents a custom implementation of a non-positional [scale][Scale], providing flexibility
 * for scenarios where predefined scales may not suffice.
 *
 * @property DomainType the type of data domain.
 * @property RangeType the type of aesthetic range.
 */
public interface CustomNonPositionalScale<DomainType, RangeType>
    : NonPositionalScale<DomainType, RangeType>, CustomScale

/**
 * Represents the default non-positional [scale][Scale] employed based on the type of data and
 * the [aesthetic attribute][Aes] in question.
 *
 * @property DomainType the type of data domain.
 * @property RangeType the type of aesthetic range.
 */
public open class NonPositionalDefaultScale<DomainType, RangeType> :
    NonPositionalScale<DomainType, RangeType>, DefaultScale {
    override fun equals(other: Any?): Boolean {
        return other is NonPositionalDefaultScale<*, *>
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

/**
 * Represents the default categorical, non-positional [scale][Scale] employed based on
 * the type of data and the [aesthetic attribute][Aes].
 *
 * @property DomainType the type of data domain.
 * @property RangeType the type of aesthetic range.
 */
public class NonPositionalDefaultCategoricalScale<DomainType, RangeType> : NonPositionalDefaultScale<DomainType, RangeType>(),
    NonPositionalCategoricalScaleBase<DomainType, RangeType>, DefaultScale {
    override fun equals(other: Any?): Boolean {
        return other is NonPositionalDefaultScale<*, *>
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
