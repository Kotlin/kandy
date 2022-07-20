package org.jetbrains.kotlinx.ggdsl.old

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData

class PointsContext(override var data: MutableNamedData) : LayerContext() {
    val size = SizeAes(this)
    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val borderWidth = BorderWidthAes(this)
    val borderColor = BorderColorAes(this)

    val symbol = SymbolAes(this)
}

class LineContext(override var data: MutableNamedData) :
    LayerContext() {
    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val width = WidthAes(this)

    val lineType = LineTypeAes(this)
}

class BarsContext(override var data: MutableNamedData) :
    LayerContext() {
    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val width = WidthAes(this)

    val borderWidth = BorderWidthAes(this)
    val borderColor = BorderColorAes(this)
}