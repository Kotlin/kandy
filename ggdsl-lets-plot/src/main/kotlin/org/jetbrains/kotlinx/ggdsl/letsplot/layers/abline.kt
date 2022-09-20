/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.letsplot.*

public val AB_LINE: LetsPlotGeom = LetsPlotGeom("abline")

public val SLOPE: AesName = AesName("slope")

public data class SlopeAes(override val context: BindingContext) : NonScalablePositionalAes {
    override val name: AesName = SLOPE
}

public val INTERCEPT: AesName = AesName("intercept")

public data class InterceptAes(override val context: BindingContext) : NonScalablePositionalAes {
    override val name: AesName = INTERCEPT
}


public class ABLineContext @PublishedApi internal constructor(override var data: MutableNamedData) :
    LayerContext() {
    public val slope: SlopeAes = SlopeAes(this)
    public val intercept: InterceptAes = InterceptAes(this)

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)
    public val type: LineTypeAes = LineTypeAes(this)
    public val width: WidthAes = WidthAes(this)
}

public inline fun PlotContext.abLine(block: ABLineContext.() -> Unit) {
    layers.add(ABLineContext(data).apply { copyFrom(this@abLine) }.apply(block).toLayer(AB_LINE))
}


