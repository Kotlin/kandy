/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.scale

import org.jetbrains.kotlinx.ggdsl.echarts.scale.guide.Axis
import org.jetbrains.kotlinx.ggdsl.echarts.scale.guide.Legend
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale


public data class EchartsPositionalMappingParameters<DomainType>(
    override var scale: PositionalScale<DomainType> = PositionalDefaultScale(),
    public val axis: Axis = Axis()
) : PositionalMappingParameters<DomainType>

public data class EchartsNonPositionalMappingParameters<DomainType, RangeType>(
    override var scale: NonPositionalScale<DomainType, RangeType> = NonPositionalDefaultScale(),
    public val legend: Legend = Legend()
) : NonPositionalMappingParameters<DomainType, RangeType>
