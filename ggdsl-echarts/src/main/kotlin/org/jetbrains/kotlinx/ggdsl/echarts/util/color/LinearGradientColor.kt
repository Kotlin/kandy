/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.util.color

import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.color.StandardColor

// todo offset
/**
 * Linear gradient color.
 *
 * @see <a href = "https://echarts.apache.org/en/option.html#color">ECharts Dcoumentation</a>
 */
public data class LinearGradientColor(
    val x1: Double = 0.0,
    val y1: Double = 0.0,
    val x2: Double = 0.0,
    val y2: Double = 1.0,
    val colors: List<Pair<Double, StandardColor>> = listOf(0.0 to Color.RED, 1.0 to Color.BLUE),
) : EchartsColor

//internal fun SingleColor(color: StandardColor) = SimpleColor(color.description)
internal fun StandardColor.toEchartsColorOption() = SimpleColorOption(description)

internal fun LinearGradientColor.toEchartsColorOption() = GradientOption(
    "linear", x1, y1, x2, y2, null, colors.map { it.toColorStop() }
)

internal fun Pair<Double, StandardColor>.toColorStop() =
    ColorStop(first, second.description)

/**
 * Radial gradient color.
 *
 * @see <a href = "https://echarts.apache.org/en/option.html#color">ECharts Dcoumentation</a>
 */
public class RadialGradientColor(
    public val x: Double = 0.5,
    public val y: Double = 0.5,
    public val r: Double = 0.5,
    public val colors: List<Pair<Double, StandardColor>> = listOf(0.0 to Color.RED, 1.0 to Color.BLUE),
) : EchartsColor

internal fun RadialGradientColor.toEchartsColorOption() = GradientOption(
    "radial", x, y, null, null, r, colors.map { it.toColorStop() }
)

