/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales

import org.jetbrains.kotlinx.kandy.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.kandy.util.color.Color

/**
 *  Viridis colormap.
 */
public enum class ViridisColormap {
    MAGMA, INFERNO, PLASMA, VIRIDIS, CIVIDIS, TURBO, TWILIGHT
}

public sealed interface ScaleColorViridis<DomainType> {
    public val limits: List<DomainType?>?
    public val colormap: ViridisColormap
    public val hueRange:  ClosedRange<Double>

    public val direction: WheelDirection
    public val transform: Transformation?
}

public data class ScaleContinuousColorViridis<DomainType>(
    override val limits: List<DomainType?>?,
    override val colormap: ViridisColormap,
    override val hueRange: ClosedRange<Double>,
    override val direction: WheelDirection,

    override val nullValue: Color?,
    override val transform: Transformation?,
) : ContinuousScale<Color>, CustomNonPositionalScale<DomainType, Color>, ScaleColorViridis<DomainType>

public data class ScaleCategoricalColorViridis<DomainType>(
    override val limits: List<DomainType>?,
    override val colormap: ViridisColormap,
    override val hueRange: ClosedRange<Double>,
    override val direction: WheelDirection,
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorViridis<DomainType> {
    override val transform: Transformation? = null
}


/**
 * Color scale with viridis color maps, designed to be perceptually-uniform,
 * both in regular form and also when converted to black-and-white.
 *
 * @param colormap [ViridisColormap] colormap
 * @param DomainType scale domain type.
 * @param hueRange [ClosedRange] of color hue, in [0, 1]
 * @param direction colormap direction
 * @param domain [ClosedRange] defining the scale domain.
 * @param nullValue value which null is mapped to.
 * @param transform the transformation of scale.
 *
 * @return new continuous color scale.
 */
public fun <DomainType : Comparable<DomainType>> continuousColorViridis(
    colormap: ViridisColormap = ViridisColormap.VIRIDIS,
    hueRange: ClosedRange<Double> = 0.0..1.0,
    direction: WheelDirection = WheelDirection.CLOCKWISE,
    domain: ClosedRange<DomainType>,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorViridis<DomainType> = ScaleContinuousColorViridis(
    domain.let {
        listOf(it.start, it.endInclusive)
    }, colormap, hueRange, direction, nullValue, transform
)

/**
 * Color scale with viridis color maps, designed to be perceptually-uniform,
 * both in regular form and also when converted to black-and-white.
 *
 * @param colormap [ViridisColormap] colormap
 * @param DomainType scale domain type.
 * @param hueRange [ClosedRange] of color hue, in [0, 1]
 * @param direction colormap direction
 * @param domainMin scale domain minimum.
 * @param domainMax scale domain maximum.
 * @param nullValue value which null is mapped to.
 * @param transform scale transformation.
 *
 * @return new continuous color scale.
 */
public fun <DomainType> continuousColorViridis(
    colormap: ViridisColormap = ViridisColormap.VIRIDIS,
    hueRange: ClosedRange<Double> = 0.0..1.0,
    direction: WheelDirection = WheelDirection.CLOCKWISE,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorViridis<DomainType> = ScaleContinuousColorViridis(
    listOf(domainMin, domainMax), colormap, hueRange, direction, nullValue, transform
)

/**
 * Color scale with viridis color maps, designed to be perceptually-uniform,
 * both in regular form and also when converted to black-and-white.
 *
 * @param colormap [ViridisColormap] colormap
 * @param DomainType scale domain type.
 * @param hueRange [ClosedRange] of color hue, in [0, 1]
 * @param direction colormap direction @param DomainType scale domain type.
 * @param domain [List] defining the scale domain.
 *
 * @return new categorical color scale.
 */
public fun <DomainType> categoricalColorViridis(
    colormap: ViridisColormap = ViridisColormap.VIRIDIS,
    hueRange: ClosedRange<Double> = 0.0..1.0,
    direction: WheelDirection = WheelDirection.CLOCKWISE,
    domain: List<DomainType>? = null,
): ScaleCategoricalColorViridis<DomainType> = ScaleCategoricalColorViridis(
    domain, colormap, hueRange, direction
)
