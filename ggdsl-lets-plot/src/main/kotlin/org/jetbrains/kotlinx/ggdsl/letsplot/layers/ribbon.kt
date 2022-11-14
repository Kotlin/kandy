/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*


@PublishedApi
internal val RIBBON: LetsPlotGeom = LetsPlotGeom("ribbon")

public interface RibbonContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YDummyAes get() = YDummyAes(this)

    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

@PlotDslMarker
public class RibbonContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), RibbonContextInterface

@PlotDslMarker
public class RibbonContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), RibbonContextInterface

public inline fun LayerCollectorContextImmutable.ribbon(block: RibbonContextImmutable.() -> Unit) {
    addLayer(RibbonContextImmutable(this).apply(block), RIBBON)
}

public inline fun LayerCollectorContextMutable.ribbon(block: RibbonContextMutable.() -> Unit) {
    addLayer(RibbonContextMutable(this).apply(block), RIBBON)
}
