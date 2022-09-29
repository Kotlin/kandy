package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale

/**
 *  Apply default scale to this column
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this.toColRef())

/**
 * Apply unspecified positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this.toColRef(), scale)

/**
 * Apply unspecified non-positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this.toColRef(), scale)

/**
 * Apply positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this.toColRef(), scale)

/**
 * Apply non-positional scale to this column
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
inline fun <reified DomainType : Any, RangeType : Any> ColumnReference<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this.toColRef(), scale)
