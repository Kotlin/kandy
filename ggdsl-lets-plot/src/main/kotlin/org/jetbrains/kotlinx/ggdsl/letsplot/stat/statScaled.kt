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

public inline fun <reified DomainType> Statistic<DomainType>.scaled(): ColumnScaledUnspecifiedDefault<DomainType> =
    ColumnScaledUnspecifiedDefault(this.toColumnReference())

public inline fun <reified DomainType> Statistic<DomainType>.scaled(scale: PositionalUnspecifiedScale): ColumnScaledPositionalUnspecified<DomainType> =
    ColumnScaledPositionalUnspecified(this.toColumnReference(), scale)

public inline fun <reified DomainType> Statistic<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): ColumnScaledNonPositionalUnspecified<DomainType> =
    ColumnScaledNonPositionalUnspecified(this.toColumnReference(), scale)

public inline fun <reified DomainType> Statistic<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): ColumnScaledPositional<DomainType> = ColumnScaledPositional(this.toColumnReference(), scale)

public inline fun <reified DomainType, RangeType> Statistic<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): ColumnScaledNonPositional<DomainType, RangeType> = ColumnScaledNonPositional(this.toColumnReference(), scale)

public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
    stat: Statistic<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping(
        this.name,
        stat.toColumnReference().scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

public inline operator fun <reified DomainType, RangeType>
        ScalableNonPositionalAes<RangeType>.invoke(
    stat: Statistic<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        stat.toColumnReference().scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}
