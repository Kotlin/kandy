package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.letsPlot.intern.FeatureList

public interface ExternalLetsPlotFeature: PlotFeature {
    public fun wrap(): FeatureList
}
