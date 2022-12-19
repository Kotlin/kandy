/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.ggdsl.letsplot.multiplot

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model.PlotGrid

public fun plotGrid(
    items: List<Plot>,
    nCol: Int,
    cellWidth: Int,
    cellHeight: Int,
    hGap: Int = 0,
    vGap: Int = 50,
    fit: Boolean = false
): PlotGrid = PlotGrid(
    items, nCol, cellWidth, cellHeight, hGap, vGap, fit
)
