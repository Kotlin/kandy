/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.multiplot

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid

/**
 *  Combines several plots on one figure, organized in a regular grid.

 *  @param plots Collection of plots.
 *          Use Null-value to fill-in empty cells in grid.
 *  @param nCol Number of columns in grid.
 *          If not specified, shows plots horizontally, in one row.
 *  @param widths Relative width of each column of grid, left to right.
 *  @param heights Relative height of each row of grid, top-down.
 *  @param hspace Cell horizontal spacing in px. Default: 4px.
 *  @param vspace Cell vertical spacing in px. Default: 4px.
 *  @param fit Default: true.
 *          Whether to stretch each plot to match the aspect ratio of its cell (`fit=true`),
 *          or to preserve the original aspect ratio of plots (`fit=false`).
 *  @param align Default: false.
 *          If `true`, align inner areas (i.e. "geom" bounds) of plots.
 *          However, cells containing other (sub)grids are not participating in the plot "inner areas" layouting.
 *
 *  @return PlotGrid with given plots.
 */
public fun plotGrid(
    plots: List<Plot?>,
    nCol: Int? = null,
    widths: List<Number>? = null,
    heights: List<Number>? = null,
    hspace: Number? = null,
    vspace: Number? = null,
    fit: Boolean = true,
    align: Boolean = false,
): PlotGrid = PlotGrid(
    plots, nCol, widths, heights, hspace, vspace, fit, align
)
