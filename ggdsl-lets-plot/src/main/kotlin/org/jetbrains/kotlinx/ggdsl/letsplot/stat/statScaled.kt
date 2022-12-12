package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.dsl.ScalableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.typeOf

public inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(): ColumnScaledUnspecifiedDefault<DomainType> =
    ColumnScaledUnspecifiedDefault(this.toColumnPointer())

public inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(scale: PositionalUnspecifiedScale): ColumnScaledPositionalUnspecified<DomainType> =
    ColumnScaledPositionalUnspecified(this.toColumnPointer(), scale)

public inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): ColumnScaledNonPositionalUnspecified<DomainType> =
    ColumnScaledNonPositionalUnspecified(this.toColumnPointer(), scale)

public inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): ColumnScaledPositional<DomainType> = ColumnScaledPositional(this.toColumnPointer(), scale)

public inline fun <reified DomainType : Any, RangeType : Any> Statistic<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): ColumnScaledNonPositional<DomainType, RangeType> = ColumnScaledNonPositional(this.toColumnPointer(), scale)

public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    stat: Statistic<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping(
        this.name,
        stat.toColumnPointer().scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

public inline operator fun <reified DomainType : Any, RangeType : Any>
        ScalableNonPositionalAes<RangeType>.invoke(
    stat: Statistic<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        stat.toColumnPointer().scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}
