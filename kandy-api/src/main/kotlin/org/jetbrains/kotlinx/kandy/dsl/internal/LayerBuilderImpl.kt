/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.*
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

/**
 * [LayerBuilder] implementation.
 */
public abstract class LayerBuilderImpl internal constructor(
    parent: LayerCreatorScope,
    internal var datasetIndex: Int = parent.datasetIndex
) : LayerBuilder {

    protected abstract val geom: Geom
    protected abstract val requiredAes: Set<Aes>

    @PublishedApi
    internal val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()

    internal val plotBuilder: MultiLayerPlotBuilder = parent.plotBuilder
    private var firstMapping: Boolean = true
    internal val datasetHandler: DatasetHandler
        get() = plotBuilder.datasetHandlers[datasetIndex]
    internal open var inheritMappings: Boolean = parent.layersInheritMappings
    private val handlerRowsCount: Int
        get() {
            val buffer = datasetHandler.buffer
            return if (buffer == DataFrame.Empty) {
                datasetHandler.initialNamedData.dataFrame.rowsCount()
            } else {
                buffer.rowsCount()
            }
        }

    private fun overrideDataset(data: TableData) {
        datasetIndex = plotBuilder.addDataset(data)
    }

    internal fun checkSourceSizeAndOverrideDataset(size: Int) {
        if (firstMapping && handlerRowsCount != size) {
            overrideDataset(NamedData(DataFrame.Empty))
            inheritMappings = false
        }
        firstMapping = false
    }

    internal val bindingHandler: BindingHandler = object : BindingHandler({ datasetHandler }) {
        override fun <DomainType, RangeType> addNonPositionalMapping(
            aes: Aes,
            columnID: String,
            parameters: NonPositionalMappingParameters<DomainType, RangeType>?
        ): NonPositionalMapping<DomainType, RangeType> {
            firstMapping = false
            return super.addNonPositionalMapping(aes, columnID, parameters)
        }

        override fun <DomainType, RangeType> addNonPositionalMapping(
            aes: Aes,
            values: DataColumn<DomainType>,
            parameters: NonPositionalMappingParameters<DomainType, RangeType>?
        ): NonPositionalMapping<DomainType, RangeType> {
            checkSourceSizeAndOverrideDataset(values.size())
            return super.addNonPositionalMapping(aes, values, parameters)
        }

        override fun <DomainType, RangeType> addNonPositionalMapping(
            aes: Aes,
            values: List<DomainType>,
            name: String?,
            parameters: NonPositionalMappingParameters<DomainType, RangeType>?
        ): NonPositionalMapping<DomainType, RangeType> {
            checkSourceSizeAndOverrideDataset(values.size)
            return super.addNonPositionalMapping(aes, values, name, parameters)
        }

        override fun <DomainType> addPositionalMapping(
            aes: Aes,
            columnID: String,
            parameters: PositionalMappingParameters<DomainType>?
        ): PositionalMapping<DomainType> {
            firstMapping = false
            return super.addPositionalMapping(aes, columnID, parameters)
        }

        override fun <DomainType> addPositionalMapping(
            aes: Aes,
            values: DataColumn<DomainType>,
            parameters: PositionalMappingParameters<DomainType>?
        ): PositionalMapping<DomainType> {
            checkSourceSizeAndOverrideDataset(values.size())
            return super.addPositionalMapping(aes, values, parameters)
        }

        override fun <DomainType> addPositionalMapping(
            aes: Aes,
            values: List<DomainType>,
            name: String?,
            parameters: PositionalMappingParameters<DomainType>?
        ): PositionalMapping<DomainType> {
            checkSourceSizeAndOverrideDataset(values.size)
            return super.addPositionalMapping(aes, values, name, parameters)
        }
    }

    /**
     * Ensures that all required aesthetics are assigned either in the layer or plot context.
     * If any of the required aesthetics are not found, an exception is thrown.
     *
     * @param requiredAes A set of aesthetics that need to be assigned.
     * @param layerBuilder The context of the layer where the aesthetics could be assigned.
     * @param plotContext The context of the plot where the aesthetics could be assigned (optional).
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
