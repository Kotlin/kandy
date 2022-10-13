package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.typeOf

public inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(): ColumnScaledUnspecifiedDefault<DomainType> =
    ColumnScaledUnspecifiedDefault(this.tColumnPointer())


public inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(scale: PositionalUnspecifiedScale): ColumnScaledPositionalUnspecified<DomainType> =
    ColumnScaledPositionalUnspecified(this.tColumnPointer(), scale)



public inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): ColumnScaledNonPositionalUnspecified<DomainType> =
    ColumnScaledNonPositionalUnspecified(this.tColumnPointer(), scale)


public inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): ColumnScaledPositional<DomainType> = ColumnScaledPositional(this.tColumnPointer(), scale)


public inline fun <reified DomainType : Any, RangeType : Any> Statistic<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): ColumnScaledNonPositional<DomainType, RangeType> = ColumnScaledNonPositional(this.tColumnPointer(), scale)

public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    stat: Statistic<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping(
        this.name,
        stat.tColumnPointer().scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

public inline operator fun <reified DomainType : Any, RangeType : Any>
        MappableNonPositionalAes<RangeType>.invoke(
    stat: Statistic<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        stat.tColumnPointer().scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}
