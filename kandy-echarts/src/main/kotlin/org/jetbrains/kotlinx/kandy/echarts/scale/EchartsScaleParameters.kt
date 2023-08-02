/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.scale

import org.jetbrains.kotlinx.kandy.echarts.scale.guide.Axis
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.scale.*


public interface EchartsPositionalMappingParameters<DomainType> : PositionalMappingParameters<DomainType> {
    public val axis: Axis
}

/*public data class EchartsPositionalMappingParametersCategorical<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale(),
    public override val axis: Axis = Axis()
) : EchartsPositionalMappingParameters<DomainType>*/

public data class EchartsPositionalMappingParametersContinuous<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale(),
    public override val axis: Axis = Axis()
) : EchartsPositionalMappingParameters<DomainType>, PositionalMappingParametersContinuous<DomainType>

public interface EchartsNonPositionalMappingParameters<DomainType, RangeType>
    : NonPositionalMappingParameters<DomainType, RangeType>

public data class EchartsNonPositionalMappingParametersCategorical<DomainType, RangeType>(
    override var scale: NonPositionalCategoricalScaleBase<out DomainType, out RangeType> =
        NonPositionalDefaultCategoricalScale<DomainType, RangeType>(),
) : EchartsNonPositionalMappingParameters<DomainType, RangeType>

public data class EchartsNonPositionalMappingParametersContinuous<DomainType, RangeType>(
    override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale(),
) : EchartsNonPositionalMappingParameters<DomainType, RangeType>,
    NonPositionalMappingParametersContinuous<DomainType, RangeType>
