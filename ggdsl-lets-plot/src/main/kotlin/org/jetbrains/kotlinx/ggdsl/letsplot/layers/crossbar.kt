/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*


@PublishedApi
internal val CROSS_BAR: LetsPlotGeom = LetsPlotGeom("crossbar")

public interface CrossBarContextInterface: BindingContext {
    public val x: XAes get() = XAes(this)
    //todo
    public val y: YDummyAes get() = YDummyAes(this)

    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)
    public val middle: MiddleAes get() = MiddleAes(this)

    public val fatten: FattenAes get() = FattenAes(this)

    public val width: WidthAes get() = WidthAes(this)
    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

@PlotDslMarker
public class CrossBarContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), CrossBarContextInterface

@PlotDslMarker
public class CrossBarContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), CrossBarContextInterface {
}

public inline fun LayerCollectorContextImmutable.crossBar(block: CrossBarContextImmutable.() -> Unit) {
    addLayer(CrossBarContextImmutable(this).apply(block), CROSS_BAR)
}

public inline fun LayerCollectorContextMutable.crossBar(block: CrossBarContextMutable.() -> Unit) {
    addLayer(CrossBarContextMutable(this).apply(block), CROSS_BAR)
}
