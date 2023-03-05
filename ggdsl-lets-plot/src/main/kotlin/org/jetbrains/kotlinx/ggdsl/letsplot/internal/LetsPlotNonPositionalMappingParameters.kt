package org.jetbrains.kotlinx.ggdsl.letsplot.internal

import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Axis
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Legend

public class LetsPlotPositionalMappingParameters<DomainType>
    (override var scale: PositionalScale<DomainType>? = null) :
    PositionalMappingParameters<DomainType> {
    public val axis: Axis<DomainType> = Axis()
}

public class LetsPlotNonPositionalMappingParameters<DomainType, RangeType>
    (override var scale: NonPositionalScale<DomainType, RangeType>? = null) :
    NonPositionalMappingParameters<DomainType, RangeType> {
        public val axis: Legend<DomainType, RangeType> = Legend()
}
