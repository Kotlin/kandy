/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.util.color

/**
 * Represents a standardized color interface, providing a unified way to define and compare colors across various formats.
 * This interface not only abstracts the essence of a color but also provides utility methods to create colors in RGB,
 * RGBA, named, and hexadecimal formats.
 *
 * In addition to the utility methods, the [Color] companion object provides a set of predefined standard colors.
 * These can be utilized for consistent and easily recognizable color references.
 *
 * Implementing classes or interfaces should provide concrete representations and manipulations specific to their color model.
 *
 * @see StandardColor for common implementations like RGB, RGBA, named, and hex formats.
 */
public sealed interface Color : Comparable<Color> {

    public companion object {
        /**
         * Creates an RGB color based on individual red, green, and blue components.
         *
         * @param r red component, in the range (0-255).
         * @param g green component, in the range (0-255).
         * @param b blue component, in the range (0-255).
         * @return an RGB representation of the color.
         */
        public fun rgb(r: Int, g: Int, b: Int): StandardColor.RGB = StandardColor.RGB(r, g, b)

        /**
         * Creates an RGBA color based on individual red, green, blue components and alpha value.
         *
         * @param r red component, in the range (0-255).
         * @param g green component, in the range (0-255).
         * @param b blue component, in the range (0-255).
         * @param a alpha value, in the range (0.0-1.0).
         * @return an RGBA representation of the color.
         */
        public fun rgba(r: Int, g: Int, b: Int, a: Double): StandardColor.RGBA =
            StandardColor.RGBA(StandardColor.RGB(r, g, b), a)

        /**
         * Creates a color based on a given name.
         * The actual interpretation and rendering are dependent on the underlying graphics engine.
         *
         * @param name the name of the color.
         * @return a named representation of the color.
         */
        public fun named(name: String): StandardColor.Named = StandardColor.Named(name)

        /**
         * Creates a color based on a hexadecimal string representation.
         *
         * @param hexString ф string encoding the color.
         * It can be in the format "#RRGGBB" (e.g., "#FF0000" for red) or "#RRGGBBAA"
         * where "RR", "GG", "BB", and "AA" are hexadecimal values for the red,
         * green, blue, and alpha components, respectively.
         * @return ф hexadecimal representation of the color.
         */
        public fun hex(hexString: String): StandardColor.Hex = StandardColor.Hex(hexString)

        /**
         * Creates a color based on a hexadecimal integer representation.
         *
         * @param hexInt An integer encoding the color in hexadecimal.
         * @return A hexadecimal representation of the color.
         */
        public fun hex(hexInt: Int): StandardColor.Hex = StandardColor.Hex('#' + hexInt.toString(16))

        // Predefined standard colors for consistent references.
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