/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.internal.toColumnPointer
import org.jetbrains.kotlinx.ggdsl.dsl.NonScalableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.ScalableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonScalableNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonScalablePositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import kotlin.reflect.typeOf

/**
 * Maps the given [ColumnReference] to this non-scalable positional ("sub-positional") aesthetic attribute.
 *
 * @param columnRef the mapped raw data column.
 */
public inline operator fun <reified DomainType> NonScalablePositionalAes.invoke(
    columnRef: ColumnReference<DomainType>
) {
    context.bindingCollector.mappings[this.name] =
        NonScalablePositionalMapping(this.name, columnRef.toColumnPointer(), typeOf<DomainType>())
}

/**
 * Maps the given [ColumnReference] to this non-scalable non-positional aesthetic attribute.
 *
 * @param columnRef the mapped raw data column.
 */
public inline operator fun <reified DomainType, RangeType: Any> NonScalableNonPositionalAes<RangeType>.invoke(
    columnRef: ColumnReference<DomainType>
) {
    context.bindingCollector.mappings[this.name] =
        NonScalableNonPositionalMapping(this.name, columnRef.toColumnPointer(), typeOf<DomainType>())
}

/**
 * Maps the given [ColumnReference] to this positional aesthetic attribute with an unspecified
 * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
 *
 * @param columnRef the mapped raw data column.
 */
public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
    columnRef: ColumnReference<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping(
        this.name,
        columnRef.scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

/**
 * Maps the given [ColumnReference] to this non-positional aesthetic attribute with an unspecified
 * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
 *
 * @param columnRef the mapped raw data column.
 */
public inline operator fun <reified DomainType, RangeType> ScalableNonPositionalAes<RangeType>.invoke(
    columnRef: ColumnReference<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        columnRef.scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}
