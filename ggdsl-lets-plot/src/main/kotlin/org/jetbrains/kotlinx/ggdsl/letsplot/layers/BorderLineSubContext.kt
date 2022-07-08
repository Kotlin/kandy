package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.letsplot.COLOR
import org.jetbrains.kotlinx.ggdsl.letsplot.LINE_TYPE
import org.jetbrains.kotlinx.ggdsl.letsplot.SIZE

class BorderLineSubContext : BindingContext() {
    override var data: MutableNamedData = mutableMapOf()
    val color = COLOR
    val type = LINE_TYPE
    val width = SIZE
}