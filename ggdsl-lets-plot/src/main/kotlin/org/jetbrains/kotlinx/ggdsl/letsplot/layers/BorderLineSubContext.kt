package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.ColorAes
import org.jetbrains.kotlinx.ggdsl.letsplot.LineTypeAes
import org.jetbrains.kotlinx.ggdsl.letsplot.SizeAes

@PlotDslMarker
class BorderLineSubContext(parentContext: BindingContext)  {
    val color = ColorAes(parentContext)
    val type = LineTypeAes(parentContext)
    val width = SizeAes(parentContext)
}

abstract class WithBorderLineContext(parent: LayerCollectorContext) : LayerContext(parent){
    val borderLine = BorderLineSubContext(this)

    inline operator fun BorderLineSubContext.invoke(block: BorderLineSubContext.() -> Unit) {
        apply(block)
    }
}
