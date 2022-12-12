package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.ColorSerializer
import kotlin.math.round

@Serializable(with = ColorSerializer::class)
public interface EchartsColor

internal class BaseColor internal constructor(val hex: String) : EchartsColor

@Serializable
internal sealed class GradientColor(
    val type: String,
    val x: Double?,
    val y: Double?,
    val x2: Double?,
    val y2: Double?,
    val r: Double?,
    val colorStops: List<ColorStop>?,
    val global: Boolean?
) : EchartsColor

internal class LinearGradientColor(
    x: Double?,
    y: Double?,
    x2: Double?,
    y2: Double?,
    colorStops: List<ColorStop>?,
    global: Boolean?
) : GradientColor("linear", x, y, x2, y2, null, colorStops, global)

internal class RadialGradientColor(
    x: Double?,
    y: Double?,
    r: Double?,
    colorStops: List<ColorStop>?,
    global: Boolean?
) : GradientColor("radial", x, y, null, null, r, colorStops, global)

@Serializable
internal data class ColorStop(val offset: Double, val color: String)
