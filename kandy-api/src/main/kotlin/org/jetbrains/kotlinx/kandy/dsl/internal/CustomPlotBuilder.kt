package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * Base class for [PlotBuilder] with non-standard configuration method (i.e., not by adding layers).
 */
public abstract class CustomPlotBuilder: PlotBuilder {
    internal abstract val datasetBuilder: DatasetBuilder
    internal abstract val plotFeatures: MutableMap<FeatureName, PlotFeature>
    internal abstract val bindingHandler: BindingHandler
    internal val bindingCollector
        get() = bindingHandler.bindingCollector
}
