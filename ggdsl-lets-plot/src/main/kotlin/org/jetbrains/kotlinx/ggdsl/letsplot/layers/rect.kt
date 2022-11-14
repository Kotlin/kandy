/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val RECT: LetsPlotGeom = LetsPlotGeom("rect")

public interface RectContextInterface : WithBorderLineContextInterface {
    public val y: YDummyAes get() = YDummyAes(this)

    public val xMin: XMinAes get() = XMinAes(this)
    public val xMax: XMaxAes get() = XMaxAes(this)
    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

@PlotDslMarker
public class RectContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), RectContextInterface

@PlotDslMarker
public class RectContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), RectContextInterface

public inline fun LayerCollectorContextImmutable.rect(block: RectContextImmutable.() -> Unit) {
    addLayer(RectContextImmutable(this).apply(block), RECT)
}

public inline fun LayerCollectorContextMutable.rect(block: RectContextMutable.() -> Unit) {
    addLayer(RectContextMutable(this).apply(block), RECT)
}
