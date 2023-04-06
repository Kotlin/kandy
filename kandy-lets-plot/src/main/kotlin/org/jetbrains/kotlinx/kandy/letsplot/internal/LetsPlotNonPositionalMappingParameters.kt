/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.internal

import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalScale
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.Axis
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.Legend

public data class LetsPlotPositionalMappingParameters<DomainType>
    (override var scale: PositionalScale<out DomainType> = PositionalDefaultScale(), public val axis: Axis<DomainType> = Axis()) :
    PositionalMappingParameters<DomainType> {
}

public data class LetsPlotNonPositionalMappingParameters<DomainType, RangeType>
    (override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale(),
     public val legend: Legend<DomainType, RangeType> = Legend()) :
    NonPositionalMappingParameters<DomainType, RangeType> {
}
