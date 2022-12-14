/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
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

    // todo
    public val freeScales: MutableMap<AesName, FreeScale> = mutableMapOf()

    /**
     * Constructor with copying bindings from other collector.
     *
     * @param other inherited [BindingCollector].
     * @param copyMappings whether to inherit the mappings.
     * @param copySettings whether to inherit the settings.
     */
    public constructor(
        other: BindingCollector,
        copyMappings: Boolean = true,
        copySettings: Boolean = true,
    ) : this() {
        if (copyMappings) mappings.putAll(other.mappings)
        if (copySettings) settings.putAll(other.settings)
    }
}

/**
 * Base interface for context with bindings.
 *
 * @property bindingCollector [BindingCollector] of this context.
 */
public interface BindingContext {
    // todo hide
    public val bindingCollector: BindingCollector
}

/**
 * Interface for nested binding context.
 */
public interface SubBindingContextInterface : BindingContext

/**
 * Base class for nested contexts (that can inherit bindings from parent) with immutable mappings
 * (i.e. mappings from existing columns on prepared dataset).
 *
 * @constructor Constructor with copying bindings from parent collector.
 * @param parent parental context.
 * @param cloneBindings whether to inherit bindings from parental context.
 * @param copyMappings whether to inherit the mappings.
 * @param copySettings whether to inherit the settings.
 */
public abstract class BindingSubContextImmutable(
    parent: BindingContext,
    cloneBindings: Boolean = true,
    copyMappings: Boolean = true,
    copySettings: Boolean = true,
) : SubBindingContextInterface {
    override val bindingCollector: BindingCollector = if (cloneBindings) {
        BindingCollector(parent.bindingCollector, copyMappings, copySettings)
    } else {
        BindingCollector()
    }
}

/**
 * Interface for bindings contexts with [TableData] as dataset.
 *
 * @property data context dataset of type [TableData] (nullable).
 */
public interface TableDataContext : BindingContext {
    public val data: TableData?
}

/**
 * Interface for nested bindings contexts with [TableData] as dataset.
 */
public interface TableSubContextInterface : TableDataContext, SubBindingContextInterface

/**
 * Nested context that can inherit bindings and data from parents.
 *
 * @constructor Constructor with copying bindings from parent collector.
 * @param parent parental context.
 * @param copyData whether to inherit dataset from parental context.
 * @param cloneBindings whether to inherit bindings from parental context.
 * @param copyMappings whether to inherit the mappings.
 * @param copySettings whether to inherit the settings.
 */
public abstract class TableSubContextImmutable(
    parent: TableDataContext,
    copyData: Boolean = false,
    cloneBindings: Boolean = true,
    copyMappings: Boolean = true,
    copySettings: Boolean = true,
) : TableSubContextInterface, BindingSubContextImmutable(parent, cloneBindings, copyMappings, copySettings) {
    override val data: TableData? = if (copyData) {
        parent.data
    } else null
}


/**
 * Context with layer collecting.
 *
 * @property layers layers buffer.
 * @property data context dataset.
 * @property addLayer creates a new layer from layer context.
 */
public interface LayerCollectorContextInterface : TableDataContext {
    // todo hide
    public val layers: MutableList<Layer>
    override val data: TableData

    /**
     * Creates and adds to the buffer a new layer from a layer context.
     *
     * @param context [LayerContextInterface] with bindings of a new layer.
     * @param geom [Geom] of a new layer.
     */
    public fun addLayer(context: LayerContextInterface, geom: Geom) {
        layers.add(
            Layer(
                context.data,
                geom,
                context.bindingCollector.mappings,
                context.bindingCollector.settings,
                context.features,
                context.bindingCollector.freeScales
            )
        )
    }
}

/**
 * Layer collector context with immutable mappings.
 */
public interface LayerCollectorContextImmutable : LayerCollectorContextInterface

/**
 * Layer building contexts.
 *
 * @property features [MutableMap] of feature names to layer features.
 */
@PlotDslMarker
public interface LayerContextInterface : TableDataContext, TableSubContextInterface {
    public val features: MutableMap<FeatureName, LayerFeature>
}

/**
 * Layer context with immutable mappings.
 *
 * @property features [MutableMap] of feature names to layer features.
 */
@PlotDslMarker
public abstract class LayerContextImmutable(parent: LayerCollectorContextImmutable) : LayerContextInterface,
    TableSubContextImmutable(parent, parent !is LayerPlotContext) {
    public override val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

/**
 * Nested layer collector context with immutable mappings.
 *
 * @property layers layers buffer, inherited from a parent.
 */
@PlotDslMarker
public abstract class SubLayerCollectorContextImmutable(parent: LayerCollectorContextImmutable)
    : TableDataContext, LayerCollectorContextImmutable, BindingSubContextImmutable(parent) {
    override val layers: MutableList<Layer> = parent.layers
}

/**
 * Context with a grouped data.
 *
 * @property data dataset of type [GroupedDataInterface].
 */
@PlotDslMarker
public interface GroupedDataContextInterface : TableDataContext, LayerCollectorContextImmutable {
    override val data: GroupedDataInterface
}

/**
 * Context with a grouped data with immutable mappings.
 *
 * @property data dataset of type [GroupedDataInterface].
 */
@PlotDslMarker
public open class GroupedDataSubContextImmutable constructor(
    override val data: GroupedDataInterface,
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
@PlotDslMarker
public interface PlotContextBase : TableDataContext {
    // todo hide
    override val data: TableData
    public val features: MutableMap<FeatureName, PlotFeature>
    public fun toPlot(): Plot
}

/**
 * Plot with an explicit layers creating context.
 */
@PlotDslMarker
public interface LayerPlotContext : LayerCollectorContextInterface, PlotContextBase {
    // todo hide
    public override fun toPlot(): Plot {
        return Plot(data, layers, bindingCollector.mappings, features, bindingCollector.freeScales)
    }
}

/**
 * Layer plot with a dataset of type [NamedDataInterface] context.
 */
@PlotDslMarker
public class NamedDataPlotContext(
    override val data: NamedDataInterface,
) : LayerPlotContext, LayerCollectorContextImmutable {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    /**
     * Performs grouping of this plot dataset by given columns.
     * Creates [GroupedDataSubContextImmutable].
     *
     * @param columnPointers pointers to grouping keys columns.
     */
    public inline fun groupBy(
        vararg columnPointers: ColumnPointer<*>,
        block: GroupedDataSubContextImmutable.() -> Unit
    ) {
        GroupedDataSubContextImmutable(
            data.groupBy(*columnPointers),
            layers,
            this
        ).apply(block)
    }
}

/**
 * Layer plot with a dataset of type [GroupedDataContextInterface] context.
 */
@PlotDslMarker
public class GroupedDataPlotContext(
    override val data: GroupedDataInterface,
) : LayerPlotContext, GroupedDataContextInterface {
    override val layers: MutableList<Layer> = mutableListOf()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val bindingCollector: BindingCollector = BindingCollector()
}
