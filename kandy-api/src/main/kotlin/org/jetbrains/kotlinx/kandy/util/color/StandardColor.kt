/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.util.color

import kotlin.math.roundToInt

/**
 * Standard color implementations interface.
 *
 * @property description base single-[String] description.
 */
public sealed interface StandardColor : Color {
    public val description: String

    /**
     * Interface for Color that can be interpreted in [Hex] format.
     *
     * @property hex [Hex] representation of this color.
     * @property hexString hex-[String] representation of this color.
     */
    public sealed interface AsHexColor : StandardColor {
        public val hex: Hex
        public val hexString: String
            get() = hex.hexString

        override val description: String
            get() = hexString
    }

    /**
     * RGB color.
     *
     * @property r the red component.
     * @property g the green component.
     * @property b the blue component.
     * @property hex [Hex] representation of this color.
     * @property hexString hex-[String] representation of this color.
     * @property toRGBA transforms to [RGBA]
     */
    public data class RGB internal constructor(val r: Int, val g: Int, val b: Int) : AsHexColor {
        public fun toRGBA(a: Double = 1.0): RGBA = RGBA(this.copy(), a)
        public override val hex: Hex by lazy {
            Hex(String.format("#%02X%02X%02X", r, g, b))
        }

        init {
            require(r in 0..255 && g in 0..255 && b in 0..255) {
                "RGB components must be in the range of 0 to 255. Received: r=$r, g=$g, b=$b"
            }
        }

        override fun compareTo(other: Color): Int {
            //TODO("Not yet implemented")
            return 1
        }
    }

    /**
     * RGBA color.
     *
     * @property rgb the [RGB] component.
     * @property a the alpha component
     * @property hex [Hex] representation of this color.
     * @property hexString hex-[String] representation of this color.
     * @property toRGB transforms to [RGB]
     */
    public data class RGBA internal constructor(val rgb: RGB, val a: Double) : AsHexColor {
        public fun toRGB(): RGB = rgb.copy()
        public override val hex: Hex by lazy {
            Hex(
                buildString {
                    append(rgb.hexString)
                    append(((a * 255).roundToInt() or (1 shr 8)).toString(16))
                }
            )
        }

        init {
            require(a in 0.0..1.0) { "Alpha component must be in the range of 0.0 to 1.0. Received: a=$a" }
        }

        override fun compareTo(other: Color): Int {
            //TODO("Not yet implemented")
            return 1
        }
    }

    /**
     * Color defined by name.
     *
     * @property name the name of this color.
     */
    //@Serializable
    public data class Named internal constructor(val name: String) : StandardColor {
        override val description: String
            get() = name

        init {
            require(name.isNotEmpty()) {
                "Name property in `StandardColor.Named` must not be empty. Please provide a valid color name."
            }
        }

        override fun compareTo(other: Color): Int {
            //TODO("Not yet implemented")
            return 1
        }
    }

    /**
     * Color in a hexadecimal format.
     *
     * @property hexString hex-[String] representation of this color.
     */
    public data class Hex internal constructor(override val hexString: String) : AsHexColor {
        override val hex: Hex = this

        init {
            require(hexString.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{8})$".toRegex())) {
                "Invalid hex string format: '$hexString'. A valid hex string must start with '#' followed by either 6 or 8 hexadecimal characters."
            }
        }

        override fun compareTo(other: Color): Int {
            //TODO("Not yet implemented")
            return 1
        }
        //todo toRgb/toRgba
    }
}
