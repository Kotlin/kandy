package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

// todo doc
// plot builder for cases when we don't want
public abstract class CustomPlotBuilder: PlotBuilder {
    internal abstract val datasetHandler: DatasetHandler
    internal val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    internal val bindingHandler: BindingHandlerDefault = BindingHandlerDefault { datasetHandler }
    internal val bindingCollector
        get() = bindingHandler.bindingCollector
}