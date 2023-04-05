package org.jetbrains.kotlinx.ggdsl.dsl.impl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale

data class CommonPositionalMappingParameters<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale()
) : PositionalMappingParameters<DomainType>

data class CommonNonPositionalMappingParameters<DomainType, RangeType>(
    override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale()
) : NonPositionalMappingParameters<DomainType, RangeType>
