/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*

@PlotDslMarker
public class AreaContextImmutable(parent: LayerCollectorContextImmutable) : EchartsLayerContextImmutable(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
    public val color: AreaColorAes = AreaColorAes(this)
    public val position: PositionAes = PositionAes(this)
    public val shadowBlur: AreaShadowBlurAes = AreaShadowBlurAes(this)
    public val shadowColor: AreaShadowColorAes = AreaShadowColorAes(this)
    public val alpha: AreaAlphaAes = AreaAlphaAes(this)

    public val lineColor: LineColorAes = LineColorAes(this)
    public val symbol: SymbolAes = SymbolAes(this)
    public val smooth: SmoothAes = SmoothAes(this)
    public val lineAlpha: LineAlphaAes = LineAlphaAes(this)
    public val lineWidth: WidthAes = WidthAes(this)
    public val lineType: LineTypeAes = LineTypeAes(this)
    public val step: StepAes = StepAes(this)
    public val cap: CapAes = CapAes(this)
    public val lineShadowColor: LineShadowColorAes = LineShadowColorAes(this)
    public val lineShadowBlur: LineShadowBlurAes = LineShadowBlurAes(this)
}