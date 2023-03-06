package org.jetbrains.kotlinx.ggdsl.ir.bindings

import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.Scale

public interface MappingParameters {
    public val scale: Scale
}

public interface PositionalMappingParameters<DomainType>: MappingParameters {
    public override var scale: PositionalScale<DomainType>
}

public interface NonPositionalMappingParameters<DomainType, RangeType>: MappingParameters {
    public override var scale: NonPositionalScale<DomainType, RangeType>
}
