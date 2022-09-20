/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.ggdsl.util.color.Color

fun<DomainType: Any> continuousColorGrey(
    paletteRange: Pair<Double, Double>? = null,
    domainLimits: Pair<DomainType, DomainType>? = null,
    transform: Transformation? = null
) = ScaleContinuousColorGrey(
    paletteRange, domainLimits, transform
)

fun<DomainType: Any> categoricalColorGrey(
    paletteRange: Pair<Double, Double>? = null,
) = ScaleCategoricalColorGrey<DomainType>(
    paletteRange
)


sealed interface ScaleColorGrey<DomainType: Any>{
    val paletteRange: Pair<Double, Double>?
    val domainLimits: Pair<DomainType, DomainType>?
    val transform: Transformation?
}


data class ScaleContinuousColorGrey<DomainType: Any> internal constructor(
    override val paletteRange: Pair<Double, Double>? = null,
    override val domainLimits: Pair<DomainType, DomainType>? = null,
    override val transform: Transformation? = null,
): ContinuousScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType>

data class ScaleCategoricalColorGrey<DomainType: Any> internal constructor(
    override val paletteRange: Pair<Double, Double>? = null
): CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType> {
    override val domainLimits: Pair<DomainType, DomainType>?
        get() = null
    override val transform: Transformation?
        get() = null
}
