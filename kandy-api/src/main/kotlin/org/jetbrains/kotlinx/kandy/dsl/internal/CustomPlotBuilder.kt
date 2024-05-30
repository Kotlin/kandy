package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

/**
 * Base class for [PlotBuilder] with non-standard configuration method (i.e., not by adding layers).
 */
public abstract class CustomPlotBuilder: PlotBuilder {
    /**
     * Plot builder dataset handler.
     */
    internal abstract val datasetHandler: DatasetHandler
    /**
     * Plot builder features collector.
     */
    internal abstract val plotFeatures: MutableMap<FeatureName, PlotFeature>
    /**
     * Plot builder binding handler.
     */
    internal abstract val bindingHandler: BindingHandler
    /**
     * Plot builder binding collector.
     */
    internal val bindingCollector
        get() = bindingHandler.bindingCollector
}
