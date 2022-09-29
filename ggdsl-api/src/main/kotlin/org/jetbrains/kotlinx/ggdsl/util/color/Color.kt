/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.util.color

/**
 * Color base interface.
 */
// TODO(return type: Color or StandardColor?)
public interface Color {
    public companion object {
        /**
         * Creates a new [StandardColor] from hex value of color.
         *
         * @param hexString the color in hexadecimal format
         */
        public fun fromHex(hexString: String): StandardColor = StandardColor(hexString)

        /**
         * Creates a new [StandardColor] from color name.
         *
         * @param name the name of the color //todo enginges???
         */
        public fun fromName(name: String): StandardColor = StandardColor(name)

        /**
         * Creates a new [StandardColor] from rgb value of color.
         *
         * @param r the red component of the color
         * @param g the green component of the color
         * @param b the blue component of the color
         */
        public fun fromRGB(r: Int, g: Int, b: Int): StandardColor = StandardColor(
            String.format("#%02X%02X%02X", r, g, b)
        )

        // todo move to hex???

        public val RED: StandardColor = StandardColor("red")
        public val BLUE: StandardColor = StandardColor("blue")
        public val GREEN: StandardColor = StandardColor("green")

        public val BLACK: StandardColor = StandardColor("black")
        public val WHITE: StandardColor = StandardColor("white")
        public val GREY: StandardColor = StandardColor("grey")

        public val YELLOW: StandardColor = StandardColor("yellow")
        public val ORANGE: StandardColor = StandardColor("orange")
        /*
        //val PURPLE = StandardColor("purple")

        // todo not work in echarts
        val DARK_RED = StandardColor("dark_red")
        val DARK_BLUE = StandardColor("dark_blue")
        val DARK_GREEN = StandardColor("dark_green")

         */

        public val PEACH: StandardColor = StandardColor("#ffe5b4")
    }
}

//TODO
/**
 * Color described by one string.
 *
 * @param description the string describing this color.
 */
public data class StandardColor internal constructor(val description: String) : Color
