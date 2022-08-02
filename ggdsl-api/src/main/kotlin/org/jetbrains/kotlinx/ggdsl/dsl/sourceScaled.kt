package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale

/**
 *  Apply default scale to this [DataSource]
 */
fun <DomainType : Any> DataSource<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this)

/**
 * Apply unspecified positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
fun <DomainType : Any> DataSource<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this, scale)

/**
 * Apply unspecified non-positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
fun <DomainType : Any> DataSource<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this, scale)

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
