/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.typedPair
import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue

public inline fun <reified DomainType : Any> continuousColorGrey(
    paletteRange: Pair<Double, Double>? = null,
    domainLimits: Pair<DomainType, DomainType>? = null,
    transform: Transformation? = null
): ScaleContinuousColorGrey<DomainType> = ScaleContinuousColorGrey(
    paletteRange, domainLimits?.typedPair(), transform
)

public fun <DomainType : Any> categoricalColorGrey(
    paletteRange: Pair<Double, Double>? = null,
): ScaleCategoricalColorGrey<DomainType> = ScaleCategoricalColorGrey<DomainType>(
    paletteRange
)

public sealed interface ScaleColorGrey<DomainType : Any> {
    public val paletteRange: Pair<Double, Double>?
    public val domainLimits: Pair<TypedValue, TypedValue>?
    public val transform: Transformation?
}

@Serializable
public data class ScaleContinuousColorGrey<DomainType : Any>(
    override val paletteRange: Pair<Double, Double>? = null,
    override val domainLimits: Pair<TypedValue, TypedValue>? = null,
    override val transform: Transformation? = null,
) : ContinuousScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType>

@Serializable
public data class ScaleCategoricalColorGrey<DomainType : Any> internal constructor(
    override val paletteRange: Pair<Double, Double>? = null
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType> {
    override val domainLimits: Pair<TypedValue, TypedValue>?
        get() = null
    override val transform: Transformation?
        get() = null
}
