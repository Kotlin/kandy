/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val TILE: LetsPlotGeom = LetsPlotGeom("tile")

public interface TileContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)

    public val width: WidthPosAes get() = WidthPosAes(this)
    public val height: HeightPosAes get() = HeightPosAes(this)
}

@PlotDslMarker
public class TileContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), TileContextInterface

@PlotDslMarker
public class TileContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), TileContextInterface

public inline fun LayerCollectorContextImmutable.tile(block: TileContextImmutable.() -> Unit) {
    addLayer(TileContextImmutable(this).apply(block), TILE)
}

public inline fun LayerCollectorContextMutable.tile(block: TileContextMutable.() -> Unit) {
    addLayer(TileContextMutable(this).apply(block), TILE)
}
