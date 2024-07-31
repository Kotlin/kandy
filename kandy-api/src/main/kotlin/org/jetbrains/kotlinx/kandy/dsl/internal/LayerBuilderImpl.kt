/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

/**
 * [LayerBuilder] implementation.
 */
public abstract class LayerBuilderImpl internal constructor(
    parent: LayerCreatorScope,
    // index in parental plot builder list of dataset builders
    internal var datasetIndex: Int = parent.datasetIndex
) : LayerBuilder {

    protected abstract val geom: Geom
    protected abstract val requiredAes: Set<Aes>

    @PublishedApi
    internal val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()

    internal val plotBuilder: MultiLayerPlotBuilder = parent.plotBuilder
    internal val datasetBuilder: DatasetBuilder
        get() = plotBuilder.datasetBuilders[datasetIndex]
    internal open var inheritMappings: Boolean = parent.layersInheritMappings

    /**
     *  Adds a new empty dataset builder into [plotBuilder] dataset builders list
     *  and update this layer builder dataset builder pointer with this new one
     *  by updating its index in the list.
     */
    private fun overrideDatasetBuilderWithNewEmpty() {
        datasetIndex = plotBuilder.addEmptyDataset()
    }

    /**
     * Checks the validity of the mapping source size.
     * If the size is not compatible with the original dataset,
     * but it is the first mapping in this builder,
     * it pre-overrides the dataset builder with a new empty one.
     */
    internal fun checkSourceSizeAndOverrideDatasetIfNeeded(size: Int) {
        val rowsCount = datasetBuilder.rowsCount()
        if (rowsCount != size) {
            if (bindingHandler.firstMapping) {
                overrideDatasetBuilderWithNewEmpty()
                inheritMappings = false
            } else {
                error("Unexpected size of mapping source: excepted $rowsCount, but received $size")
            }
        }
    }

    internal val bindingHandler: BindingHandler = object : BindingHandler({ datasetBuilder }) {
        /**
         * Overrides [checkMappingSourceSize]:
         * allows seamless use of data sources with a size
         * different from that of the original dataset.
         */
        override fun checkMappingSourceSize(size: Int) {
            checkSourceSizeAndOverrideDatasetIfNeeded(size)
        }
    }

    /**
     * Ensures that all required aesthetics are assigned either in the layer or plot context.
     * If any of the required aesthetics are not found, an exception is thrown.
     *
     * @throws IllegalArgumentException If any of the required aesthetics is not assigned in either the layer or the plot context.
     */
    private fun checkRequiredAes() {
        val assignedAes: Set<Aes> = with(bindingCollector) {
            mappings.keys + settings.keys
        }.let { layerAssignedAes ->
            if (inheritMappings) {
                plotBuilder.bindingCollector.run {
                    mappings.keys + settings.keys
                } + layerAssignedAes
            } else {
                layerAssignedAes
            }
        }

        requiredAes.forEach {
            require(it in assignedAes) { "`${it.name}` is not assigned." }
        }
    }

    override fun toLayer(): Layer {
        checkRequiredAes()
        return Layer(
            datasetIndex,
            geom,
            bindingCollector.mappings,
            bindingCollector.settings,
            layerFeatures,
            bindingCollector.freeScales,
            inheritMappings
        )
    }

    internal val bindingCollector: BindingCollector = bindingHandler.bindingCollector

}
