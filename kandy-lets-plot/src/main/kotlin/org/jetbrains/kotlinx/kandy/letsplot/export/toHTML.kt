package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrapValue
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.core.util.PlotHtmlExport
import org.jetbrains.letsPlot.core.util.PlotHtmlHelper
import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.kotlinx.kandy.letsplot.internal.LETS_PLOT_JS_VERSION

internal val scriptUrl: String
    get() = PlotHtmlHelper.scriptUrl(LETS_PLOT_JS_VERSION)

/**
 * Exports the plot to HTML format.
 *
 * @receiver [Plot] - the plot to export.
 * @param iFrame Whether to wrap HTML in IFrame
 * 
 * @return A [String] in HTML format representing the exported plot.
 */
public fun Plot.toHTML(iFrame: Boolean = true): String =
    PlotHtmlExport.buildHtmlFromRawSpecs(toLetsPlot().toSpec(), scriptUrl, iFrame)

/**
 * Exports the plot grid to HTML format.
 *
 * @receiver [PlotGrid] - the plot grid to export.
 * @param iFrame Whether to wrap HTML in IFrame
 *
 * @return A [String] in HTML format representing the exported plot.
 */
public fun PlotGrid.toHTML(iFrame: Boolean = true): String =
    PlotHtmlExport.buildHtmlFromRawSpecs(wrap().toSpec(), scriptUrl, iFrame)

/**
 * Exports the plot bunch to HTML format.
 *
 * @receiver [PlotBunch] - the plot bunch to export.
 * @param iFrame Whether to wrap HTML in IFrame
 *
 * @return A [String] in HTML format representing the exported plot.
 */
public fun PlotBunch.toHTML(iFrame: Boolean = true): String =
    PlotHtmlExport.buildHtmlFromRawSpecs(wrap().toSpec(), scriptUrl, iFrame)
