/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.settings.LinearGradient
import org.jetbrains.kotlinx.kandy.echarts.settings.RadialGradient
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.ColorSerializer
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.color.StandardColor

internal fun Color.toEchartsColor(): EchartsColor = when (this) {
    is StandardColor -> BaseColor(this.description)
    is LinearGradient -> LinearGradientColor(x, y, x2, y2, colorStops)
    is RadialGradient -> RadialGradientColor(x, y, r, colorStops)
    else -> BaseColor(this.toString()) // TODO?
}

@Serializable(with = ColorSerializer::class)
internal interface EchartsColor

internal class BaseColor internal constructor(val hex: String) : EchartsColor

@Serializable
internal sealed interface GradientColor : EchartsColor {
    val x: Double?
    val y: Double?
    val colorStops: List<ColorStop>?
}

@Serializable
@SerialName("linear")
internal class LinearGradientColor(
    override val x: Double?,
    override val y: Double?,
    val x2: Double?,
    val y2: Double?,
    override val colorStops: List<ColorStop>?,
) : GradientColor

@Serializable
@SerialName("radial")
internal class RadialGradientColor(
    override val x: Double?,
    override val y: Double?,
    val r: Double?,
    override val colorStops: List<ColorStop>?,
) : GradientColor

@Serializable
internal data class ColorStop(val offset: Double, val color: EchartsColor)
