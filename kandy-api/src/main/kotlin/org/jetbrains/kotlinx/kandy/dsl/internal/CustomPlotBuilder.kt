package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

// todo doc
// plot builder for cases when we don't want to add layers in the plot directly, needed for some statistic plots
public abstract class CustomPlotBuilder: PlotBuilder {
    internal abstract val datasetBuilder: DatasetBuilder
    internal abstract val plotFeatures: MutableMap<FeatureName, PlotFeature>
    internal abstract val bindingHandler: BindingHandler
    internal val bindingCollector
        get() = bindingHandler.bindingCollector
}
