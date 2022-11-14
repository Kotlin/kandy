/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val LINE: LetsPlotGeom = LetsPlotGeom("line")

public interface LineContextInterface: BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

@PlotDslMarker
public class LineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), LineContextInterface

@PlotDslMarker
public class LineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), LineContextInterface

public inline fun LayerCollectorContextImmutable.line(block: LineContextImmutable.() -> Unit) {
    addLayer(LineContextImmutable(this).apply(block), LINE)
}

public inline fun LayerCollectorContextMutable.line(block: LineContextMutable.() -> Unit) {
    addLayer(LineContextMutable(this).apply(block), LINE)
}
