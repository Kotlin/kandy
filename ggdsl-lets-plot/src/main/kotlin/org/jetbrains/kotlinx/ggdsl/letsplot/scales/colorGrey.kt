/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.typed
import org.jetbrains.kotlinx.ggdsl.dsl.internal.typedPair
import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue

public inline fun <reified DomainType> continuousColorGrey(
    paletteRange: Pair<Double, Double>? = null,
    domainLimits: Pair<DomainType & Any, DomainType & Any>? = null,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGrey<DomainType> = ScaleContinuousColorGrey(
    paletteRange, domainLimits?.typedPair(), nullValue?.typed(), transform
)

public inline fun <reified DomainType> categoricalColorGrey(
    paletteRange: Pair<Double, Double>? = null,
    //nullValue: Color? = null,
): ScaleCategoricalColorGrey<DomainType> = ScaleCategoricalColorGrey<DomainType>(
    paletteRange, //nullValue?.typed()
)

public sealed interface ScaleColorGrey<DomainType> {
    public val paletteRange: Pair<Double, Double>?
    public val domainLimits: Pair<TypedValue, TypedValue>?
    public val transform: Transformation?
}

@Serializable
public data class ScaleContinuousColorGrey<DomainType> @PublishedApi internal constructor(
    override val paletteRange: Pair<Double, Double>? = null,
    override val domainLimits: Pair<TypedValue, TypedValue>? = null,
    override val nullValue: TypedValue?,
    override val transform: Transformation? = null,
) : ContinuousScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType>

@Serializable
public data class ScaleCategoricalColorGrey<DomainType> @PublishedApi internal constructor(
    override val paletteRange: Pair<Double, Double>? = null,
    //override val nullValue: TypedValue?
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType> {
    override val domainLimits: Pair<TypedValue, TypedValue>?
        get() = null
    override val transform: Transformation?
        get() = null
}
