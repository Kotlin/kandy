/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale

/**
 * Applies the default (i.e. without specifying the type and parameters;
 * they will be defined automatically; can be both used for positional and non-positional
 * mappings) scale to this [ColumnPointer].
 *
 * @param DomainType type of the domain.
 * @return scaled source.
 */
public fun <DomainType> ColumnPointer<DomainType>.scaled(): ColumnScaledUnspecifiedDefault<DomainType> =
    ColumnScaledUnspecifiedDefault(this)

/**
 * Applies an unspecified (i.e. without specifying the type and parameters;
 * they will be defined automatically) positional scale to this [ColumnPointer].
 *
 * @param DomainType type of the domain.
 * @param scale positional default scale.
 * @return scaled source.
 */
public fun <DomainType> ColumnPointer<DomainType>.scaled(scale: PositionalUnspecifiedScale):
        ColumnScaledPositionalUnspecified<DomainType> =
    ColumnScaledPositionalUnspecified(this, scale)

/**
 * Applies an unspecified (i.e. without specifying the type and parameters;
 * they will be defined automatically) non-positional scale to this [ColumnPointer].
 *
 * @param DomainType type of the domain.
 * @param scale non-positional default scale.
 * @return scaled source.
 */
public fun <DomainType> ColumnPointer<DomainType>.scaled(scale: NonPositionalUnspecifiedScale):
        ColumnScaledNonPositionalUnspecified<DomainType> =
    ColumnScaledNonPositionalUnspecified(this, scale)

/**
 * Applies a positional scale to this [ColumnPointer].
 *
 * @param DomainType type of the domain.
 * @param scale positional scale.
 * @return scaled source.
 */
public fun <DomainType> ColumnPointer<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): ColumnScaledPositional<DomainType> = ColumnScaledPositional(this, scale)

/**
 * Applies a non-positional scale to this [ColumnPointer].
 *
 * @param DomainType type of the domain.
 * @param scale non-positional scale.
 * @return scaled source.
 */
public fun <DomainType, RangeType> ColumnPointer<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): ColumnScaledNonPositional<DomainType, RangeType> = ColumnScaledNonPositional(this, scale)
