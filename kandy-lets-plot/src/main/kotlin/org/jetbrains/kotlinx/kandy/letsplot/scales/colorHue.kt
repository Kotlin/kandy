/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales

import org.jetbrains.kotlinx.kandy.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.kandy.util.color.Color

/**
 * Creates a qualitative continuous color scale with evenly spaced hues.
 *
 * @param DomainType scale domain type.
 * @param domainLimits [ClosedRange] defining the scale domain.
 * @param huesRange [ClosedRange] of hues, in [0,360]
 * @param chroma numeric Chroma (intensity of color), maximum value varies depending on
 * @param luminance numeric Luminance (lightness), in [0,100]
 * @param hueStart number Hue to start at
 * @param direction [WheelDirection] to travel around the color wheel.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 * @return new continuous color scale.
 */
public inline fun <reified DomainType : Comparable<DomainType>> continuousColorHue(
    huesRange: ClosedRange<Int>? = null,
    chroma: Int? = null,
    luminance: Int? = null,
    hueStart: Int? = null,
    direction: WheelDirection? = null,
    domainLimits: ClosedRange<DomainType>,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorHue<DomainType> = ScaleContinuousColorHue(
    domainLimits.let {
        it.start to it.endInclusive
    }, huesRange?.let {
        it.start to it.endInclusive
    }, chroma, luminance, hueStart, direction, nullValue, transform
)

/**
 * Creates a qualitative continuous color scale with evenly spaced hues.
 *
 * @param DomainType scale domain type.
 * @param domainMin scale domain minimum.
 * @param domainMax scale domain maximum.
 * @param huesRange [ClosedRange] of hues, in [0,360]
 * @param chroma numeric Chroma (intensity of color), maximum value varies depending on
 * @param luminance numeric Luminance (lightness), in [0,100]
 * @param hueStart number Hue to start at
 * @param direction [WheelDirection] to travel around the color wheel.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 * @return new continuous color scale.
 */
public inline fun <reified DomainType : Comparable<DomainType>> continuousColorHue(
    huesRange: ClosedRange<Int>? = null,
    chroma: Int? = null,
    luminance: Int? = null,
    hueStart: Int? = null,
    direction: WheelDirection? = null,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorHue<DomainType> = ScaleContinuousColorHue(
    domainMin to domainMax, huesRange?.let {
        it.start to it.endInclusive
    }, chroma, luminance, hueStart, direction, nullValue, transform
)


// TODO(https://github.com/Kotlin/kandy/issues/418)
/**
 * Creates a qualitative categorical color scale with evenly spaced hues.
 *
 * @param DomainType scale domain type.
 * @param huesRange [ClosedRange] of hues, in [0,360]
 * @param chroma numeric Chroma (intensity of color), maximum value varies depending on
 * @param luminance numeric Luminance (lightness), in [0,100]
 * @param hueStart number Hue to start at
 * @param direction [WheelDirection] to travel around the color wheel.
 * @return new categorical color scale.
 */
public fun <DomainType> categoricalColorHue(
    huesRange: Pair<Int, Int>? = null,
    chroma: Int? = null,
    luminance: Int? = null,
    hueStart: Int? = null,
    direction: WheelDirection? = null,
): ScaleCategoricalColorHue<DomainType> = ScaleCategoricalColorHue<DomainType>(
    huesRange, chroma, luminance, hueStart, direction,
)

public data class WheelDirection internal constructor(val value: Int) {
    public companion object {
        public val CLOCKWISE: WheelDirection = WheelDirection(1)
        public val COUNTER_CLOCKWISE: WheelDirection = WheelDirection(-1)
    }
}

public sealed interface ScaleColorHue<DomainType> {
    public val domainLimits: Pair<DomainType?, DomainType?>?
    public val huesRange: Pair<Int, Int>?
    public val chroma: Int?
    public val luminance: Int?
    public val hueStart: Int?
    public val direction: WheelDirection?
    public val transform: Transformation?
}

public data class ScaleContinuousColorHue<DomainType> @PublishedApi internal constructor(
    override val domainLimits: Pair<DomainType?, DomainType?>?,
    override val huesRange: Pair<Int, Int>?,
    override val chroma: Int?,
    override val luminance: Int?,
    override val hueStart: Int?,
    override val direction: WheelDirection?,
    override val nullValue: Color?,
    override val transform: Transformation?
) : ContinuousScale<Color>, CustomNonPositionalScale<DomainType, Color>, ScaleColorHue<DomainType>

public data class ScaleCategoricalColorHue<DomainType> @PublishedApi internal constructor(
    override val huesRange: Pair<Int, Int>?,
    override val chroma: Int?,
    override val luminance: Int?,
    override val hueStart: Int?,
    override val direction: WheelDirection?,
    //override val nullValue: TypedValue?,
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorHue<DomainType> {
    override val domainLimits: Pair<DomainType & Any, DomainType & Any>?
        get() = null
    override val transform: Transformation?
        get() = null
}
