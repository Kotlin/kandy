package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonScalablePositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import kotlin.reflect.typeOf

/**
 * Mapping the given column to this non-scalable ("sub-positional") aesthetic attribute.
 *
 * @param columnRef the mapped column.
 */
inline operator fun <reified DomainType : Any> NonScalablePositionalAes.invoke(
    columnRef: ColumnReference<DomainType>
) {
    context.bindingCollector.mappings[this.name] =
        NonScalablePositionalMapping(this.name, columnRef.toColRef(), typeOf<DomainType>())
}

/**
 * Mapping the given column to this positional aesthetic attribute with default scale.
 *
 * @param columnRef the mapped column.
 */
inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
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
 * Mapping the given column to this non-positional aesthetic attribute with default scale.
 *
 * @param columnRef the mapped column.
 */
inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
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
