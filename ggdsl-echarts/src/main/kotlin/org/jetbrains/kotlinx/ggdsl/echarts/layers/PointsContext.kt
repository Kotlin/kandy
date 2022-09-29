package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.data.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.echarts.*

class PointsContext(parent: LayerCollectorContext) : LayerContext(parent) {
    val x = XAes(this)
    val y = YAes(this)

    val size = SizeAes(this)
    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val borderWidth = BorderWidthAes(this)
    val borderColor = BorderColorAes(this)

    val symbol = SymbolAes(this)
}

class LineContext(parent: LayerCollectorContext) :
    LayerContext(parent) {
    val x = XAes(this)
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val width = WidthAes(this)

    val lineType = LineTypeAes(this)
}

class BarsContext(parent: LayerCollectorContext) :
    LayerContext(parent) {
    val x = XAes(this)
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val width = WidthAes(this)

    val borderWidth = BorderWidthAes(this)
    val borderColor = BorderColorAes(this)
}