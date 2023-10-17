/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.api.toColumnGroup
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup
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
 * The `BaseContext` interface represents a base context used in the plotting.
 * It provides a marker interface for all context classes.
 */
public interface BaseContext

/**
 * An abstract class that represents a context for collecting layers in a plot.
 *
 * @property _plotContext The plot context associated with this layer collector context.
 * @property _datasetIndex The index of the dataset within the plot context.
 * @property _layers The list of layers in the plot.
 * @property _layersInheritMappings A flag indicating whether the layers inherit mappings from the plot context.
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
     * Adds a new layer to the plot by converting the provided [LayerContextInterface] into a [Layer]
     * and appending it to the internal layers collection.
     *
     * @param context The [LayerContextInterface] that encapsulates the configurations,
     * aesthetic mappings, and other settings of the layer to be added.
     * The context provides all the necessary information to construct and visualize the layer within the plot.
     *
     * @throws IllegalArgumentException If the required aesthetics for the layer are not present.
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

/**
 * Retrieves the dataset handler associated with the binding context.
 *
 * This property provides access to the dataset handler in the given binding context.
 * The dataset handler is responsible for managing data operations related to the dataset used in the context.
 *
 * @return The dataset handler associated with the binding context.
 *
 * @throws IllegalStateException if the binding context implementation does not have the expected properties for 'datasetHandler' retrieval.
 */
@PublishedApi
@Suppress("unchecked_cast")
internal val BindingContext.datasetHandler: DatasetHandler
    get() {
        if (this is SubBindingContext) {
            return parentContext.datasetHandler
        }
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
 * The BindingContext interface represents a context for creating and managing bindings between aesthetics
 * (aes) and values.
 */
public interface BindingContext : BaseContext {
    public val bindingCollector: BindingCollector

    /**
     * Adds a [non-positional setting][NonPositionalSetting] with the given aes and value.
     *
     * @param aes the aesthetic attribute (aes) to associate with the non-positional setting.
     * @param value the value of the setting.
     * @return the newly created [NonPositionalSetting] object with the provided aes and value.
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
     * Adds a [positional setting][PositionalSetting] with the given aes and value.
     *
     * @param aes the aesthetic attribute (aes) to associate with the positional setting.
     * @param value the value of the setting.
     * @return the newly created [PositionalSetting] object with the provided aes and value.
     */
    public fun <DomainType> addPositionalSetting(aes: Aes, value: DomainType): PositionalSetting<DomainType> {
        return PositionalSetting(aes, value).also {
            bindingCollector.settings[aes] = it
        }
    }

    /**
     * Creates and adds a [positional mapping][PositionalMapping] for a given aesthetic attribute
     * ([aes]) and [values].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param values the list of values to be mapped.
     * @param name the name of the mapping (optional, defaults to the name of the aes).
     * @param parameters the positional mapping parameters (optional).
     * @return the created [positional mapping][PositionalMapping].
     */
    public fun <DomainType> addPositionalMapping(
        aes: Aes, values: List<DomainType>, name: String?, parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        val columnID = datasetHandler.addColumn(values, name ?: aes.name)
        return PositionalMapping(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Creates and adds a [positional mapping][PositionalMapping] for a given aesthetic attribute
     * ([aes]), [columnID], and [parameters].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param columnID the column ID from dataset to be mapped.
     * @param parameters the positional mapping parameters (optional).
     * @return the created [positional mapping][PositionalMapping].
     */
    public fun <DomainType> addPositionalMapping(
        aes: Aes, columnID: String, parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        val newColumnID = datasetHandler.takeColumn(columnID)
        return PositionalMapping(aes, newColumnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Creates and adds a [positional mapping][PositionalMapping] for a given aesthetic attribute
     * ([aes]), [values], and [parameters].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param values the [DataColumn] of values to be mapped.
     * @param parameters the positional mapping parameters (optional).
     * @return the created [positional mapping][PositionalMapping].
     */
    public fun <DomainType> addPositionalMapping(
        aes: Aes, values: DataColumn<DomainType>, parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        val columnID = datasetHandler.addColumn(values)
        return PositionalMapping(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Creates and adds a [non-positional mapping][NonPositionalMapping] for a given aesthetic attribute
     * ([aes]) and [values].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param values the list of values to be mapped.
     * @param name the name of the mapping (optional, defaults to the name of the aes).
     * @param parameters the non-positional mapping parameters (optional).
     * @return the created [non-positional mapping][NonPositionalMapping].
     */
    public fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        values: List<DomainType>,
        name: String?,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        val columnID = datasetHandler.addColumn(values, name ?: aes.name)
        return NonPositionalMapping(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Creates and adds a [non-positional mapping][NonPositionalMapping] for a given aesthetic attribute
     * ([aes]), [values], and [parameters].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param values the [DataColumn] of values to be mapped.
     * @param parameters the non-positional mapping parameters (optional).
     * @return the created [non-positional mapping][NonPositionalMapping].
     */
    public fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        values: DataColumn<DomainType>,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        val columnID = datasetHandler.addColumn(values)
        return NonPositionalMapping(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Creates and adds a [non-positional mapping][NonPositionalMapping] for a given aesthetic attribute
     * ([aes]), [columnID], and [parameters].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param columnID the column ID from dataset to be mapped.
     * @param parameters the non-positional mapping parameters (optional).
     * @return the created [non-positional mapping][NonPositionalMapping].
     */
    public fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        columnID: String,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        val newColumnID = datasetHandler.takeColumn(columnID)
        return NonPositionalMapping(aes, newColumnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }
    }

    /**
     * Adds a [non-positional mapping][NonPositionalMapping] for a given positional aesthetic attribute
     * ([aes]) and [parameters] to [binding collector][BindingCollector].
     *
     * @param aes the positional aesthetic attribute (aes) to be mapped.
     * @param parameters the positional mapping parameters (optional).
     */
    public fun <DomainType> addPositionalFreeScale(
        aes: Aes,
        parameters: PositionalMappingParameters<DomainType>
    ) {
        bindingCollector.freeScales[aes] = PositionalFreeScale(aes, parameters)
    }
}

/**
 * Represents the high-level context for creating and configuring a [Plot].
 *
 * The `PlotContext` provides a structured environment to define and manage datasets,
 * features, and other aesthetic bindings essential for generating a plot.
 * It acts as an intermediary between raw data and the final visual representation
 * by aggregating all necessary components and then allowing the creation of a [Plot] using the `toPlot` method.
 *
 * @property datasetHandlers A mutable list to store and manage various dataset handlers,
 * which help in processing and translating raw data sources into visual representations.
 * @property plotFeatures A mutable map associating feature names to their respective plot features,
 * enabling custom visual enhancements and modifications on the plot.
 */
public interface PlotContext : BindingContext {
    public val datasetHandlers: MutableList<DatasetHandler>
    public val plotFeatures: MutableMap<FeatureName, PlotFeature>

    /**
     * Creates a [Plot] instance based on the current configurations and bindings
     * present within this context.
     *
     * @return A newly instantiated [Plot] configured by the current context.
     */
    public fun toPlot(): Plot
}

/**
 * Represents a context specifically tailored for creating and configuring layers within a plot.
 *
 * The `LayerContextInterface` forms the backbone of defining how individual layers in a plot should appear and behave.
 * It provides the essential properties and mechanisms to handle geometries,
 * features, and aesthetic bindings necessary for the formation of a layer.
 *
 * @property geom the geometry of the layer that determines the visual representation.
 * @property layerFeatures a mutable map connecting feature names to their respective layer-specific features,
 * allowing for granular visual and functional customizations at the layer level.
 * @property requiredAes a set of aesthetics that are vital for the proper visual representation of the layer.
 */
public interface LayerContextInterface : BindingContext {
    public val geom: Geom
    public val layerFeatures: MutableMap<FeatureName, LayerFeature>
    public val requiredAes: Set<Aes>

    /**
     * Produces a [Layer] instance based on the current configurations, bindings, and
     * properties found within this context.
     *
     * @param layersInheritMappings a flag determining whether the resulting layer should inherit mappings from a higher-level context.
     * @return a created [Layer] as configured by this context.
     */
    public fun toLayer(layersInheritMappings: Boolean): Layer
}

/**
 * Represents a sub-context derived from a parent [BindingContext], inheriting essential property [bindingCollector].
 * The `SubBindingContext` acts as a proxy to its parent context,
 * enabling hierarchical and organized configuration within the plotting environment.
 *
 * @property parentContext The primary [BindingContext] from which this sub-context derives
 * its inherited properties and configurations.
 */
public interface SubBindingContext : BindingContext {
    public val parentContext: BindingContext
    override val bindingCollector: BindingCollector
        get() = parentContext.bindingCollector
}

/**
 * Represents the configuration context for a layer within a plot,
 * managing its aesthetic mappings, settings, and associated features.
 * The `LayerContext` provides a mechanism
 * to define how data should be visually presented in a layer and how it interacts with the overall plot.
 *
 * @param parent The primary [LayerCollectorContext] from which this layer context derives its foundational configurations.
 * @property layerFeatures A mutable map associating feature names with their corresponding layer-specific features.
 */
public abstract class LayerContext(parent: LayerCollectorContext) : LayerContextInterface {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()

    internal var datasetIndex: Int = parent.datasetIndex
    private val plotContext: PlotContext = parent.plotContext

    @PublishedApi
    internal val datasetHandler: DatasetHandler
        get() = plotContext.datasetHandlers[datasetIndex]

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

    private fun checkAdnOverrideDataset(size: Int) {
        if (firstMapping && handlerRowsCount != size) {
            overrideDataset()
        }
        firstMapping = false
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
        checkAdnOverrideDataset(values.size())
        return super.addNonPositionalMapping(aes, values, parameters)
    }

    override fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        values: List<DomainType>,
        name: String?,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        checkAdnOverrideDataset(values.size)
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
        checkAdnOverrideDataset(values.size())
        return super.addPositionalMapping(aes, values, parameters)
    }

    override fun <DomainType> addPositionalMapping(
        aes: Aes,
        values: List<DomainType>,
        name: String?,
        parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        checkAdnOverrideDataset(values.size)
        return super.addPositionalMapping(aes, values, name, parameters)
    }

}

/**
 * Represents a context specifically tailored for managing and visualizing grouped datasets.
 *
 * @property _datasetIndex Index or identifier for the specific dataset within the collection of datasets managed by the plot context.
 * @property _plotContext Reference to the [LayerPlotContext], which provides a broader context encompassing multiple layers and datasets.
 * @property _layers List of layers derived from the grouped dataset. These layers are managed within the broader [LayerPlotContext].
 */
public abstract class WithGroupedDataLayerCollectorContext<T, G>(
    internal val groupBy: GroupBy<T, G>,
) : LayerCollectorContext() {
    abstract override val _datasetIndex: Int
    @Suppress("UNCHECKED_CAST")
    public val key: ColumnGroup<T> =
        groupBy.concatFixed().getColumns(*groupBy.keys.columnNames().toTypedArray()).toColumnGroup(
            "key"
        ) as ColumnGroup<T>
}

public class GroupedContext<T, G>(groupBy: GroupBy<T, G>, initialBuffer: DataFrame<*>, override val _plotContext: LayerPlotContext): WithGroupedDataLayerCollectorContext<T, G>(
    groupBy
), ColumnsContainer<G> by groupBy.groups.concat() {
    override val _datasetIndex: Int = addDataset(groupBy, initialBuffer)
    override val _layers: MutableList<Layer>
        get() = _plotContext.layers
}

/**
 * Defines a specialized plotting context tailored for plots with a single layer.
 *
 * @property layer The single [Layer] managed by this context, representing the visual configuration and data mappings for the plot.
 */
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
 * A specialized [PlotContext] that facilitates the collection of layers
 * and constructs a [Plot] from the accumulated layers.
 *
 * @property bindingCollector A collector that consolidates mappings, settings, and other configurations for this plot context.
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

public abstract class GroupedLayerPlotContext<T, G>(groupBy: GroupBy<T, G>) : WithGroupedDataLayerCollectorContext<T, G>(
    groupBy
), PlotContext {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val _plotContext: PlotContext
        get() = this

    override val _datasetIndex: Int = 0

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
