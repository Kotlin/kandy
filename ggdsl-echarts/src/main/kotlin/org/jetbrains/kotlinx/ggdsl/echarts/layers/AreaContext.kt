package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Geom
import org.jetbrains.kotlinx.ggdsl.ir.aes.*

// todo echarts geom
val AREA = Geom("area")
/*
class AreaContext : EchartsLayerContext() {
    val color = COLOR
    val alpha = ALPHA

   // val borderWidth = BORDER_WIDTH
   // val borderColor = BORDER_COLOR
}

fun PlotContext.area(block: AreaContext.() -> Unit) {
    layers.add(AreaContext().apply { copyFrom(this@area) }.apply(block).toLayer(AREA))
}


 */