package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.PlotBunch
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.PlotGrid
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.gggrid


fun PlotBunch.wrap(): GGBunch {
    return GGBunch().apply {
        items.forEach { addPlot(it.plot.toLetsPlot(), it.x, it.y, it.width, it.height) }
    }
}

fun PlotGrid.wrap(): GGBunch {
    return gggrid(items.map { it.toLetsPlot() }, nCol, cellWidth, cellHeight, hGap, vGap, fit)
}
