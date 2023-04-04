package org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model

import org.jetbrains.kotlinx.ggdsl.ir.Plot

//@Serializable
public data class PlotGrid(
    val plots: List<Plot?>,
    val nCol: Int?,
    val widths: List<Number>?,
    val heights: List<Number>?,
    val hspace: Number?,
    val vspace: Number?,
    val fit: Boolean,
    val align: Boolean,
)
