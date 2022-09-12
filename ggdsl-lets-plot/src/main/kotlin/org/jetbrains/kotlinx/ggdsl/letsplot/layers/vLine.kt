/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val V_LINE = LetsPlotGeom("vLine")


@PlotDslMarker
class VLineContext(override var data: MutableNamedData) : LayerContext() {
    val x = XInterceptAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val type = LineTypeAes(this)
    val width = SizeAes(this)
}


inline fun PlotContext.vLine(block: VLineContext.() -> Unit) {
    layers.add(VLineContext(data).apply { copyFrom(this@vLine) }.apply(block).toLayer(V_LINE))
}
