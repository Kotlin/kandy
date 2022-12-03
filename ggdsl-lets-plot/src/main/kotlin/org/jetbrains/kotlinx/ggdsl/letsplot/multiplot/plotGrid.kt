/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
package org.jetbrains.kotlinx.ggdsl.letsplot.multiplot

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.Plot

@Serializable
public data class PlotGrid(
    val items: List<Plot>,
    val nCol: Int,
    val cellWidth: Int,
    val cellHeight: Int,
    val hGap: Int = 0,
    val vGap: Int = 50,
    val fit: Boolean = false
)

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
