package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom

@PublishedApi
internal val PATH: LetsPlotGeom = LetsPlotGeom("path")

public inline fun LayerCollectorContextImmutable.path(block: LineContextImmutable.() -> Unit) {
    addLayer(LineContextImmutable(this).apply(block), PATH)
}

public inline fun LayerCollectorContextMutable.path(block: LineContextMutable.() -> Unit) {
    addLayer(LineContextMutable(this).apply(block), PATH)
}
