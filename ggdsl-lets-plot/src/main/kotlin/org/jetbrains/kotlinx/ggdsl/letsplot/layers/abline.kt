/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.letsplot.*

public val AB_LINE: LetsPlotGeom = LetsPlotGeom("abline")


public class ABLineContext @PublishedApi internal constructor(parent: LayerCollectorContext) :
    LayerContext(parent) {
    public val slope: SlopeAes = SlopeAes(this)
    public val intercept: InterceptAes = InterceptAes(this)

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
    public val type: LineTypeAes = LineTypeAes(this)
    public val width: WidthAes = WidthAes(this)
}

public inline fun LayerCollectorContext.abLine(block: ABLineContext.() -> Unit) {
    addLayer(ABLineContext(this).apply(block), AB_LINE)
}


