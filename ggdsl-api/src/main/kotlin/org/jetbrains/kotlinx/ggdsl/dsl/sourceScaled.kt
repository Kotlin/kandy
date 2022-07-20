package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale

/**
 *  TODO determines behavior
 */
fun <DomainType : Any> DataSource<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this)

/**
 * Apply default positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
fun <DomainType : Any> DataSource<DomainType>.scaled(scale: PositionalDefaultScale) =
    SourceScaledPositionalDefault(this, scale)

/**
 * Apply default non-positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
fun <DomainType : Any> DataSource<DomainType>.scaled(scale: NonPositionalDefaultScale) =
    SourceScaledNonPositionalDefault(this, scale)

/**
 * Apply positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
fun <DomainType : Any> DataSource<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this, scale)

/**
 * Apply non-positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
fun <DomainType : Any, RangeType : Any> DataSource<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this, scale)