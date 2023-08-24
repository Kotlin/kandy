/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.*
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

/**
 * Base plotting DSL context.
 */
public interface BaseContext

/**
 * Context with layers collecting.
 *
 * @property layers layers buffer.
 */
public abstract class LayerCollectorContext : BaseContext {
    protected abstract val _plotContext: PlotContext

    protected open val _datasetIndex: Int = 0
    protected open val _layers: MutableList<Layer> = mutableListOf()
    protected open val _layersInheritMappings: Boolean = true

    @PublishedApi
    internal val datasetIndex: Int
        get() = _datasetIndex

    @PublishedApi
    internal val plotContext: PlotContext
        get() = _plotContext

    @PublishedApi
    internal val datasetHandler: DatasetHandler
        get() = _plotContext.datasetHandlers[_datasetIndex]

    internal val layers: MutableList<Layer>
        get() = _layers

    private val layersInheritMappings: Boolean
        get() = _layersInheritMappings


    /**
     * Creates a layers from [LayerContext] and adds it to the buffer.
     *
     * @param context [LayerContext] with bindings of a new layer.
     */
    public fun addLayer(context: LayerContextInterface) {
        checkRequiredAes(
            context.requiredAes, context, if (layersInheritMappings) {
                plotContext
            } else null
        )
        layers.add(
            context.toLayer(layersInheritMappings)
        )
    }
}

@PublishedApi
@Suppress("unchecked_cast")
internal val BindingContext.datasetHandler: DatasetHandler
    get() {
        val properties: Collection<KProperty1<out BindingContext, *>> = this::class.memberProperties
        return when {
            properties.any { it.name == "datasetHandler" } -> {
                (properties.find { it.name == "datasetHandler" } as KProperty1<BindingContext, DatasetHandler>).get(this)
            }

            properties.any { it.name == "datasetHandlers" } && properties.any { it.name == "datasetIndex" } -> {
                val datasetHandlers =
                    (properties.find { it.name == "datasetHandlers" } as KProperty1<BindingContext, MutableList<DatasetHandler>>).get(
                        this
                    )
                val datasetIndex =
                    (properties.find { it.name == "datasetIndex" } as KProperty1<BindingContext, Int>).get(this)
                datasetHandlers[datasetIndex]
            }

            else -> error("BindingContext implementation '${this::class.simpleName}' does not have expected properties for 'datasetHandler' retrieval.")
        }
    }

/**
 * Context with bindings collecting.
 *
 * @property bindingCollector collector of context bindings.
 */
public interface BindingContext : BaseContext {
    public val bindingCollector: BindingCollector

    /**
     * Adds [NonPositionalSetting] for given non-positional aes and value.
     *
     * @param aes name of aes.
     * @param value assigned value.
     * @param DomainType type of value.
     */
    public fun <DomainType> addNonPositionalSetting(
        aes: Aes,
        value: DomainType
    ): NonPositionalSetting<DomainType> {
        return NonPositionalSetting(aes, value).also {
            bindingCollector.settings[aes] = it
        }
    }

    /**
     * Adds [PositionalSetting] for given positional aes and value.
     *
     * @param aes name of aes.
     * @param value assigned value.
     * @param DomainType type of value.
     */
    public fun <DomainType> addPositionalSetting(aes: Aes, value: DomainType): PositionalSetting<DomainType> {
        return PositionalSetting(aes, value).also {
            bindingCollector.settings[aes] = it
        }
    }

    /**
     * Adds [PositionalMapping] for given positional aes from given [List] of values.
     *
     * @param aes name of aes.
     * @param values [List] of mapped values.
     * @param parameters mapping parameters, optional.
     * @param DomainType type of values.
     */
    public fun <DomainType> addPositionalMapping(
        aes: Aes, values: List<DomainType>, name: String?, parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        val columnID = datasetHandler.addColumn(values, name ?: aes.name)
        return PositionalMapping<DomainType>(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Adds [PositionalMapping] for given positional aes from column of dataset referenced by given id.
     *
     * @param aes name of aes.
     * @param columnID name of mapped column of dataset.
     * @param parameters mapping parameters, optional.
     * @param DomainType type of values.
     */
    public fun <DomainType> addPositionalMapping(
        aes: Aes, columnID: String, parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        val newColumnID = datasetHandler.takeColumn(columnID)
        return PositionalMapping<DomainType>(aes, newColumnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Adds [PositionalMapping] for given positional aes from given [DataColumn] of values.
     *
     * @param aes name of aes.
     * @param values mapped column.
     * @param parameters mapping parameters, optional.
     * @param DomainType type of values.
     */
    public fun <DomainType> addPositionalMapping(
        aes: Aes, values: DataColumn<DomainType>, parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        val columnID = datasetHandler.addColumn(values)
        return PositionalMapping<DomainType>(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Adds [NonPositionalMapping] for given non-positional aes from given [List] of values.
     *
     * @param aes name of aes.
     * @param values [List] of mapped values.
     * @param parameters mapping parameters, optional.
     * @param DomainType type of values.
     */
    public fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        values: List<DomainType>,
        name: String?,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        val columnID = datasetHandler.addColumn(values, name ?: aes.name)
        return NonPositionalMapping<DomainType, RangeType>(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Adds [NonPositionalMapping] for given positional aes from given [DataColumn] of values.
     *
     * @param aes name of aes.
     * @param values mapped column.
     * @param parameters mapping parameters, optional.
     * @param DomainType type of values.
     */
    public fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        values: DataColumn<DomainType>,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        val columnID = datasetHandler.addColumn(values)
        return NonPositionalMapping<DomainType, RangeType>(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Adds [NonPositionalMapping] for given non-positional aes from column of dataset referenced by given id.
     *
     * @param aes name of aes.
     * @param columnID name of mapped column of dataset.
     * @param parameters mapping parameters, optional.
     * @param DomainType type of values.
     */
    public fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        columnID: String,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        val newColumnID = datasetHandler.takeColumn(columnID)
        return NonPositionalMapping<DomainType, RangeType>(aes, newColumnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Adds [PositionalFreeScale] for given positional aes.
     *
     * @param aes name of aes.
     * @param parameters mapping parameters.
     * @param DomainType scale domain type.
     */
    public fun <DomainType> addPositionalFreeScale(
        aes: Aes,
        parameters: PositionalMappingParameters<DomainType>
    ) {
        bindingCollector.freeScales[aes] = PositionalFreeScale<DomainType>(aes, parameters)
    }
}

/**
 * Top plotting context which allows to configure and create a [Plot].
 *
 * @property datasetHandlers buffer of plot datasets.
 * @property plotFeatures
 */
public interface PlotContext : BindingContext {
    public val datasetHandlers: MutableList<DatasetHandler>
    public val plotFeatures: MutableMap<FeatureName, PlotFeature>

    /**
     * Creates [Plot] configured by this context.
     *
     * @return new [Plot].
     */
    public fun toPlot(): Plot
}

public interface LayerContextInterface : BindingContext {
    public val geom: Geom
    public val layerFeatures: MutableMap<FeatureName, LayerFeature>
    public val requiredAes: Set<Aes>

    public fun toLayer(layersInheritMappings: Boolean): Layer
}

/**
 * Nested context. [bindingCollector], [plotContext] and [datasetIndex] are inherited from parent.
 *
 * @property parentContext parental [BindingContext].
 */
public interface SubBindingContext : BindingContext {
    public val parentContext: BindingContext
    override val bindingCollector: BindingCollector
        get() = parentContext.bindingCollector
}

/**
 * Context that defines the configuration of the layer.
 *
 * @param parent parental [LayerCollectorContext].
 * @property layerFeatures [MutableMap] of feature names to corresponding layer features.
 */
public abstract class LayerContext(parent: LayerCollectorContext) : LayerContextInterface {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()

    internal var datasetIndex: Int = parent.datasetIndex
    internal val plotContext: PlotContext = parent.plotContext

    @PublishedApi
    internal val datasetHandler: DatasetHandler = plotContext.datasetHandlers[datasetIndex]

    private var firstMapping = true
    private val handlerRowsCount: Int
        get() {
            val buffer = datasetHandler.buffer
            return if (buffer == DataFrame.Empty) {
                datasetHandler.initialNamedData.dataFrame.rowsCount()
            } else {
                buffer.rowsCount()
            }
        }

    private fun overrideDataset() {
        plotContext.datasetHandlers.add(DatasetHandler(NamedData(DataFrame.Empty)))
        datasetIndex = plotContext.datasetHandlers.lastIndex
    }

    public override fun toLayer(layersInheritMappings: Boolean): Layer {
        return Layer(
            datasetIndex,
            geom,
            bindingCollector.mappings,
            bindingCollector.settings,
            layerFeatures,
            bindingCollector.freeScales,
            layersInheritMappings
        )
    }

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
        if (firstMapping && handlerRowsCount != values.size()) {
            overrideDataset()
        }
        firstMapping = false
        return super.addNonPositionalMapping(aes, values, parameters)
    }

    override fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        values: List<DomainType>,
        name: String?,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        if (firstMapping && handlerRowsCount != values.size) {
            overrideDataset()
        }
        firstMapping = false
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
        if (firstMapping && handlerRowsCount != values.size()) {
            overrideDataset()
        }
        firstMapping = false
        return super.addPositionalMapping(aes, values, parameters)
    }

    override fun <DomainType> addPositionalMapping(
        aes: Aes,
        values: List<DomainType>,
        name: String?,
        parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        if (firstMapping && handlerRowsCount != values.size) {
            overrideDataset()
        }
        firstMapping = false
        return super.addPositionalMapping(aes, values, name, parameters)
    }

}

/**
 * Context with a grouped dataset.
 */
public class GroupedContext(
    override val _datasetIndex: Int,
    override val _plotContext: LayerPlotContext
) : LayerCollectorContext() {
    override val _layers: MutableList<Layer> = _plotContext.layers
}

public interface SingleLayerPlotContext : PlotContext {
    public val layer: Layer
    public override fun toPlot(): Plot {
        return Plot(
            datasetHandlers.map { it.data() },
            listOf(layer),
            bindingCollector.mappings,
            bindingCollector.settings,
            plotFeatures,
            bindingCollector.freeScales
        )
    }
}

/**
 *  [PlotContext] that directly collects layers and creates [Plot] from them.
 */
public abstract class LayerPlotContext : LayerCollectorContext(), PlotContext {
    override val bindingCollector: BindingCollector = BindingCollector()

    public override fun toPlot(): Plot {
        return Plot(
            datasetHandlers.map { it.data() },
            layers,
            bindingCollector.mappings,
            bindingCollector.settings,
            plotFeatures,
            bindingCollector.freeScales
        )
    }
}
