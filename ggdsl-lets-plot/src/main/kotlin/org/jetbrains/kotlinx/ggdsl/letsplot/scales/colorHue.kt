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

/**
 * Creates a qualitative color scale with evenly spaced hues.
 *
 * @param DomainType type of domain
 * @param domainLimits segment defining the domain
 * @param huesRange a pair of numbers Range of hues, in [0,360]
 * @param chroma numeric Chroma (intensity of color), maximum value varies depending on
 * @param luminance numeric Luminance (lightness), in [0,100]
 * @param hueStart list of two numbers Hue to start at
 * @param direction numeric Direction to travel around the color wheel
 * @param transform the transformation of scale
 * @return new [ContinuousScale]/[CustomNonPositionalScale] with given limits
 */
public inline fun <reified DomainType> continuousColorHue(
    huesRange: Pair<Int, Int>? = null,
    chroma: Int? = null,
    luminance: Int? = null,
    hueStart: Int? = null,
    direction: WheelDirection? = null,
    domainLimits: Pair<DomainType & Any, DomainType & Any>? = null,
    transform: Transformation? = null
): ScaleContinuousColorHue<DomainType> = ScaleContinuousColorHue(
    domainLimits?.typedPair(), huesRange, chroma, luminance, hueStart, direction, transform
)

// todo(LP) categories support
public fun <DomainType> categoricalColorHue(
    huesRange: Pair<Int, Int>? = null,
    chroma: Int? = null,
    luminance: Int? = null,
    hueStart: Int? = null,
    direction: WheelDirection? = null,
): ScaleCategoricalColorHue<DomainType> = ScaleCategoricalColorHue<DomainType>(
    huesRange, chroma, luminance, hueStart, direction,
)

@Serializable
public data class WheelDirection internal constructor(val value: Int) {
    public companion object {
        public val CLOCKWISE: WheelDirection = WheelDirection(1)
        public val COUNTER_CLOCKWISE: WheelDirection = WheelDirection(-1)
    }
}

public sealed interface ScaleColorHue<DomainType> {
    public val domainLimits: Pair<TypedValue, TypedValue>?
    public val huesRange: Pair<Int, Int>?
    public val chroma: Int?
    public val luminance: Int?
    public val hueStart: Int?
    public val direction: WheelDirection?
    public val transform: Transformation?
}

@Serializable
public data class ScaleContinuousColorHue<DomainType> @PublishedApi internal constructor(
    override val domainLimits: Pair<TypedValue, TypedValue>?,
    override val huesRange: Pair<Int, Int>?,
    override val chroma: Int?,
    override val luminance: Int?,
    override val hueStart: Int?,
    override val direction: WheelDirection?,
    override val transform: Transformation?
) : ContinuousScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorHue<DomainType>

@Serializable
public data class ScaleCategoricalColorHue<DomainType> @PublishedApi internal constructor(
    override val huesRange: Pair<Int, Int>?,
    override val chroma: Int?,
    override val luminance: Int?,
    override val hueStart: Int?,
    override val direction: WheelDirection?,
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorHue<DomainType> {
    override val domainLimits: Pair<TypedValue, TypedValue>?
        get() = null
    override val transform: Transformation?
        get() = null
}
