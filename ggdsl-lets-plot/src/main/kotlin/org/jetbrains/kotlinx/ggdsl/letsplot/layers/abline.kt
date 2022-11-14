/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val AB_LINE: LetsPlotGeom = LetsPlotGeom("abline")

public interface ABLineContextInterface: BindingContext {
    public val slope: SlopeAes get() = SlopeAes(this)
    public val intercept: InterceptAes get() = InterceptAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: WidthAes get() = WidthAes(this)
}

@PlotDslMarker
public class ABLineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), ABLineContextInterface

@PlotDslMarker
public class ABLineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), ABLineContextInterface

public inline fun LayerCollectorContextImmutable.abLine(block: ABLineContextImmutable.() -> Unit) {
    addLayer(ABLineContextImmutable(this).apply(block), AB_LINE)
}

public inline fun LayerCollectorContextMutable.abLine(block: ABLineContextMutable.() -> Unit) {
    addLayer(ABLineContextMutable(this).apply(block), AB_LINE)
}
