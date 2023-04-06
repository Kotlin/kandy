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
//@Serializable
public sealed interface StandardColor: Color {
    public val description: String

    /**
     * Interface for Color that can be interpreted in [Hex] format.
     *
     * @property hex [Hex] representation of this color.
     * @property hexString hex-[String] representation of this color.
     */
    public sealed interface AsHexColor: StandardColor {
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
    //@Serializable
    public data class RGB internal constructor(val r: Int, val g: Int, val b: Int) : AsHexColor {
        public fun toRGBA(a: Double = 1.0): RGBA = RGBA(this.copy(), a)
        public override val hex: Hex = Hex(
            String.format("#%02X%02X%02X", r, g, b)
        )

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
    //@Serializable
    public data class RGBA internal constructor(val rgb: RGB, val a: Double) : AsHexColor {
        public fun toRGB(): RGB = rgb.copy()
        public override val hex: Hex = Hex(
            buildString {
                append(rgb.hexString)
                append(((a * 255).roundToInt() or (1 shr 8)).toString(16))
            }
        )

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
    //@Serializable
    public data class Hex internal constructor(override val hexString: String) : AsHexColor {
        override val hex: Hex = this
        override fun compareTo(other: Color): Int {
            //TODO("Not yet implemented")
            return 1
        }
        //todo toRgb/toRgba
    }
}