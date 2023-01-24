/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this column code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import kotlin.reflect.typeOf

/**
 * Maps the given [ColumnPointer] to this non-scalable positional ("sub-positional") aesthetic attribute.
 *
 * @param column the mapped raw data column.
 */
public inline operator fun <reified DomainType> NonScalablePositionalAes.invoke(
    column: ColumnPointer<DomainType>
) {
    context.bindingCollector.mappings[this.name] =
        NonScalablePositionalMapping(this.name, column, typeOf<DomainType>())
}

/**
 * Maps the given [ColumnPointer] to this non-scalable non-positional aesthetic attribute.
 *
 * @param column the mapped raw data column.
 */
public inline operator fun <reified DomainType, RangeType> NonScalableNonPositionalAes<RangeType>.invoke(
    column: ColumnPointer<DomainType>
) {
    context.bindingCollector.mappings[this.name] =
        NonScalableNonPositionalMapping(this.name, column, typeOf<DomainType>())
}

/**
 * Maps the given [ColumnPointer] to this positional aesthetic attribute with an unspecified
 * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
 *
 * @param column the mapped raw data column.
 */
public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
    column: ColumnPointer<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping<DomainType>(
        this.name,
        column.scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

/**
 * Maps the given [ColumnPointer] to this non-positional aesthetic attribute with an unspecified
 * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
 *
 * @param column the mapped raw data column.
 */
public inline operator fun <reified DomainType, RangeType> ScalableNonPositionalAes<RangeType>.invoke(
    column: ColumnPointer<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        column.scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

/**
 * Maps the given scaled column to this positional aesthetic attribute with an unspecified
 * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
 *
 * @param columnScaledDefault the mapped column scaled default.
 */
public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
    columnScaledDefault: ColumnScaledUnspecifiedDefault<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping(
        this.name,
        columnScaledDefault,
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

/**
 * Maps the given scaled column to this non-positional aesthetic attribute with an unspecified
 * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
 *
 * @param columnScaledDefault the mapped column scaled default.
 */
public inline operator fun <reified DomainType, RangeType> ScalableNonPositionalAes<RangeType>.invoke(
    columnScaledDefault: ColumnScaledUnspecifiedDefault<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        columnScaledDefault,
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

/**
 * Maps the given scaled column to this positional aesthetic attribute with a default
 * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
 *
 * @param columnScaledDefault the mapped column scaled unspecified positional.
 */
public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
    columnScaledDefault: ColumnScaledPositionalUnspecified<DomainType>
): ScaledPositionalUnspecifiedMapping<DomainType> {
    val mapping = ScaledPositionalUnspecifiedMapping(
        this.name,
        columnScaledDefault,
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}


/**
 * Maps the given scaled column to this non-positional aesthetic attribute with an unspecified
 * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
 *
 * @param columnScaledDefault the mapped column scaled unspecified non-positional.
 */
public inline operator fun <reified DomainType, RangeType> ScalableNonPositionalAes<RangeType>.invoke(
    columnScaledDefault: ColumnScaledNonPositionalUnspecified<DomainType>
): ScaledNonPositionalUnspecifiedMapping<DomainType, RangeType> {
    val mapping = ScaledNonPositionalUnspecifiedMapping<DomainType, RangeType>(
        this.name,
        columnScaledDefault,
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

/**
 * Maps the given scaled column to this positional aesthetic attribute.
 *
 * @param columnScaledPositional the mapped column scaled positional.
 */
public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
    columnScaledPositional: ColumnScaledPositional<DomainType>
): ScaledPositionalMapping<DomainType> {
    val mapping = ScaledPositionalMapping(
        this.name,
        columnScaledPositional,
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

/**
 * Maps the given scaled column to this non-positional aesthetic attribute.
 *
 * @param columnScaledNonPositional the mapped column scaled non-positional.
 */
public inline operator fun <reified DomainType, reified RangeType>
        ScalableNonPositionalAes<RangeType>.invoke(
    columnScaledNonPositional: ColumnScaledNonPositional<DomainType, RangeType>
): ScaledNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledNonPositionalMapping(
        this.name,
        columnScaledNonPositional,
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}
