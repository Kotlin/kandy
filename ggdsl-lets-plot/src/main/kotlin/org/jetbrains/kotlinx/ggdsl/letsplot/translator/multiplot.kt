/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model.PlotGrid
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.gggrid


internal fun PlotBunch.wrap(): GGBunch {
    return GGBunch().apply {
        items.forEach { addPlot(it.plot.toLetsPlot(), it.x, it.y, it.width, it.height) }
    }
}

internal fun PlotGrid.wrap(): GGBunch {
    return gggrid(items.map { it.toLetsPlot() }, nCol, cellWidth, cellHeight, hGap, vGap, fit)
}

