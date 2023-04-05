package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalScale

data class CommonPositionalMappingParameters<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale()
) : PositionalMappingParameters<DomainType>

data class CommonNonPositionalMappingParameters<DomainType, RangeType>(
    override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale()
) : NonPositionalMappingParameters<DomainType, RangeType>
