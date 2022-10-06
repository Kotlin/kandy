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
import kotlin.reflect.KProperty

/**
 *  Apply default scale to this [ColumnPointer]
 */
public fun <DomainType : Any> ColumnPointer<DomainType>.scaled(): SourceScaledUnspecifiedDefault<DomainType> =
    SourceScaledUnspecifiedDefault(this)

public inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(): SourceScaledUnspecifiedDefault<DomainType> =
    SourceScaledUnspecifiedDefault(this.toColumnPointer())

/**
 * Apply unspecified positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */

public fun <DomainType : Any> ColumnPointer<DomainType>.scaled(scale: PositionalUnspecifiedScale):
        SourceScaledPositionalUnspecified<DomainType> =
    SourceScaledPositionalUnspecified(this, scale)

public inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(scale: PositionalUnspecifiedScale):
        SourceScaledPositionalUnspecified<DomainType> =
    SourceScaledPositionalUnspecified(this.toColumnPointer(), scale)


/**
 * Apply unspecified non-positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */

public fun <DomainType : Any> ColumnPointer<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): SourceScaledNonPositionalUnspecified<DomainType> =
    SourceScaledNonPositionalUnspecified(this, scale)

public inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): SourceScaledNonPositionalUnspecified<DomainType> =
    SourceScaledNonPositionalUnspecified(this.toColumnPointer(), scale)

/**
 * Apply positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */

public fun <DomainType : Any> ColumnPointer<DomainType>.scaled(

    scale: PositionalScale<DomainType>
): SourceScaledPositional<DomainType> = SourceScaledPositional(this, scale)

public inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(
    scale: PositionalScale<DomainType>

): SourceScaledPositional<DomainType> = SourceScaledPositional(this.toColumnPointer(), scale)

/**
 * Apply non-positional scale to this [ColumnPointer]

 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */

public fun <DomainType : Any, RangeType : Any> ColumnPointer<DomainType>.scaled(

    scale: NonPositionalScale<DomainType, RangeType>
): SourceScaledNonPositional<DomainType, RangeType> = SourceScaledNonPositional(this, scale)

public inline fun <reified DomainType : Any, RangeType : Any> KProperty<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>

): SourceScaledNonPositional<DomainType, RangeType> = SourceScaledNonPositional(this.toColumnPointer(), scale)

