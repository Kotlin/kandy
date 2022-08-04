package org.jetbrains.kotlinx.ggdsl.letsplot.multiplot

import org.jetbrains.kotlinx.ggdsl.ir.Plot

data class PlotGrid(
    val items: List<Plot>,
    val nCol: Int,
    val cellWidth: Int,
    val cellHeight: Int,
    val hGap: Int = 0,
    val vGap: Int = 50,
    val fit: Boolean = false
)

fun plotGrid(
    items: List<Plot>,
    nCol: Int,
    cellWidth: Int,
    cellHeight: Int,
    hGap: Int = 0,
    vGap: Int = 50,
    fit: Boolean = false
) = PlotGrid(
    items, nCol, cellWidth, cellHeight, hGap, vGap, fit
)
