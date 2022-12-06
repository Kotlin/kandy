package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.column.toColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonScalablePositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import kotlin.reflect.typeOf
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale


/**
 * Maps column with the given name to this non-scalable ("sub-positional") aesthetic attribute.
 *
 * @param column the mapped raw data column.
 */
public operator fun NonScalablePositionalAes.invoke(
    column: String
) {
    context.bindingCollector.mappings[this.name] =
        NonScalablePositionalMapping(this.name, column.toColumnPointer(), typeOf<Any>())
}

/**
 * Maps column with the given name to this positional aesthetic attribute with default scale.
 *
 * @param column the mapped raw data column.
 */
public operator fun ScalablePositionalAes.invoke(
    column: String
): ScaledUnspecifiedDefaultPositionalMapping<Any> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping<Any>(
        this.name,
        column.toColumnPointer<Any>().scaled(),
        typeOf<Any>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

/**
 * Maps column with the given name to this non-positional aesthetic attribute with default scale.
 *
 * @param column the mapped raw data column.
 */
public operator fun <RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
    column: String
): ScaledUnspecifiedDefaultNonPositionalMapping<Any, RangeType>     {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<Any, RangeType>(
        this.name,
        column.toColumnPointer<Any>().scaled(),
        typeOf<Any>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}


/**
 *  Apply default scale to this [ColumnPointer]
 */
public fun String.scaled(): ColumnScaledUnspecifiedDefault<Any> =
    ColumnScaledUnspecifiedDefault(this.toColumnPointer())

/**
 * Apply unspecified positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale positional default scale
 * @return scaled source
 */
public fun String.scaled(scale: PositionalUnspecifiedScale):
        ColumnScaledPositionalUnspecified<Any> =
    ColumnScaledPositionalUnspecified(this.toColumnPointer(), scale)

/**
 * Apply unspecified non-positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale non-positional default scale
 * @return scaled source
 */
public fun String.scaled(scale: NonPositionalUnspecifiedScale):
        ColumnScaledNonPositionalUnspecified<Any> =
    ColumnScaledNonPositionalUnspecified(this.toColumnPointer(), scale)

/**
 * Apply positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale positional scale
 * @return scaled source
 */
public fun <DomainType : Any> String.scaled(
    scale: PositionalScale<DomainType>
): ColumnScaledPositional<DomainType> = ColumnScaledPositional(this.toColumnPointer<DomainType>(), scale)

/**
 * Apply non-positional scale to this [ColumnPointer]
 *
 * @param DomainType type of domain
 * @param scale non-positional scale
 * @return scaled source
 */
public fun <DomainType : Any, RangeType : Any> String.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): ColumnScaledNonPositional<DomainType, RangeType> = ColumnScaledNonPositional(this.toColumnPointer<DomainType>(), scale)
