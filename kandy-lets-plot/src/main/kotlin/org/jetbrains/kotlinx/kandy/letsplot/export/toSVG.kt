package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrapValue
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.intern.toSpec

/**
 * Exports the plot to SVG format.
 *
 * @receiver [Plot] - the plot to export.
 *
 * @return A [String] in SVG format representing the exported plot.
 */
public fun Plot.toSVG(): String = PlotSvgExport.buildSvgImageFromRawSpecs(toLetsPlot().toSpec())

/**
 * Exports the plot grid to SVG format.
 *
 * @receiver [PlotGrid] - the plot grid to export.
 *
 * @return A [String] in SVG format representing the exported plot.
 */
public fun PlotGrid.toSVG(): String = PlotSvgExport.buildSvgImageFromRawSpecs(wrap().toSpec())

/**
 * Exports the plot bunch to SVG format.
 *
 * @receiver [PlotBunch] - the plot bunch to export.
 *
 * @return A [String] in SVG format representing the exported plot.
 */
public fun PlotBunch.toSVG(): String = PlotSvgExport.buildSvgImageFromRawSpecs(wrap().toSpec())
