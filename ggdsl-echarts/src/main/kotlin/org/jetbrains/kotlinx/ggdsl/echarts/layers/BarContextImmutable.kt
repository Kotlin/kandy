package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.aes.AlphaAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.ColorAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.XAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.YAes

@PlotDslMarker
public class BarContextImmutable(parent: LayerCollectorContextImmutable) : EchartsLayerContextImmutable(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
}