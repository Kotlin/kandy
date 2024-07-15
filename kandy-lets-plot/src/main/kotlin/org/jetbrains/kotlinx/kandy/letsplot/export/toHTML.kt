package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.internal.letsPlotJSUrl
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.letsPlot.core.util.PlotHtmlExport
import org.jetbrains.letsPlot.intern.toSpec


/**
 * Exports the plot to HTML format.
 *
 * @receiver [Plot] - the plot to export.
 * @param iFrame Whether to wrap HTML in IFrame
 *
 * @return A [String] in HTML format representing the exported plot.
 */
public fun Plot.toHTML(iFrame: Boolean = true): String =
    PlotHtmlExport.buildHtmlFromRawSpecs(toLetsPlot().toSpec(), letsPlotJSUrl, iFrame)

/**
 * Exports the plot grid to HTML format.
 *
 * @receiver [PlotGrid] - the plot grid to export.
 * @param iFrame Whether to wrap HTML in IFrame
 *
 * @return A [String] in HTML format representing the exported plot.
 */
public fun PlotGrid.toHTML(iFrame: Boolean = true): String =
    PlotHtmlExport.buildHtmlFromRawSpecs(wrap().toSpec(), letsPlotJSUrl, iFrame)

/**
 * Exports the plot bunch to HTML format.
 *
 * @receiver [PlotBunch] - the plot bunch to export.
 * @param iFrame Whether to wrap HTML in IFrame
 *
 * @return A [String] in HTML format representing the exported plot.
 */
public fun PlotBunch.toHTML(iFrame: Boolean = true): String =
    PlotHtmlExport.buildHtmlFromRawSpecs(wrap().toSpec(), letsPlotJSUrl, iFrame)
