/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.ggdsl.letsplot.multiplot

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model.PlotGrid

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
