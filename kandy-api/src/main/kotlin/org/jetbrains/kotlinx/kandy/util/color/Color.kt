/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.util.color

/**
 * Color base interface.
 */
public sealed interface Color: Comparable<Color> {

    public companion object {
        /**
         * Creates an RGB color with the specified red, green,
         * and blue components values in the range (0 - 255).
         * @param r the red component.
         * @param g the green component.
         * @param b the blue component.
         */
        public fun rgb(r: Int, g: Int, b: Int): StandardColor.RGB = StandardColor.RGB(r, g, b)

        /**
         * Creates an RGBA color with the specified red, green, blue
         * components values in the range (0 - 255) and alpha value in the range (0.0 - 1.0).
         * @param r the red component.
         * @param g the green component.
         * @param b the blue component.
         * @param a the alpha value.
         */
        public fun rgba(r: Int, g: Int, b: Int, a: Double): StandardColor.RGBA = StandardColor.RGBA(StandardColor.RGB(r, g, b), a)

        /**
         * Creates a color specified by given name (its interpretation depends on engine).
         *
         * @param name name of color.
         */
        public fun named(name: String): StandardColor.Named = StandardColor.Named(name)

        /**
         * Creates an RGB or RGBA color from the given hexadecimal string.
         * (possible formats: "#rrggbb" and "#rrggbbaa").
         * @param hexString [String] encoding the color in hexadecimal format.
         */
        public fun hex(hexString: String): StandardColor.Hex = StandardColor.Hex(hexString)

        /**
         * Creates an RGB or RGBA color from the given hex-value [Int].
         * @param hexInt [Int] encoding the color in hexadecimal format.
         */
        public fun hex(hexInt: Int): StandardColor.Hex = StandardColor.Hex('#' + hexInt.toString(16))

        public val RED: StandardColor.Hex = StandardColor.Hex("#ee6666")
        public val BLUE: StandardColor.Hex = StandardColor.Hex("#5470c6")
        public val GREEN: StandardColor.Hex = StandardColor.Hex("#3ba272")
        public val YELLOW: StandardColor.Hex = StandardColor.Hex("#fac858")
        public val ORANGE: StandardColor.Hex = StandardColor.Hex("#fc8452")
        public val PURPLE: StandardColor.Hex = StandardColor.Hex("#9a60b4")

        public val LIGHT_BLUE: StandardColor.Hex = StandardColor.Hex("#73c0de")
        public val LIGHT_GREEN: StandardColor.Hex = StandardColor.Hex("#91cc75")
        public val LIGHT_PURPLE: StandardColor.Hex = StandardColor.Hex("#ea7ccc")

        public val BLACK: StandardColor.Hex = StandardColor.Hex("#000000")
        public val WHITE: StandardColor.Hex = StandardColor.Hex("#ffffff")
        public val GREY: StandardColor.Hex = StandardColor.Hex("#a39999")

        public val PEACH: StandardColor.Hex = StandardColor.Hex("#ffe5b4")
    }
}