package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.KProperty

/**
 *  Apply a default scale to this [DataSource]
 */
public fun <DomainType : Any> DataSource<DomainType>.scaled(): SourceScaledUnspecifiedDefault<DomainType> =
    SourceScaledUnspecifiedDefault(this)

public inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(): SourceScaledUnspecifiedDefault<DomainType> =
    SourceScaledUnspecifiedDefault(this.toDataSource())

/**
 * Apply an unspecified positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
public fun <DomainType : Any> DataSource<DomainType>.scaled(scale: PositionalUnspecifiedScale): SourceScaledPositionalUnspecified<DomainType> =
    SourceScaledPositionalUnspecified(this, scale)

public inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(scale: PositionalUnspecifiedScale): SourceScaledPositionalUnspecified<DomainType> =
    SourceScaledPositionalUnspecified(this.toDataSource(), scale)

/**
 * Apply unspecified non-positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
public fun <DomainType : Any> DataSource<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): SourceScaledNonPositionalUnspecified<DomainType> =
    SourceScaledNonPositionalUnspecified(this, scale)

public inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): SourceScaledNonPositionalUnspecified<DomainType> =
    SourceScaledNonPositionalUnspecified(this.toDataSource(), scale)

/**
 * Apply a positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
public fun <DomainType : Any> DataSource<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): SourceScaledPositional<DomainType> = SourceScaledPositional(this, scale)

public inline fun <reified DomainType : Any> KProperty<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): SourceScaledPositional<DomainType> = SourceScaledPositional(this.toDataSource(), scale)

/**
 * Apply a non-positional scale to this [DataSource]
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
public fun <DomainType : Any, RangeType : Any> DataSource<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): SourceScaledNonPositional<DomainType, RangeType> = SourceScaledNonPositional(this, scale)

public inline fun <reified DomainType : Any, RangeType : Any> KProperty<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): SourceScaledNonPositional<DomainType, RangeType> = SourceScaledNonPositional(this.toDataSource(), scale)