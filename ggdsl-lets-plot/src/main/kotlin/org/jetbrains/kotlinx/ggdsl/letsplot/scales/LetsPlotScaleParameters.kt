/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import org.jetbrains.kotlinx.ggdsl.ir.bindings.BaseScaledNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.BaseScaledPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreePositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ScaleParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Axis
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Legend

public interface LetsPlotScaleParameters : ScaleParameters

internal data class OrderBy(
    val name: String?,
    val order: Int
)

public data class PositionalParameters<DomainType : Any>(
    val axis: Axis<DomainType>
) : LetsPlotScaleParameters {
    internal var orderBy: OrderBy? = null
}

public data class NonPositionalParameters<DomainType : Any, RangeType : Any>
(val legend: Legend<DomainType, RangeType>) : LetsPlotScaleParameters

public fun <DomainType : Any> BaseScaledPositionalMapping<DomainType>.with(
    block: PositionalParameters<DomainType>.() -> Unit
) {
    scaleParameters = PositionalParameters(Axis<DomainType>()).apply(block)
}

public fun <DomainType : Any> FreePositionalScale<DomainType>.with(
    block: PositionalParameters<DomainType>.() -> Unit
) {
    scaleParameters = PositionalParameters(Axis<DomainType>()).apply(block)
}

public fun <DomainType : Any, RangeType : Any> BaseScaledNonPositionalMapping<DomainType, RangeType>.with(
    block: NonPositionalParameters<DomainType, RangeType>.() -> Unit
) {
    scaleParameters = NonPositionalParameters(Legend<DomainType, RangeType>()).apply(block)
}
