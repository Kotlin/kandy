/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val AREA: LetsPlotGeom = LetsPlotGeom("area")

public interface AreaContextInterface: WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

@PlotDslMarker
public class AreaContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), AreaContextInterface

@PlotDslMarker
public class AreaContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), AreaContextInterface

public inline fun LayerCollectorContextImmutable.area(block: AreaContextImmutable.() -> Unit) {
    addLayer(AreaContextImmutable(this).apply(block), AREA)
}

public inline fun LayerCollectorContextMutable.area(block: AreaContextMutable.() -> Unit) {
    addLayer(AreaContextMutable(this).apply(block), AREA)
}
