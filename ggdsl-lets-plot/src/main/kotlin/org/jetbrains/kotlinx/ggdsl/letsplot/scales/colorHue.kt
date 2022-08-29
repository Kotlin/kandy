package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.ggdsl.util.color.Color

/**
 * Creates qualitative color scale with evenly spaced hues.
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
fun<DomainType: Any> continuousColorHue(
    domainLimits: Pair<DomainType, DomainType>? = null,
    huesRange: Pair<Int, Int>? = null,
    chroma: Int? = null,
    luminance: Int? = null,
    hueStart: Int? = null,
    direction: WheelDirection? = null,
    transform: Transformation? = null
) = ScaleContinuousColorHue(
    domainLimits, huesRange, chroma, luminance, hueStart, direction, transform
)

fun<DomainType: Any> categoricalColorHue(
    huesRange: Pair<Int, Int>? = null,
    chroma: Int? = null,
    luminance: Int? = null,
    hueStart: Int? = null,
    direction: WheelDirection? = null,
) = ScaleCategoricalColorHue<DomainType>(
   huesRange, chroma, luminance, hueStart, direction,
)

data class WheelDirection internal constructor(val value: Int) {
    companion object {
        val CLOCKWISE = WheelDirection(1)
        val COUNTER_CLOCKWISE = WheelDirection(-1)
    }
}

sealed interface ScaleColorHue<DomainType: Any>{
    val domainLimits: Pair<DomainType, DomainType>?
    val huesRange: Pair<Int, Int>?
    val chroma: Int?
    val luminance: Int?
    val hueStart: Int?
    val direction: WheelDirection?
    val transform: Transformation?
}


data class ScaleContinuousColorHue<DomainType: Any> internal constructor(
    override val domainLimits: Pair<DomainType, DomainType>? = null,
    override val huesRange: Pair<Int, Int>? = null,
    override val chroma: Int? = null,
    override val luminance: Int? = null,
    override val hueStart: Int? = null,
    override val direction: WheelDirection? = null,
    override val transform: Transformation? = null
): ContinuousScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorHue<DomainType>

data class ScaleCategoricalColorHue<DomainType: Any> internal constructor(
    override val huesRange: Pair<Int, Int>? = null,
    override val chroma: Int? = null,
    override val luminance: Int? = null,
    override val hueStart: Int? = null,
    override val direction: WheelDirection? = null,
): CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorHue<DomainType> {
    override val domainLimits: Pair<DomainType, DomainType>?
        get() = null
    override val transform: Transformation?
        get() = null
}