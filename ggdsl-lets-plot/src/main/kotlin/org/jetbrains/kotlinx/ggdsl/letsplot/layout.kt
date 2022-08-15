package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.Layout
import org.jetbrains.kotlinx.ggdsl.letsplot.theme.CustomTheme
import org.jetbrains.kotlinx.ggdsl.letsplot.theme.Theme


//todo
@PlotDslMarker
data class LetsPlotLayout(
    var title: String? = null,
    var subtitle: String? = null,
    var caption: String? = null,
    // todo remove?
    var xAxisLabel: String? = null,
    var yAxisLabel: String? = null,

    @PublishedApi
    internal var theme: Theme? = null,

    var size: Pair<Int, Int>? = null
) : Layout {

    fun theme(theme: Theme) {
        this.theme = theme
    }


    inline fun theme(block: CustomTheme.() -> Unit) {
        theme = CustomTheme().apply(block)
    }
}

inline fun PlotContext.layout(block: LetsPlotLayout.() -> Unit) {
    layout = LetsPlotLayout().apply(block)
}