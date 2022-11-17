/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val V_LINE: LetsPlotGeom = LetsPlotGeom("vLine")

public interface VLineContextInterface: BindingContext {
    public val x: XInterceptAes get() = XInterceptAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

@PlotDslMarker
public class VLineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), VLineContextInterface

@PlotDslMarker
public class VLineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), VLineContextInterface

public inline fun LayerCollectorContextImmutable.vLine(block: VLineContextImmutable.() -> Unit) {
    addLayer(VLineContextImmutable(this).apply(block), V_LINE)
}

public inline fun LayerCollectorContextMutable.vLine(block: VLineContextMutable.() -> Unit) {
    addLayer(VLineContextMutable(this).apply(block), V_LINE)
}

