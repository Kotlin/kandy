package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.letsplot.*

class BorderLineSubContext : BindingContext() {
    override var data: MutableNamedData = mutableMapOf()
    val color = ColorAes(this)
    val type = LineTypeAes(this)
    val width = SizeAes(this)
}