/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val RASTER: LetsPlotGeom = LetsPlotGeom("raster")

public interface RasterContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

@PlotDslMarker
public class RasterContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), RasterContextInterface

@PlotDslMarker
public class RasterContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), RasterContextInterface

public inline fun LayerCollectorContextImmutable.raster(block: RasterContextImmutable.() -> Unit) {
    addLayer(RasterContextImmutable(this).apply(block), RASTER)
}

public inline fun LayerCollectorContextMutable.raster(block: RasterContextMutable.() -> Unit) {
    addLayer(RasterContextMutable(this).apply(block), RASTER)
}

