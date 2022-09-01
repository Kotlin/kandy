package org.jetbrains.kotlinx.ggdsl.letsplot.export

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.PlotBunch
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.PlotGrid
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.wrap
import org.jetbrains.letsPlot.export.ggsave

fun Plot.save(
    filename: String,
    scale: Number = 2,
    dpi: Number? = null,
    path: String? = null
) = ggsave(toLetsPlot(), filename, scale, dpi, path)

fun PlotGrid.save(
    filename: String,
    scale: Number = 2,
    dpi: Number? = null,
    path: String? = null
) = ggsave(wrap(), filename, scale, dpi, path)

fun PlotBunch.save(
    filename: String,
    scale: Number = 2,
    dpi: Number? = null,
    path: String? = null
) = ggsave(wrap(), filename, scale, dpi, path)
