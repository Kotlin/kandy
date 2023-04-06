/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

/**
 * Positional scale is used by mapping to positional aesthetic attribute.
 * It has an implicit range.
 *
 * @property DomainType type of the domain.
 */
public sealed interface PositionalScale<DomainType> : Scale

/**
 * Positional continuous scale.
 *
 * @property DomainType type of the domain.
 * @property min scale domain minimum.
 * @property max scale domain maximum.
 * @property nullValue value which null is mapped to.
 * @property transform scale transformation.
 */
public data class PositionalContinuousScale<DomainType>(
    val min: DomainType?,
    val max: DomainType?,
    override val nullValue: DomainType?,
    override val transform: PositionalTransform?,
) : ContinuousScale<DomainType>, PositionalScale<DomainType>

/**
 * Positional categorical scale.
 *
 * @property DomainType the type of the domain.
 * @property categories the list of the domain categories.
 */
public data class PositionalCategoricalScale<DomainType>(
    val categories: List<DomainType>?,
) : CategoricalScale, PositionalScale<DomainType>

/**
 * Positional default scale.
 */
public class PositionalDefaultScale<DomainType>
    : PositionalScale<DomainType>, DefaultScale {
    public override fun equals(other: Any?): Boolean {
        return other is PositionalDefaultScale<*>
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
