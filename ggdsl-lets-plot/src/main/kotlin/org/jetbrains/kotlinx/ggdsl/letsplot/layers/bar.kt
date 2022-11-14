/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val BAR: LetsPlotGeom = LetsPlotGeom("bar")

public interface BarContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val width: WidthAes get() = WidthAes(this)
}

@PlotDslMarker
public open class BarContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), BarContextInterface

@PlotDslMarker
public open class BarContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), BarContextInterface

public inline fun LayerCollectorContextImmutable.bar(block: BarContextImmutable.() -> Unit) {
    addLayer(BarContextImmutable(this).apply(block), BAR)
}

public inline fun LayerCollectorContextMutable.bar(block: BarContextMutable.() -> Unit) {
    addLayer(BarContextMutable(this).apply(block), BAR)
}
