package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

/*
 todo: allow dataset overriding?
 */
internal abstract class SingleLayerPlotBuilder : PlotBuilder, LayerBuilder {
    internal abstract val datasetHandler: DatasetHandler
    internal val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    internal val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()

    abstract val geom: Geom
    abstract val requiredAes: Set<Aes>

    internal abstract val bindingHandler: BindingHandlerDefault
    internal val bindingCollector
        get() = bindingHandler.bindingCollector

    override fun toLayer(): Layer {
        checkRequiredAes()
        return Layer(
            0,
            geom,
            bindingCollector.mappings,
            bindingCollector.settings,
            layerFeatures,
            bindingCollector.freeScales,
            false
        )
    }

    private fun checkRequiredAes() {
        val assignedAes: Set<Aes> = with(bindingCollector) { mappings.keys + settings.keys }

        requiredAes.forEach {
            require(it in assignedAes) { "`${it.name}` is not assigned." }
        }
    }
}