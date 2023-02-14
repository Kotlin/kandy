package org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.Plot

//@Serializable
public data class PlotGrid(
    val items: List<Plot>,
    val nCol: Int,
    val cellWidth: Int,
    val cellHeight: Int,
    val hGap: Int = 0,
    val vGap: Int = 50,
    val fit: Boolean = false
)