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
 *  Apply default scale to this [ColumnPointer]
 */
public fun <DomainType : Any> ColumnPointer<DomainType>.scaled(): ColumnScaledUnspecifiedDefault<DomainType> =
    ColumnScaledUnspecifiedDefault(this)

/**
 * Apply unspecified positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
public fun <DomainType : Any> ColumnPointer<DomainType>.scaled(scale: PositionalUnspecifiedScale):
        ColumnScaledPositionalUnspecified<DomainType> =
    ColumnScaledPositionalUnspecified(this, scale)

/**
 * Apply unspecified non-positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */

public fun <DomainType : Any> ColumnPointer<DomainType>.scaled(scale: NonPositionalUnspecifiedScale):
        ColumnScaledNonPositionalUnspecified<DomainType> =
    ColumnScaledNonPositionalUnspecified(this, scale)

/**
 * Apply positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
public fun <DomainType : Any> ColumnPointer<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): ColumnScaledPositional<DomainType> = ColumnScaledPositional(this, scale)

/**
 * Apply non-positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
public fun <DomainType : Any, RangeType : Any> ColumnPointer<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): ColumnScaledNonPositional<DomainType, RangeType> = ColumnScaledNonPositional(this, scale)
