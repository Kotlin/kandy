/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*

/**
 * Point settings.
 *
 * @property x mapping data on the x-axis.
 * @property y mapping data on the y-axis.
 * @property color points [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * @property symbol [symbol][org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol] of points. `circle` by default.
 * @property size [symbol][org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol] size. `10` by default.
 * @property alpha opacity of points.
 *
 * @see points
 * @see org.jetbrains.kotlinx.ggdsl.util.color.Color
 * @see org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol
 */
@PlotDslMarker
public class PointContextImmutable(parent: LayerCollectorContextImmutable) : EchartsLayerContextImmutable(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
    public val color: ColorAes = ColorAes(this)
    public val symbol: SymbolAes = SymbolAes(this)
    public val size: SizeAes = SizeAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
}