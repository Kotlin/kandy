/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.aes.AlphaAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.ColorAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.XAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.YAes

/**
 * Bar settings.
 *
 * @property x mapping data on the x-axis.
 * @property y mapping data on the y-axis.
 * @property color bars [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * @property alpha bars opacity.
 *
 * @see bars
 * @see org.jetbrains.kotlinx.ggdsl.util.color.Color
 */
@PlotDslMarker
public class BarContextImmutable(parent: LayerCollectorContextImmutable) : EchartsLayerContextImmutable(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
}