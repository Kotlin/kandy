package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.Layout


//todo
data class LetsPlotLayout(
    var title: String? = null,
    var subtitle: String? = null,
    var caption: String? = null,
    var size: Pair<Int, Int>? = null
): Layout

inline fun PlotContext.layout(block: LetsPlotLayout.() -> Unit) {
    layout = LetsPlotLayout().apply(block)
}