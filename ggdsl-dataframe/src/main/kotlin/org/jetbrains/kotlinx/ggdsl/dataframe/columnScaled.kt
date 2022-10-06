/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale

/**
 *  Apply a default scale to this column
 */

public inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(): SourceScaledUnspecifiedDefault<DomainType> =
    SourceScaledUnspecifiedDefault(this.toColRef())

/**
 * Apply an unspecified positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */

public inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(scale: PositionalUnspecifiedScale): SourceScaledPositionalUnspecified<DomainType> =
    SourceScaledPositionalUnspecified(this.toColRef(), scale)


/**
 * Apply an unspecified non-positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */

public inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): SourceScaledNonPositionalUnspecified<DomainType> =
    SourceScaledNonPositionalUnspecified(this.toColRef(), scale)


/**
 * Apply a positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
public inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): SourceScaledPositional<DomainType> = SourceScaledPositional(this.toColRef(), scale)


/**
 * Apply a non-positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
public inline fun <reified DomainType : Any, RangeType : Any> ColumnReference<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): SourceScaledNonPositional<DomainType, RangeType> = SourceScaledNonPositional(this.toColRef(), scale)

