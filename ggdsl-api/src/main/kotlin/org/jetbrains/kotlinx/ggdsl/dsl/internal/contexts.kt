/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreeScale
import kotlin.reflect.typeOf

/**
 * Internal collector of mappings and settings.
 */
public class BindingCollector() {
    public val mappings: MutableMap<AesName, Mapping> = mutableMapOf()
    public val settings: MutableMap<AesName, Setting> = mutableMapOf()

    // todo
    public val freeScales: MutableMap<AesName, FreeScale> = mutableMapOf()

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
 */
public interface BindingContext {
    // todo hide
    public val bindingCollector: BindingCollector
}

public interface SubBindingContextInterface : BindingContext {
  //  public val parent: BindingContext
}

/**
 * Base class for nested contexts that inherit bindings from parents.
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
 * Interface for contexts with [TableData] as dataset.
 */
public interface TableDataContext : BindingContext {
    public val data: TableData?
}

public interface TableSubContextInterface : TableDataContext, SubBindingContextInterface

/**
 * Nested contexts that inherit bindings and data from parents.
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

/* TODO
/**
 * Interface for contexts with [NamedDataInterface] as dataset.
 */
public interface NameDataBindingContext : TableBindingContext {
    override val data: NamedDataInterface
}

 */


/**
 * Interface for contexts that collect layers.
 */

public interface LayerCollectorContextInterface : TableDataContext {
    // todo hide
    public val layers: MutableList<Layer>
    override val data: TableData
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

public interface LayerCollectorContextImmutable : LayerCollectorContextInterface

/**
 * Interface for contexts for building layers.
 */
@PlotDslMarker
public interface LayerContextInterface : TableDataContext, TableSubContextInterface {
    public val features: MutableMap<FeatureName, LayerFeature>
}

@PlotDslMarker
public abstract class LayerContextImmutable(parent: LayerCollectorContextImmutable) : LayerContextInterface,
    TableSubContextImmutable(parent, parent !is LayerPlotContext) {
    public override val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

/**
 * Interface for nested [LayerCollectorContextImmutable].
 */
@PlotDslMarker
public abstract class SubLayerCollectorContextImmutable(parent: LayerCollectorContextImmutable) : TableDataContext,
    LayerCollectorContextImmutable,
    BindingSubContextImmutable(parent) {
    override val layers: MutableList<Layer> = parent.layers
}

@PlotDslMarker
public interface GroupedDataContextInterface : TableDataContext, LayerCollectorContextImmutable {
    override val data: GroupedDataInterface
}
@PlotDslMarker
public open class GroupedDataSubContextImmutable constructor(
    override val data: GroupedDataInterface,
    override val layers: MutableList<Layer>,
    parent: BindingContext,
) : GroupedDataContextInterface, BindingSubContextImmutable(parent)

@PlotDslMarker
public interface PlotContextBase : TableDataContext {
    // todo hide
    override val data: TableData
    public val features: MutableMap<FeatureName, PlotFeature>
    public fun toPlot(): Plot
}

@PlotDslMarker
public interface LayerPlotContext : LayerCollectorContextInterface, PlotContextBase {
    // todo hide
    public override fun toPlot(): Plot {
        return Plot(data, layers, bindingCollector.mappings, features, bindingCollector.freeScales)
    }
}

@PlotDslMarker
public class NamedDataPlotContext(
    override val data: NamedDataInterface,
) : LayerPlotContext, LayerCollectorContextImmutable {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

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

@PlotDslMarker
public class GroupedDataPlotContext(
    override val data: GroupedDataInterface,
) : LayerPlotContext, GroupedDataContextInterface {
    override val layers: MutableList<Layer> = mutableListOf()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val bindingCollector: BindingCollector = BindingCollector()
}
