package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale

/**
 *  TODO determines behavior
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this.toDataSource())

/**
 * Apply default positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(scale: PositionalDefaultScale) =
    SourceScaledPositionalDefault(this.toDataSource(), scale)

/**
 * Apply default non-positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(scale: NonPositionalDefaultScale) =
    SourceScaledNonPositionalDefault(this.toDataSource(), scale)

/**
 * Apply positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
inline fun <reified DomainType : Any> ColumnReference<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this.toDataSource(), scale)

/**
 * Apply non-positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
inline fun <reified DomainType : Any, RangeType : Any> ColumnReference<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this.toDataSource(), scale)
