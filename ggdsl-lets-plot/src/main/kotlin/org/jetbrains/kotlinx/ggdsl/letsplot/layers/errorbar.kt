/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*


@PublishedApi
internal val ERROR_BAR: LetsPlotGeom = LetsPlotGeom("errorbar")

public interface ErrorBarContextInterface: BindingContext {
    public val x: XAes get() = XAes(this)

    //todo
    public val y: YDummyAes get() = YDummyAes(this)
    
    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val width: WidthAes get() = WidthAes(this)
}

@PlotDslMarker
public class ErrorBarContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), ErrorBarContextInterface

@PlotDslMarker
public class ErrorBarContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), ErrorBarContextInterface

public inline fun LayerCollectorContextImmutable.errorBar(block: ErrorBarContextImmutable.() -> Unit) {
    addLayer(ErrorBarContextImmutable(this).apply(block), ERROR_BAR)
}

public inline fun LayerCollectorContextMutable.errorBar(block: ErrorBarContextMutable.() -> Unit) {
    addLayer(ErrorBarContextMutable(this).apply(block), ERROR_BAR)
}
