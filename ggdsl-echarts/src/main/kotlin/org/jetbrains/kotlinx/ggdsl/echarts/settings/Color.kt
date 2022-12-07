package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.color.CustomColor

private fun List<Color>.toColorStops(): List<ColorStop> {
    require(size > 1) { "size must be greater than 1" }
    return List(size) {
        ColorStop(it * (1.0 / (size - 1.0)), this[it].toEchartsColor())
    }
}

public class LinearGradient private constructor(
    internal val x: Double? = null,
    internal val y: Double? = null,
    internal val x2: Double? = null,
    internal val y2: Double? = null,
    internal val colorStops: List<ColorStop>
) : CustomColor {

    public constructor(limits: Pair<Color, Color>) : this(
        colorStops = listOf(
            ColorStop(.0, limits.first.toEchartsColor()),
            ColorStop(1.0, limits.second.toEchartsColor())
        )
    )


    public constructor(colors: List<Color>) : this(colorStops = colors.toColorStops())

    public constructor(x: Double, y: Double, x2: Double, y2: Double, colors: List<Color>) : this(
        x, y, x2, y2, colors.toColorStops()
    )


}

public class RadialGradient private constructor(
    internal val x: Double? = null,
    internal val y: Double? = null,
    internal val r: Double? = null,
    internal val colorStops: List<ColorStop>
) : CustomColor {

    public constructor(limits: Pair<Color, Color>) : this(
        colorStops = listOf(
            ColorStop(.0, limits.first.toEchartsColor()),
            ColorStop(1.0, limits.second.toEchartsColor())
        )
    )


    public constructor(colors: List<Color>) : this(colorStops = colors.toColorStops())

    public constructor(x: Double, y: Double, r: Double, colors: List<Color>) : this(
        x, y, r, colors.toColorStops()
    )
}
