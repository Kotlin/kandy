/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers.label

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PlotDslMarker
public class FontContext(
    parentContext: LayerContext,
) {
    public val color: ColorAes = ColorAes(parentContext)
    public val size: SizeAes = SizeAes(parentContext)
    public val family: FontFamilyAes = FontFamilyAes(parentContext)
}

public val TEXT: LetsPlotGeom = LetsPlotGeom("text")

@PlotDslMarker
public class TextContext(parent: LayerCollectorContext) : LayerContext(parent) {
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

public inline fun LayerCollectorContext.text(block: TextContext.() -> Unit) {
    addLayer(TextContext(this).apply(block), TEXT)
}