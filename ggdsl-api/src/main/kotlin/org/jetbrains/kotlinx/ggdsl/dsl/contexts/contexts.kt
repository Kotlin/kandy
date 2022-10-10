/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.contexts

import org.jetbrains.kotlinx.ggdsl.dsl.GatherDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.StatDSLMarker
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
import org.jetbrains.kotlinx.ggdsl.ir.scale.*

/**
 * Internal collector of mappings and settings.
 */
public class BindingCollector internal constructor() {
    public val mappings: MutableMap<AesName, Mapping> = mutableMapOf()
    public val settings: MutableMap<AesName, Setting> = mutableMapOf()

    public val freeScales: MutableMap<AesName, FreeScale> = mutableMapOf()

    internal fun copyFrom(other: BindingCollector) {
        mappings.putAll(other.mappings)
        settings.putAll(other.settings)
    }
}

/**
 * Base class for binding context.
 *
 * In this context, the mechanism of bindings, that is, mappings and settings, is defined.
 * It is implemented with aesthetic attribute properties invocation with a raw or scaled source as an argument.
 *
 * @property data the mutual dataset context.
 */

//@PlotDslMarker


public interface BindingContext {
    // todo hide
    public val bindingCollector: BindingCollector
}


public abstract class SubBindingContext(parentalBindingCollector: BindingCollector?) : BindingContext {
    override val bindingCollector: BindingCollector = BindingCollector().apply {
        parentalBindingCollector?.let {
            copyFrom(it)
        }
    }
}


public interface TableBindingContext : BindingContext {
    public val data: TableData
}


public abstract class SubTableBindingContext(parent: TableBindingContext) : TableBindingContext,
    SubBindingContext(parent.bindingCollector) {
    override val data: TableData = parent.data
}

public interface NameDataBindingContext : TableBindingContext {
    override val data: NamedDataInterface
}


public abstract class LayerContext(parent: LayerCollectorContext) : TableBindingContext,
    SubTableBindingContext(parent) {
    public val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()

}


public interface LayerCollectorContextInterface : BindingContext {
    public val layers: MutableList<Layer>

}

public interface LayerCollectorContext : LayerCollectorContextInterface, TableBindingContext {
    // todo hide
    public fun addLayer(context: LayerContext, geom: Geom) {
        layers.add(
            Layer(
                data,
                geom,
                context.bindingCollector.mappings,
                context.bindingCollector.settings,
                context.features,
                context.bindingCollector.freeScales
            )
        )
    }
}

public abstract class SubLayerCollectorContext(parent: LayerCollectorContextInterface) : TableBindingContext,
    LayerCollectorContext,
    SubBindingContext(parent.bindingCollector) {
    override val layers: MutableList<Layer> = parent.layers
}

@GatherDslMarker
public class GatheredNamedDataContext<T : Any>(
    parent: LayerCollectorContextInterface,
    override val data: NamedDataInterface,
    valuesColumnName: String,
    keysColumnName: String,
) : NameDataBindingContext, SubLayerCollectorContext(parent) {
    public val GATHER_VALUES: ColumnPointer<T> = ColumnPointer<T>(valuesColumnName)
    public val GATHER_KEYS: ColumnPointer<String> = ColumnPointer<String>(keysColumnName)
}


@PlotDslMarker
@StatDSLMarker
public open class WithGroupingBindingContext constructor(
    override val data: GroupedDataInterface,
    override val layers: MutableList<Layer>,
    parentalBindingCollector: BindingCollector?
) : TableBindingContext, LayerCollectorContext, SubBindingContext(parentalBindingCollector)


public interface PlotContext : LayerCollectorContextInterface, TableBindingContext {
    // todo hide
    public val features: MutableMap<FeatureName, PlotFeature>
    public fun toPlot(): Plot {
        return Plot(data, layers, features, bindingCollector.freeScales)
    }
}

@PlotDslMarker
@GatherDslMarker
@StatDSLMarker
public class NamedDataPlotContext<T : NamedDataInterface>(
    override val data: T,
) : PlotContext, NameDataBindingContext, LayerCollectorContext {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    public inline fun groupBy(
        vararg columnPointers: ColumnPointer<*>,
        block: WithGroupingBindingContext.() -> Unit
    ) {
        WithGroupingBindingContext(
            data.groupBy(*columnPointers),
            layers,
            bindingCollector
        ).apply(block)
    }

    public inline fun <T : Any> gather(
        valuesColumnName: String,
        keysColumnName: String,
        firstColumn: ColumnPointer<T>,
        secondColumn: ColumnPointer<T>,
        vararg columnPointers: ColumnPointer<T>,
        block: GatheredNamedDataContext<T>.() -> Unit
    ) {
        GatheredNamedDataContext<T>(
            this,
            data.gather(valuesColumnName, keysColumnName, firstColumn, secondColumn, *columnPointers),
            valuesColumnName,
            keysColumnName
        ).apply(block)
        TODO("Not yet implemented")
    }
}


@PlotDslMarker
public class GroupedDataPlotContext(
    override val data: GroupedDataInterface,
    override val layers: MutableList<Layer> = mutableListOf() // TODO
) : PlotContext, WithGroupingBindingContext(data, layers, null) {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
}

