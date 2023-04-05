/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

/**
 * Positional scale interface. Positional scale is used in case
 * of mapping to positional aesthetic attribute.
 * It has an implicit range.
 *
 * @param DomainType the type of the domain.
 */
public sealed interface PositionalScale<DomainType> : Scale {
    //public val nullValue: TypedValue?
}

/**
 * Positional continuous scale.
 *
 * @param DomainType the type of the domain.
 * @param limits the limits of the domain.
 */
//@Serializable
public data class PositionalContinuousScale<DomainType>(
    val min: DomainType?,
    val max: DomainType?,
    override val nullValue: DomainType?,
    override val transform: PositionalTransform?,
) : ContinuousScale<DomainType>, PositionalScale<DomainType>

/**
 * Positional categorical scale.
 *
 * @param DomainType the type of the domain.
 * @param categories the list of the domain categories.
 */
//@Serializable
public data class PositionalCategoricalScale<DomainType>(
    val categories: List<DomainType>?,
    //override val nullValue: TypedValue?,
) : CategoricalScale, PositionalScale<DomainType>

// TODO!!!
public class PositionalDefaultScale<DomainType>
    : PositionalScale<DomainType>, DefaultScale {
    public override fun equals(other: Any?): Boolean {
        return other is PositionalDefaultScale<*>
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
