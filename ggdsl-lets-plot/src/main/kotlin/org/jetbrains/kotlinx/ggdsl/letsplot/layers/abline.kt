package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.letsplot.*

val AB_LINE = LetsPlotGeom("abline")

val SLOPE = AesName("slope")
data class SlopeAes(override val context: BindingContext) :NonScalablePositionalAes{
    override val name: AesName = SLOPE
}
val INTERCEPT = AesName("intercept")
data class InterceptAes(override val context: BindingContext) :NonScalablePositionalAes {
    override val name: AesName = INTERCEPT
}


class ABLineContext @PublishedApi internal constructor(override var data: MutableNamedData) :
    LayerContext() {
    val slope = SlopeAes(this)
    val intercept = InterceptAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)
    val type = LineTypeAes(this)
    val width = WidthAes(this)
}

inline fun PlotContext.abLine(block: ABLineContext.() -> Unit) {
    layers.add(ABLineContext(data).apply { copyFrom(this@abLine) }.apply(block).toLayer(AB_LINE))
}


