/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.ColorStop
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.color.CustomColor

private fun List<Color>.toColorStops(): List<ColorStop> {
    require(size > 1) { "size must be greater than 1" }
    return List(size) {
        ColorStop(it * (1.0 / (size - 1.0)), this[it].toEchartsColor())
    }
}

/**
 * Linear gradient color.
 */
public class LinearGradient private constructor(
    internal val x: Double? = null,
    internal val y: Double? = null,
    internal val x2: Double? = null,
    internal val y2: Double? = null,
    internal val colorStops: List<ColorStop>
) : CustomColor {

    /**
     * Returns the linear gradient from the first color of [limits] with offset `0` to the second color of [limits] with offset `1`.
     * coordinate parameters are default.
     */
    public constructor(limits: Pair<Color, Color>) : this(
        colorStops = listOf(
            ColorStop(.0, limits.first.toEchartsColor()),
            ColorStop(1.0, limits.second.toEchartsColor())
        )
    )

    /**
     * Returns the linear gradient based on the colors in the list.
     * Offsets are calculated by the position of the color in the list.
     */
    public constructor(colors: List<Color>) : this(colorStops = colors.toColorStops())

    /**
     * Returns the linear gradient with first four parameters [x], [y], [x2], [y2], ranging from `0 - 1`,
     * corresponding to the percentage in the graphical wraparound box.
     */
    public constructor(x: Double, y: Double, x2: Double, y2: Double, colors: List<Color>) : this(
        x, y, x2, y2, colors.toColorStops()
    )

    override fun compareTo(other: Color): Int { // ???
        TODO("Not yet implemented")
    }


}

/**
 * Radial gradient color.
 */
public class RadialGradient private constructor(
    internal val x: Double? = null,
    internal val y: Double? = null,
    internal val r: Double? = null,
    internal val colorStops: List<ColorStop>
) : CustomColor {

    /**
     * Returns the radial gradient from the first color of [limits] with offset `0` to the second color of [limits] with offset `1`.
     * coordinate parameters are default.
     */
    public constructor(limits: Pair<Color, Color>) : this(
        colorStops = listOf(
            ColorStop(.0, limits.first.toEchartsColor()),
            ColorStop(1.0, limits.second.toEchartsColor())
        )
    )

    /**
     * Returns the radial gradient based on the colors in the list.
     * Offsets are calculated by the position of the color in the list.
     */
    public constructor(colors: List<Color>) : this(colorStops = colors.toColorStops())

    /**
     * Returns the radial gradient, the first three parameters are the center [x], [y] and [r].
     */
    public constructor(x: Double, y: Double, r: Double, colors: List<Color>) : this(
        x, y, r, colors.toColorStops()
    )

    override fun compareTo(other: Color): Int {
        TODO("Not yet implemented")
    }
}
