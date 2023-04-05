/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales

import org.jetbrains.kotlinx.kandy.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.kandy.util.color.Color

public inline fun <reified DomainType : Comparable<DomainType>> continuousColorGrey(
    paletteRange: Pair<Double, Double>? = null,
    domainLimits: ClosedRange<DomainType>,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGrey<DomainType> = ScaleContinuousColorGrey(
    paletteRange, domainLimits.let {
        it.start to it.endInclusive
    }, nullValue, transform
)

public inline fun <reified DomainType : Comparable<DomainType>> continuousColorGrey(
    paletteRange: Pair<Double, Double>? = null,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorGrey<DomainType> = ScaleContinuousColorGrey(
    paletteRange, domainMin to domainMax, nullValue, transform
)

public inline fun <reified DomainType> categoricalColorGrey(
    paletteRange: Pair<Double, Double>? = null,
    //nullValue: Color? = null,
): ScaleCategoricalColorGrey<DomainType> = ScaleCategoricalColorGrey<DomainType>(
    paletteRange, //nullValue?
)

public sealed interface ScaleColorGrey<DomainType> {
    public val paletteRange: Pair<Double, Double>?
    public val domainLimits: Pair<DomainType?, DomainType?>?
    public val transform: Transformation?
}

//@Serializable
public data class ScaleContinuousColorGrey<DomainType> @PublishedApi internal constructor(
    override val paletteRange: Pair<Double, Double>?,
    override val domainLimits: Pair<DomainType?, DomainType?>,
    override val nullValue: Color?,
    override val transform: Transformation?,
) : ContinuousScale<Color>, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType>

//@Serializable
public data class ScaleCategoricalColorGrey<DomainType> @PublishedApi internal constructor(
    override val paletteRange: Pair<Double, Double>?,
    //override val nullValue: TypedValue?
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorGrey<DomainType> {
    override val domainLimits: Pair<DomainType & Any, DomainType & Any>?
        get() = null
    override val transform: Transformation?
        get() = null
}
