/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.util.color

import kotlin.math.roundToInt

/**
 * Color base interface.
 */
public sealed interface Color {

    public data class RGB internal constructor(val r: Int, val g: Int, val b: Int) : Color {
        public fun toRGBA(a: Double = 1.0): RGBA = RGBA(this.copy(), a)
        public fun toHex(): Hex = Hex(
            buildString {
                append("#")
                append(r.toString(16))
                append(g.toString(16))
                append(b.toString(16))
            }
        )
    }

    public data class RGBA internal constructor(val rgb: RGB, val a: Double) : Color {
        public fun toRGB(): RGB = rgb.copy()
        public fun toHexA(): HexA = HexA(
            buildString {
                append(rgb.toHex())
                append(((a * 255).roundToInt() or (1 shr 8)).toString(16))
            }
        )
    }

    public data class Named internal constructor(val name: String) : Color

    public data class Hex internal constructor(val hex: String) : Color

    public data class HexA internal constructor(val hexA: String) : Color


    public companion object {
        public fun rgb(r: Int, g: Int, b: Int): RGB = RGB(r, g, b)
        public fun rgba(r: Int, g: Int, b: Int, a: Double): RGBA = RGBA(RGB(r, g, b), a)
        public fun named(name: String): Named = Named(name)
        public fun hex(hexString: String): Hex = Hex(hexString)
        public fun hex(hexInt: Int): Hex = Hex('#' + hexInt.toString(16))
        public fun hexA(hexAString: String): HexA = HexA(hexAString)

        public val RED: Hex = hex("#ee6666")
        public val BLUE: Hex = hex("#5470c6")
        public val GREEN: Hex = hex("#3ba272")
        public val YELLOW: Hex = hex("#fac858")
        public val ORANGE: Hex = hex("#fc8452")
        public val PURPLE: Hex = hex("#9a60b4")

        public val LIGHT_BLUE: Hex = hex("#73c0de")
        public val LIGHT_GREEN: Hex = hex("#91cc75")
        public val LIGHT_PURPLE: Hex = hex("#ea7ccc")

        public val BLACK: Hex = hex("#000")
        public val WHITE: Hex = hex("#fff")
        public val GREY: Hex = hex("#a39999")

        public val PEACH: Hex = hex("#ffe5b4")
    }
}
