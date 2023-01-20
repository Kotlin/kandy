/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.scale

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue

/**
 * Positional scale interface. Positional scale is used in case
 * of mapping to positional aesthetic attribute.
 * It has an implicit range.
 *
 * @param DomainType the type of the domain.
 */
public sealed interface PositionalScale<DomainType> : Scale {
    public val nullValue: TypedValue?
}

/**
 * Positional continuous scale.
 *
 * @param DomainType the type of the domain.
 * @param limits the limits of the domain.
 */
@Serializable
public data class PositionalContinuousScale<DomainType>(
    val limits: Pair<TypedValue, TypedValue>?,
    override val nullValue: TypedValue?,
    override val transform: PositionalTransform?,
) : ContinuousScale, PositionalScale<DomainType>

/**
 * Positional categorical scale.
 *
 * @param DomainType the type of the domain.
 * @param categories the list of the domain categories.
 */
@Serializable
public data class PositionalCategoricalScale<DomainType>(
    val categories: TypedList?,
    override val nullValue: TypedValue?,
) : CategoricalScale, PositionalScale<DomainType>
