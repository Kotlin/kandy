/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*

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