package org.jetbrains.kotlinx.ggdsl.letsplot.layers.label

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PlotDslMarker
class FontContext(
    parentContext: LayerContext,
) {
    val color = ColorAes(parentContext)
    val size = SizeAes(parentContext)
    val family = FontFamilyAes(parentContext)
    val face = FontFaceAes(parentContext)
}

val TEXT = LetsPlotGeom("text")

@PlotDslMarker
class TextContext(parent: LayerCollectorContext) : LayerContext(parent) {
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

inline fun LayerCollectorContext.text(block: TextContext.() -> Unit) {
    addLayer(TextContext(this).apply(block), TEXT)
}