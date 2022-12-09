package org.jetbrains.kotlinx.ggdsl.util.color

import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
public sealed interface StandardColor: Color {
    // basic string description
    public val description: String

    // colors than can be interpreted in `Hex` format
    public sealed interface AsHexColor: StandardColor {
        public val hex: Hex
        public val hexString: String
            get() = hex.hexString

        override val description: String
            get() = hexString
    }

    @Serializable
    public data class RGB internal constructor(val r: Int, val g: Int, val b: Int) : AsHexColor {
        public fun toRGBA(a: Double = 1.0): RGBA = RGBA(this.copy(), a)
        public override val hex: Hex = Hex(
            String.format("#%02X%02X%02X", r, g, b)
        )
    }

    @Serializable
    public data class RGBA internal constructor(val rgb: RGB, val a: Double) : AsHexColor {
        public fun toRGB(): RGB = rgb.copy()
        public override val hex: Hex = Hex(
            buildString {
                append(rgb.hexString)
                append(((a * 255).roundToInt() or (1 shr 8)).toString(16))
            }
        )
    }

    @Serializable
    public data class Named internal constructor(val name: String) : StandardColor {
        override val description: String
            get() = name
    }

    @Serializable
    //todo toRgb/toRgba
    public data class Hex internal constructor(override val hexString: String) : AsHexColor {
        override val hex: Hex = this
    }
}
