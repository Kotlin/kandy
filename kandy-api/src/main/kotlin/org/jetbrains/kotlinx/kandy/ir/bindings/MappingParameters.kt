package org.jetbrains.kotlinx.kandy.ir.bindings

import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.Scale

public interface MappingParameters {
    public val scale: Scale
}

public interface PositionalMappingParameters<DomainType>: MappingParameters {
    public override var scale: PositionalScale<DomainType>
}

public interface NonPositionalMappingParameters<DomainType, RangeType>: MappingParameters {
    public override var scale: NonPositionalScale<DomainType, RangeType>
}
