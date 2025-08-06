/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.letsPlot.ggbunch
import org.jetbrains.letsPlot.gggrid
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure

public fun PlotBunch.wrap(): SubPlotsFigure {
    val plots = items.map { it.plot.toLetsPlot() }
    val regions = items.map { listOf(it.x,  it.y,  it.width, it.height) }
    return ggbunch(plots, regions)
}

public fun PlotGrid.wrap(): SubPlotsFigure {
    return gggrid(plots.map { it?.toLetsPlot() }, nCol, widths, heights, hspace, vspace, fit, align)
}
