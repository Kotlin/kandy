package org.jetbrains.kotlinx.ggdsl.letsplot.layers.label

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.SubContext

@PlotDslMarker
public class FontContext(
    parentContext: LayerContext,
    override var data: MutableNamedData = mutableMapOf()
) : SubContext(parentContext) {
    public val color: ColorAes = ColorAes(parentContext)
    public val size: SizeAes = SizeAes(parentContext)
    public val family: FontFamilyAes = FontFamilyAes(parentContext)
    public val face: FontFaceAes = FontFaceAes(parentContext)
}

public val TEXT: LetsPlotGeom = LetsPlotGeom("text")

@PlotDslMarker
public class TextContext(override var data: MutableNamedData) : LayerContext() {
    // todo sizeUnit
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
    public val label: LabelAes = LabelAes(this)

    public val alpha: AlphaAes = AlphaAes(this)
    public val angle: AngleAes = AngleAes(this)
    public val format: FormatAes = FormatAes(this)
    public val horizontalJustification: HorizontalJustificationAes = HorizontalJustificationAes(this)
    public val verticalJustification: VerticalJustificationAes = VerticalJustificationAes(this)

    public val font: FontContext = FontContext(this)
}

public inline fun PlotContext.text(block: TextContext.() -> Unit) {
    layers.add(TextContext(data).apply { copyFrom(this@text) }.apply(block).toLayer(TEXT))
}