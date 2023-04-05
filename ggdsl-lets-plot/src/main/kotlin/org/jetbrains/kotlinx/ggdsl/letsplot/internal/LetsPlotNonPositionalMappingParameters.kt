package org.jetbrains.kotlinx.ggdsl.letsplot.internal

import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Axis
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Legend

public data class LetsPlotPositionalMappingParameters<DomainType>
    (override var scale: PositionalScale<out DomainType> = PositionalDefaultScale(), public val axis: Axis<DomainType> = Axis()) :
    PositionalMappingParameters<DomainType> {
}

public data class LetsPlotNonPositionalMappingParameters<DomainType, RangeType>
    (override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale(),
     public val legend: Legend<DomainType, RangeType> = Legend()) :
    NonPositionalMappingParameters<DomainType, RangeType> {
}
