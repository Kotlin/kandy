package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*

public class LineContext(parent: LayerCollectorContext) : EchartsLayerContext(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)

    public val color: ColorAes = ColorAes(this)
    public val symbol: SymbolAes = SymbolAes(this)

    public val smooth: SmoothAes = SmoothAes(this)
//    public val alpha: AlphaAes = AlphaAes(this)

//    public val width: WidthAes = WidthAes(this)

//    public val lineType: LineTypeAes = LineTypeAes(this)
}