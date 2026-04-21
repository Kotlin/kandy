package org.jetbrains.kotlinx.kandy.letsplot.internal

import org.jetbrains.letsPlot.core.util.PlotHtmlHelper
import org.jetbrains.letsPlot.export.VersionChecker

internal val LETS_PLOT_JS_VERSION
    get() = VersionChecker.letsPlotJsVersion

internal val letsPlotJSUrl: String
    get() = PlotHtmlHelper.scriptUrl(LETS_PLOT_JS_VERSION)