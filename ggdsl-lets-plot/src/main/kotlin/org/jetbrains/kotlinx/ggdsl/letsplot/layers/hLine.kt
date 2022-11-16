/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val H_LINE: LetsPlotGeom = LetsPlotGeom("hLine")

public interface HLineContextInterface: BindingContext {
    public val y: YInterceptAes get() = YInterceptAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

@PlotDslMarker
public class HLineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), HLineContextInterface

@PlotDslMarker
public class HLineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), HLineContextInterface

public inline fun LayerCollectorContextImmutable.hLine(block: HLineContextImmutable.() -> Unit) {
    addLayer(HLineContextImmutable(this).apply(block), H_LINE)
}

public inline fun LayerCollectorContextMutable.hLine(block: HLineContextMutable.() -> Unit) {
    addLayer(HLineContextMutable(this).apply(block), H_LINE)
}
