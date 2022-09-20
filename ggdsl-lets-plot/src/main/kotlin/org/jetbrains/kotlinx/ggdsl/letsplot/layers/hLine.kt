/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.*

// TODO

@PublishedApi
internal val H_LINE: LetsPlotGeom = LetsPlotGeom("hLine")


@PlotDslMarker
public class HLineContext(override var data: MutableNamedData) : LayerContext() {
    public val y: YInterceptAes = YInterceptAes(this)

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
    public val type: LineTypeAes = LineTypeAes(this)
    public val width: SizeAes = SizeAes(this)
}


public inline fun PlotContext.hLine(block: HLineContext.() -> Unit) {
    layers.add(HLineContext(data).apply { copyFrom(this@hLine) }.apply(block).toLayer(H_LINE))
}
