package org.jetbrains.kotlinx.ggdsl.echarts.settings

import kotlin.math.round

public class Color private constructor() {
    internal lateinit var hex: String

    public constructor(r: Byte, g: Byte, b: Byte, a: Double) : this() {
        hex = buildString {
            append("#")
            append(r.toString(16))
            append(g.toString(16))
            append(b.toString(16))
            if (a != 1.0) {
                append(
                    (round(a * 255).toInt() or (1 shr 8)).toString(16) // ~ [0..1)
                )
            }
        }
    }

    public constructor(r: Byte, g: Byte, b: Byte) : this(r, g, b, 1.0)

    public constructor(color: String) : this() {
        // TODO("validate")
        if (color.startsWith("#"))
            this.hex = color
        else
            when(color.lowercase()) {
                "red" -> hex = "#ee6666"
                "blue" -> hex = "#5470c6"
                "green" -> hex = "#3ba272"
                "yellow" -> hex = "#fac858"
                "orange" -> hex = "#fc8452"
                "purple" -> hex = "#9a60b4"
                "light blue" -> hex = "#73c0de"
                "light green" -> hex = "#91cc75"
                "light purple" -> hex = "#ea7ccc"
                "black" -> hex = "#000"
                "white" -> hex = "#fff"
                "grey" -> hex = "#a39999"
                else -> TODO()
            }
    }

    public companion object {
        // TODO add color palette to main settings! ['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc']

        public val RED: Color = Color("#ee6666")
        public val BLUE: Color = Color("#5470c6")
        public val GREEN: Color = Color("#3ba272")
        public val YELLOW: Color = Color("#fac858")
        public val ORANGE: Color = Color("#fc8452")
        public val PURPLE: Color = Color("#9a60b4")

        public val LIGHT_BLUE: Color = Color("#73c0de")
        public val LIGHT_GREEN: Color = Color("#91cc75")
        public val LIGHT_PURPLE: Color = Color("#ea7ccc")

        public val BLACK: Color = Color("#000")
        public val WHITE: Color = Color("#fff")
        public val GREY: Color = Color("#a39999")
    }
}