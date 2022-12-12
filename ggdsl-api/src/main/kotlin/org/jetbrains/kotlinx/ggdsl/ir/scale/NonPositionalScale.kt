/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.scale

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue

/**
 * Non-positional scale interface. Non-positional scale is used in case
 * of mapping to non-positional aesthetic attribute.
 *
 * @param DomainType the type of the domain.
 * @param RangeType the type of the range.
 */
public sealed interface NonPositionalScale<DomainType : Any, RangeType : Any> : Scale

/**
 * Non-positional continuous scale.
 *
 * @param DomainType the type of the domain.
 * @param RangeType the type of the range.
 * @param domainLimits the limits of the domain.
 * @param rangeLimits the limits of the range.
 */
@Serializable
public data class NonPositionalContinuousScale<DomainType : Any, RangeType : Any>(
    val domainLimits: Pair<TypedValue, TypedValue>? = null,
    val rangeLimits: Pair<TypedValue, TypedValue>? = null,
    override val transform: NonPositionalTransform? = null,
) : ContinuousScale, NonPositionalScale<DomainType, RangeType>

/**
 * Non-positional categorical scale.
 *
 * @param DomainType the type of the domain.
 * @param domainCategories the list of the domain categories.
 * @param rangeValues the list of the range values.
 */
@Serializable
public data class NonPositionalCategoricalScale<DomainType : Any, RangeType : Any>(
    val domainCategories: TypedList? = null,
    val rangeValues: TypedList? = null,
) : CategoricalScale, NonPositionalScale<DomainType, RangeType>

public interface CustomNonPositionalScale<DomainType : Any, RangeType : Any>
    : NonPositionalScale<DomainType, RangeType>, CustomScale
