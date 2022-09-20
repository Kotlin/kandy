package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.echarts.*

public class PointsContext(override var data: MutableNamedData) : LayerContext() {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)

    public val size: SizeAes = SizeAes(this)
    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val borderWidth: BorderWidthAes = BorderWidthAes(this)
    public val borderColor: BorderColorAes = BorderColorAes(this)

    public val symbol: SymbolAes = SymbolAes(this)
}

public class LineContext(override var data: MutableNamedData) :
    LayerContext() {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val width: WidthAes = WidthAes(this)

    public val lineType: LineTypeAes = LineTypeAes(this)
}

public class BarsContext(override var data: MutableNamedData) :
    LayerContext() {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val width: WidthAes = WidthAes(this)

    public val borderWidth: BorderWidthAes = BorderWidthAes(this)
    public val borderColor: BorderColorAes = BorderColorAes(this)
}