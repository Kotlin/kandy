/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.util.color

import kotlin.math.roundToInt

/**
 * Color base interface.
 */
public sealed interface Color {

    public companion object {
        public fun rgb(r: Int, g: Int, b: Int): RGB = RGB(r, g, b)
        public fun rgba(r: Int, g: Int, b: Int, a: Double): RGBA = RGBA(RGB(r, g, b), a)
        public fun named(name: String): Named = Named(name)
        public fun hex(hexString: String): Hex = Hex(hexString)
        public fun hex(hexInt: Int): Hex = Hex('#' + hexInt.toString(16))

        public val RED: Hex = Hex("#ee6666")
        public val BLUE: Hex = Hex("#5470c6")
        public val GREEN: Hex = Hex("#3ba272")
        public val YELLOW: Hex = Hex("#fac858")
        public val ORANGE: Hex = Hex("#fc8452")
        public val PURPLE: Hex = Hex("#9a60b4")

        public val LIGHT_BLUE: Hex = Hex("#73c0de")
        public val LIGHT_GREEN: Hex = Hex("#91cc75")
        public val LIGHT_PURPLE: Hex = Hex("#ea7ccc")

        public val BLACK: Hex = Hex("#000000")
        public val WHITE: Hex = Hex("#ffffff")
        public val GREY: Hex = Hex("#a39999")

        public val PEACH: Hex = Hex("#ffe5b4")
    }
}
