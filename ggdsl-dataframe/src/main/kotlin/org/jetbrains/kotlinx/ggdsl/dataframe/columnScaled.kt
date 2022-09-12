/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale

/**
 *  Apply default scale to this column
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this.toDataSource())

/**
 * Apply unspecified positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this.toDataSource(), scale)

/**
 * Apply unspecified non-positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this.toDataSource(), scale)

/**
 * Apply positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this.toDataSource(), scale)

/**
 * Apply non-positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
inline fun <reified DomainType : Any, RangeType : Any> ColumnReference<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this.toDataSource(), scale)
