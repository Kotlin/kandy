package org.jetbrains.kotlinx.ggdsl.echarts

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.Layout

/**
 * Plot layout settings
 *
 * @param title the title of this plot
 * @param size the size of this plot
 */
data class EChartsLayout(
    var title: String? = null,
    // todo width height?
    var size: Pair<Int, Int>? = null,
): Layout


inline fun PlotContext.layout(block: EChartsLayout.() -> Unit) {
    layout = EChartsLayout().apply(block)
}
