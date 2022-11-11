package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.letsPlot.intern.FeatureList

public interface ExternalLetsPlotFeature: PlotFeature {
    public fun wrap(): FeatureList
}
