package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

/*
 todo: allow dataset overriding?
 */
// todo doc
public abstract class SingleLayerPlotBuilder @PublishedApi internal constructor(data: TableData)
    : CustomPlotBuilder(), LayerBuilder {
    override val datasetHandler: DatasetHandler = DatasetHandler(data)

    override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val bindingHandler: BindingHandler = BindingHandler { datasetHandler }
    internal val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()

    internal abstract val geom: Geom
    internal abstract val requiredAes: Set<Aes>

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

    override fun toPlot(): Plot {
        return Plot(
            listOf(datasetHandler.data()),
            listOf(toLayer()),
            bindingCollector.mappings,
            bindingCollector.settings,
            plotFeatures,
            bindingCollector.freeScales
        )
    }

    private fun checkRequiredAes() {
        val assignedAes: Set<Aes> = with(bindingCollector) { mappings.keys + settings.keys }

        requiredAes.forEach {
            require(it in assignedAes) { "`${it.name}` is not assigned." }
        }
    }
}
