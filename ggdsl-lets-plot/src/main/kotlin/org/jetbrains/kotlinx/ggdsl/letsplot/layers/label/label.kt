/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers.label

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContext
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
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)
    public val label: LabelAes get() = LabelAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val angle: AngleAes get() = AngleAes(this)
    public val format: FormatAes get() = FormatAes(this)
    public val horizontalJustification: HorizontalJustificationAes get() = HorizontalJustificationAes(this)
    public val verticalJustification: VerticalJustificationAes get() = VerticalJustificationAes(this)

    public val font: FontContext get() = FontContext(this)
}

public inline fun LayerCollectorContext.text(block: TextContext.() -> Unit) {
    addLayer(TextContext(this).apply(block), TEXT)
}