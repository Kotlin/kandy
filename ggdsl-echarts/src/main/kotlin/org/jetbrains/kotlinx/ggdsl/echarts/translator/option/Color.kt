package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.ColorSerializer
import kotlin.math.round

@Serializable(with = ColorSerializer::class)
public interface Color

internal class BaseColor private constructor() : Color {
    lateinit var hex: String

    constructor(r: Byte, g: Byte, b: Byte, a: Double) : this() {
        hex = buildString {
            append("#")
            append(r.toString(16))
            append(g.toString(16))
            append(b.toString(16))
            if (a != 1.0) {
                append(
                    (round(a * 255).toInt() or (1 shr 8)).toString(16) // ~ [0..1)
                )
            }
        }
    }

    constructor(r: Byte, g: Byte, b: Byte) : this(r, g, b, 1.0)

    constructor(colorName: String) : this() {
        hex = "#ccc"
        TODO("сопоставление!")
    }
}

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
) : Color

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
