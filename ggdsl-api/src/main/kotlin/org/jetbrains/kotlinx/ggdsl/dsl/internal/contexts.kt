/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreeScale

/**
 * Internal collector of mappings and settings.
 *
 * @property mappings [MutableMap] of aesthetic names to mappings.
 * @property settings [MutableMap] of aesthetic names to settings   .
 */
public class BindingCollector() {
    public val mappings: MutableMap<AesName, Mapping> = mutableMapOf()
    public val settings: MutableMap<AesName, Setting> = mutableMapOf()
    public val freeScales: MutableMap<AesName, FreeScale> = mutableMapOf()
}


/**
 * Base interface for context with bindings.
 *
 * @property bindingCollector [BindingCollector] of this context.
 */

public interface BaseContext {
    public val plotContext: PlotContext
    public val datasetIndex: Int
}

public abstract class LayerContext(parent: LayerCollectorContext) : BindingContext {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val datasetIndex: Int = parent.datasetIndex
    public val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
    override val plotContext: PlotContext = parent.plotContext
}

public interface LayerCollectorContext : BaseContext {
    // todo hide
    public val layers: MutableList<Layer>

    /**
     * Creates and adds to the buffer a new layer from a layer context.
     *
     * @param context [LayerContext] with bindings of a new layer.
     * @param geom [Geom] of a new layer.
     */
    public fun addLayer(context: LayerContext, geom: Geom) {
        layers.add(
            Layer(
                datasetIndex,
                geom,
                context.bindingCollector.mappings,
                context.bindingCollector.settings,
                context.features,
                context.bindingCollector.freeScales
            )
        )
    }
}

public interface PlotContext : BindingContext {
    public val datasetHandlers: MutableList<DatasetHandler>
    public val features: MutableMap<FeatureName, PlotFeature>
    public fun toPlot(): Plot
}

public interface LayerPlotContext : LayerCollectorContext, PlotContext {
    // todo hide
    public override fun toPlot(): Plot {
        return Plot(
            datasetHandlers.map { NamedData(it.buffer) },
            layers,
            bindingCollector.mappings,
            features,
            bindingCollector.freeScales
        )
    }
}

public interface BindingContext : BaseContext {
    public override val plotContext: PlotContext
    public override val datasetIndex: Int
    public val bindingCollector: BindingCollector
    public val datasetHandler: DatasetHandler
        get() = plotContext.datasetHandlers[datasetIndex]

    public fun <DomainType> addPositionalSetting(aesName: AesName, value: DomainType): PositionalSetting<DomainType> {
        return PositionalSetting(aesName, value).also {
            bindingCollector.settings[aesName] = it
        }
    }

    public fun <DomainType> addPositionalMapping(
        aesName: AesName, values: List<DomainType>, name: String?, parameters: PositionalMappingParameters<DomainType>
    ): PositionalMapping<DomainType> {
        val columnID = datasetHandler.addColumn(values, name ?: aesName.name)
        return PositionalMapping<DomainType>(aesName, columnID, parameters).also {
            bindingCollector.mappings[aesName] = it
        }
    }

    public fun <DomainType> addPositionalMapping(
        aesName: AesName,
        columnID: String,
        parameters: PositionalMappingParameters<DomainType>
    ): PositionalMapping<DomainType> {
        val newColumnID = datasetHandler.takeColumn(columnID)
        return PositionalMapping<DomainType>(aesName, newColumnID, parameters).also {
            bindingCollector.mappings[aesName] = it
        }
    }

    public fun <DomainType, RangeType> addNonPositionalMapping(
        aesName: AesName,
        values: List<DomainType>,
        name: String?,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>
    ): NonPositionalMapping<DomainType, RangeType> {
        val columnID = datasetHandler.addColumn(values, name ?: aesName.name)
        return NonPositionalMapping<DomainType, RangeType>(aesName, columnID, parameters).also {
            bindingCollector.mappings[aesName] = it
        }
    }

    public fun <DomainType, RangeType> addNonPositionalMapping(
        aesName: AesName,
        columnID: String,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>
    ): NonPositionalMapping<DomainType, RangeType> {
        val newColumnID = datasetHandler.takeColumn(columnID)
        return NonPositionalMapping<DomainType, RangeType>(aesName, newColumnID, parameters).also {
            bindingCollector.mappings[aesName] = it
        }
    }
}

public interface SubBindingContext: BindingContext {
    public val parentContext: BindingContext
    override val bindingCollector: BindingCollector
        get() = parentContext.bindingCollector
    override val plotContext: PlotContext
        get() = parentContext.plotContext
    override val datasetIndex: Int
        get() = parentContext.datasetIndex
}
/*

/**
 * Context with a grouped data.
 *
 * @property data dataset of type [GroupedData].
 */
/*@PlotDslMarker*/
public interface GroupedDataContextInterface : TableDataContext, LayerCollectorContextImmutable {
    override val data: GroupedData
}

/**
 * Context with a grouped data with immutable mappings.
 *
 * @property data dataset of type [GroupedData].
 */
/*@PlotDslMarker*/
public open class GroupedDataSubContextImmutable constructor(
    override val data: GroupedData,
    override val layers: MutableList<Layer>,
    parent: BindingContext,
) : GroupedDataContextInterface, BindingSubContextImmutable(parent)


/**
 * Plot creating context.
 *
 * @property data plot dataset.
 * @property features [MutableMap] of feature names to plot features.
 * @property toPlot creates a new plot from this context.
 */


/**
 * Plot with an explicit layers creating context.
 */
/*
public interface NamedDataPlotContextInterface: LayerPlotContext {
    override val data: NamedData
}

 */

/**
 * Layer plot with a dataset of type [NamedData] context.
 */
/*@PlotDslMarker*/
public class NamedDataPlotContext(
    override val data: NamedData,
) : NamedDataPlotContextInterface, LayerCollectorContextImmutable {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    /**
     * Performs grouping of this plot dataset by given columns.
     * Creates [GroupedDataSubContextImmutable].
     *
     * @param ColumnReferences pointers to grouping keys columns.
     */
    public inline fun groupBy(
        vararg columnReferences: ColumnReference<*>,
        block: GroupedDataSubContextImmutable.() -> Unit
    ) {
        GroupedDataSubContextImmutable(
            data.groupBy(*columnReferences),
            layers,
            this
        ).apply(block)
    }
}

/**
 * Layer plot with a dataset of type [GroupedDataContextInterface] context.
 */
/*@PlotDslMarker*/
public class GroupedDataPlotContext(
    override val data: GroupedData,
) : LayerPlotContext, GroupedDataContextInterface {
    override val layers: MutableList<Layer> = mutableListOf()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val bindingCollector: BindingCollector = BindingCollector()
}


 */