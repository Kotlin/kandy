/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*

/**
 * Line settings.
 *
 * @property x mapping data on the x-axis.
 * @property y mapping data on the y-axis.
 * @property color line [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * @property symbol [symbol][org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol] on line.
 * Symbols are not shown by default.
 * @property smooth smooth curve. `false` by default.
 * @property alpha line opacity.
 * @property width line width. `2` by default.
 * @property lineType [line type][org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType]. `solid` by default.
 * @property step step line. `false` by default.
 * @property cap [end points][org.jetbrains.kotlinx.ggdsl.echarts.settings.Cap] of line. `butt` by default.
 * @property shadowColor shadow color of line.
 * @property shadowBlur shadow blur size of line.
 *
 * @see line
 * @see org.jetbrains.kotlinx.ggdsl.util.color.Color
 * @see org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol
 * @see org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
 * @see org.jetbrains.kotlinx.ggdsl.echarts.settings.Cap
 */
@PlotDslMarker
public class LineContextImmutable(parent: LayerCollectorContextImmutable) : EchartsLayerContextImmutable(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
    public val color: LineColorAes = LineColorAes(this)
    public val symbol: SymbolAes = SymbolAes(this)
    public val smooth: SmoothAes = SmoothAes(this)
    public val alpha: LineAlphaAes = LineAlphaAes(this)
    public val width: WidthAes = WidthAes(this)
    public val lineType: LineTypeAes = LineTypeAes(this)
    public val step: StepAes = StepAes(this)
    public val cap: CapAes = CapAes(this)
    public val shadowColor: LineShadowColorAes = LineShadowColorAes(this)
    public val shadowBlur: LineShadowBlurAes = LineShadowBlurAes(this)
}