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
fun <DomainType : Any> ColumnPointer<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this)

inline fun <reified DomainType : Any> KProperty<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this.toColumnPointer())

/**
 * Apply unspecified positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
fun <DomainType : Any> ColumnPointer<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this, scale)

inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this.toColumnPointer(), scale)

/**
 * Apply unspecified non-positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
fun <DomainType : Any> ColumnPointer<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this, scale)

inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this.toColumnPointer(), scale)

/**
 * Apply positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
fun <DomainType : Any> ColumnPointer<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this, scale)

inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this.toColumnPointer(), scale)

/**
 * Apply non-positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
fun <DomainType : Any, RangeType : Any> ColumnPointer<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this, scale)

inline fun <reified DomainType : Any, RangeType : Any> KProperty<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this.toColumnPointer(), scale)