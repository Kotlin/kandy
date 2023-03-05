package org.jetbrains.kotlinx.ggdsl.dsl.impl

import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale

data class CommonPositionalMappingParameters<DomainType>(override var scale: PositionalScale<DomainType>? = null) :
    PositionalMappingParameters<DomainType>

data class CommonNonPositionalMappingParameters<DomainType, RangeType>(override var scale: NonPositionalScale<DomainType, RangeType>? = null) :
    NonPositionalMappingParameters<DomainType, RangeType>