/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.bindings.BaseScaledNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.BaseScaledPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreePositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ScaleParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Axis
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Legend

public sealed interface LetsPlotScaleParameters : ScaleParameters

//@Serializable
internal data class OrderBy(
    val name: String?,
    val order: Int
)

//@Serializable
public data class PositionalParameters<DomainType>(
    val axis: Axis<DomainType>
) : LetsPlotScaleParameters {
    internal var orderBy: OrderBy? = null
}

//@Serializable
public data class NonPositionalParameters<DomainType, RangeType>(val legend: Legend<DomainType, RangeType>)
    : LetsPlotScaleParameters

public inline fun <reified DomainType> BaseScaledPositionalMapping<DomainType>.with(
    block: PositionalParameters<DomainType>.() -> Unit
) {
    scaleParameters = PositionalParameters(Axis.create<DomainType>()).apply(block)
}

public inline fun <reified DomainType> FreePositionalScale<DomainType>.with(
    block: PositionalParameters<DomainType>.() -> Unit
) {
    scaleParameters = PositionalParameters(Axis.create<DomainType>()).apply(block)
}

public inline fun <reified DomainType, RangeType> BaseScaledNonPositionalMapping<DomainType, RangeType>.with(
    block: NonPositionalParameters<DomainType, RangeType>.() -> Unit
) {
    scaleParameters = NonPositionalParameters(Legend.create<DomainType, RangeType>()).apply(block)
}
