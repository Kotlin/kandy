package org.jetbrains.kotlinx.ggdsl.letsplot.layers.label

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.SubContext

@PlotDslMarker
class FontContext(
    parentContext: LayerContext,
    override var data: MutableNamedData = mutableMapOf()
) : SubContext(parentContext) {
    val color = ColorAes(parentContext)
    val size = SizeAes(parentContext)
    val family = FontFamilyAes(parentContext)
    val face = FontFaceAes(parentContext)
}

val TEXT = LetsPlotGeom("text")

@PlotDslMarker
class TextContext(override var data: MutableNamedData) : LayerContext() {
    // todo sizeUnit
    val x = XAes(this)
    val y = YAes(this)
    val label = LabelAes(this)

    val alpha = AlphaAes(this)
    val angle = AngleAes(this)
    val format = FormatAes(this)
    val horizontalJustification = HorizontalJustificationAes(this)
    val verticalJustification = VerticalJustificationAes(this)

    val font = FontContext(this)
}

inline fun PlotContext.text(block: TextContext.() -> Unit) {
    layers.add(TextContext(data).apply { copyFrom(this@text) }.apply(block).toLayer(TEXT))
}