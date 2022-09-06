package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.layoutAccessor
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

    var size: Pair<Int, Int>? = null
) : Layout {

    @PublishedApi
    internal var theme: Theme? = null
    @PublishedApi
    internal var customTheme: CustomTheme? = null

    inline fun theme(theme: Theme, block: CustomTheme.() -> Unit = {}) {
        this.theme = theme
        customTheme = CustomTheme().apply(block)
    }

    inline fun theme(block: CustomTheme.() -> Unit) {
        theme = CustomTheme().apply(block)
    }
}

inline fun PlotContext.layout(block: LetsPlotLayout.() -> Unit) {
    layoutAccessor = LetsPlotLayout().apply(block)
}

val PlotContext.layout: LetsPlotLayout
    get() {
        if(this.layoutAccessor == null) {
            this.layoutAccessor = LetsPlotLayout()
        }
        return this.layoutAccessor as LetsPlotLayout
    }
