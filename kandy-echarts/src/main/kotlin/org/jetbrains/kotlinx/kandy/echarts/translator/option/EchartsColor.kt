package org.jetbrains.kotlinx.kandy.echarts.translator.option

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.settings.LinearGradient
import org.jetbrains.kotlinx.kandy.echarts.settings.RadialGradient
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.ColorSerializer
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.RgbColorSerializer
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.RgbaColorSerializer
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.color.StandardColor

internal fun Color.toEchartsColor(): EchartsColor = when (this) {
    is StandardColor.Hex -> EchartsColor.Hex(hexString)
    is StandardColor.Named -> EchartsColor.Named(name)
    is StandardColor.RGB -> EchartsColor.Rgb(r, g, b)
    is StandardColor.RGBA -> EchartsColor.Rgba(rgb.r, rgb.g, rgb.b, a)
    is LinearGradient -> EchartsColor.LinearGradient(x, y, x2, y2, colorStops)
    is RadialGradient -> EchartsColor.RadialGradient(x, y, r, colorStops)
    else -> throw IllegalArgumentException("Unsupported color type: ${this::class.simpleName}")
}

@Serializable(with = ColorSerializer::class)
internal sealed interface EchartsColor {
    @Serializable
    @JvmInline
    value class Hex(val hex: String) : EchartsColor

    @Serializable
    @JvmInline
    value class Named(val name: String) : EchartsColor

    @Serializable(with = RgbColorSerializer::class)
    data class Rgb(val r: Int, val g: Int, val b: Int) : EchartsColor {
        init {
            require(r in 0..255 && g in 0..255 && b in 0..255) {
                "Invalid RGB values: ($r, $g, $b). Each value should be between 0 and 255 (inclusive)."
            }
        }

        fun toHex(): String = buildString {
            append('#')
            append(r.toString(16).padStart(2, '0'))
            append(g.toString(16).padStart(2, '0'))
            append(b.toString(16).padStart(2, '0'))
        }

        override fun toString(): String = "rgb($r, $g, $b)"
    }

    @Serializable(with = RgbaColorSerializer::class)
    data class Rgba(val r: Int, val g: Int, val b: Int, val a: Double) : EchartsColor {
        init {
            require(r in 0..255 && g in 0..255 && b in 0..255) {
                "Invalid RGB values: ($r, $g, $b). Each value should be between 0 and 255 (inclusive)."
            }
            require(a in 0.0..1.0) {
                "Invalid alpha value: $a. Alpha value should be between 0.0 and 1.0 (inclusive)."
            }
        }

        fun toHex(): String = buildString {
            append('#')
            append(r.toString(16).padStart(2, '0'))
            append(g.toString(16).padStart(2, '0'))
            append(b.toString(16).padStart(2, '0'))
            val alpha = (a * 255).toInt()
            append(alpha.toString(16).padStart(2, '0'))
        }

        override fun toString(): String = "rgba($r, $g, $b, $a)"
    }

    @Serializable
    sealed interface Gradient : EchartsColor {
        val x: Double?
        val y: Double?
        val colorStops: List<ColorStop>?
    }

    @Serializable
    @SerialName("linear")
    data class LinearGradient(
        override val x: Double?, override val y: Double?,
        val x2: Double?, val y2: Double?, override val colorStops: List<ColorStop>?,
    ) : Gradient

    @Serializable
    @SerialName("radial")
    data class RadialGradient(
        override val x: Double?, override val y: Double?,
        val r: Double?, override val colorStops: List<ColorStop>?,
    ) : Gradient
}

@Serializable
internal data class ColorStop(val offset: Double, val color: EchartsColor)
