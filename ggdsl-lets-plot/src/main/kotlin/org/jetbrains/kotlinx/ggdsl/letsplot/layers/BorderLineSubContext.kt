package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.*

abstract class SubContext(val parentContext: BindingContext): BindingContext()

@PlotDslMarker
class BorderLineSubContext(parentContext: BindingContext) : SubContext(parentContext) {
    override var data: MutableNamedData = mutableMapOf()
    val color = ColorAes(parentContext)
    val type = LineTypeAes(parentContext)
    val width = SizeAes(parentContext)
}

abstract class WithBorderLineContext : LayerContext(){
    val borderLine = BorderLineSubContext(this)

    inline operator fun BorderLineSubContext.invoke(block: BorderLineSubContext.() -> Unit) {
        apply(block)
    }
}