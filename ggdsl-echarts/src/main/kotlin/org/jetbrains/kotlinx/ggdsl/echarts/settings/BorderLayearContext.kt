package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderColorAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderRadiusAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderTypeAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderWidthAes
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.layers.PointContextImmutable

public fun BarContextImmutable.border(block: BorderLayerContext.() -> Unit) {
    BorderLayerContext(this).apply(block)
}

public fun PointContextImmutable.border(block: BorderLayerContext.() -> Unit) {
    BorderLayerContext(this).apply(block)
}

public class BorderLayerContext(context: BindingContext) {
    public val color: BorderColorAes = BorderColorAes(context)
    public val width: BorderWidthAes = BorderWidthAes(context)
    public val type: BorderTypeAes = BorderTypeAes(context)
    public val radius: BorderRadiusAes = BorderRadiusAes(context)
}
