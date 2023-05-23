/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

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
    class Rgb(val r: Int, val g: Int, val b: Int) : EchartsColor {
        init {
            require(r in 0..255 && g in 0..255 && b in 0..255) {
                "Invalid RGB values: ($r, $g, $b). Each value should be between 0 and 255 (inclusive)."
            }
        }

        fun toHex(): String {
            val hexR = r.toString(16).padStart(2, '0')
            val hexG = g.toString(16).padStart(2, '0')
            val hexB = b.toString(16).padStart(2, '0')
            return "#$hexR$hexG$hexB"
        }

        override fun toString(): String {
            return "rgb($r, $g, $b)"
        }
    }

    @Serializable(with = RgbaColorSerializer::class)
    class Rgba(val r: Int, val g: Int, val b: Int, val a: Double) : EchartsColor {
        init {
            require(r in 0..255 && g in 0..255 && b in 0..255) {
                "Invalid RGB values: ($r, $g, $b). Each value should be between 0 and 255 (inclusive)."
            }
            require(a in 0.0..1.0) {
                "Invalid alpha value: $a. Alpha value should be between 0.0 and 1.0 (inclusive)."
            }
        }

        fun toHex(): String {
            val hexR = r.toString(16).padStart(2, '0')
            val hexG = g.toString(16).padStart(2, '0')
            val hexB = b.toString(16).padStart(2, '0')
            val alpha = (a * 255).toInt()
            val hexA = alpha.toString(16).padStart(2, '0')
            return "#$hexR$hexG$hexB$hexA"
        }

        override fun toString(): String {
            return "rgba($r, $g, $b, $a)"
        }
    }

    @Serializable
    sealed interface Gradient : EchartsColor {
        val x: Double?
        val y: Double?
        val colorStops: List<ColorStop>?
    }

    @Serializable
    @SerialName("linear")
    class LinearGradient(
        override val x: Double?, override val y: Double?,
        val x2: Double?, val y2: Double?, override val colorStops: List<ColorStop>?,
    ) : Gradient

    @Serializable
    @SerialName("radial")
    class RadialGradient(
        override val x: Double?, override val y: Double?,
        val r: Double?, override val colorStops: List<ColorStop>?,
    ) : Gradient
}

@Serializable
internal data class ColorStop(val offset: Double, val color: EchartsColor)
