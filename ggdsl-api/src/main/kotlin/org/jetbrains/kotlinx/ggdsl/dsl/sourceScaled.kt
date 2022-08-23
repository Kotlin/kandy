package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.KProperty

/**
 *  Apply default scale to this [DataSource]
 */
fun <DomainType : Any> DataSource<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this)

inline fun <reified DomainType : Any> KProperty<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this.toDataSource())

/**
 * Apply unspecified positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
fun <DomainType : Any> DataSource<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this, scale)

inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this.toDataSource(), scale)

/**
 * Apply unspecified non-positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
fun <DomainType : Any> DataSource<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this, scale)

inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this.toDataSource(), scale)

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

inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this.toDataSource(), scale)

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

inline fun <reified DomainType : Any, RangeType : Any> KProperty<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this.toDataSource(), scale)