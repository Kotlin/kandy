package org.jetbrains.kotlinx.ggdsl.dsl

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
class BindingCollector internal constructor() {
    val mappings: MutableMap<AesName, Mapping> = mutableMapOf()
    val settings: MutableMap<AesName, Setting> = mutableMapOf()
    val freeScales: MutableMap<AesName, FreeScale> = mutableMapOf()

    fun copyFrom(other: BindingCollector) {
        mappings.putAll(other.mappings)
        settings.putAll(other.settings)
    }
}

/**
 * Base class for binding context.
 *
 * In this context, the mechanism of bindings, that is, mappings and settings, is defined.
 * It is implemented with aesthetic attribute properties invocation with raw or scaled source as an argument.
 *
 * @property data the mutual dataset context.
 */
//@PlotDslMarker

interface BindingContext {
    val bindingCollector: BindingCollector
}

abstract class SubBindingContext(parentalBindingCollector: BindingCollector?) : BindingContext {
    override val bindingCollector = BindingCollector().apply {
        parentalBindingCollector?.let {
            copyFrom(it)
        }
    }
}

interface TableBindingContext : BindingContext {
    val data: TableData?
}

abstract class SubTableBindingContext(parent: TableBindingContext) : TableBindingContext,
    SubBindingContext(parent.bindingCollector) {
    override val data = parent.data
}

interface NameDataBindingContext : TableBindingContext {
    override val data: NamedDataInterface?

    fun groupBy(vararg columnPointers: ColumnPointer<*>, block: GroupedBindingContext.() -> Unit)
}


/*
abstract class BindingContext: BindingContext {
    val data: MutableNamedData = mutableMapOf()
    override val bindingCollector = BindingCollector()

    //todo
    @PublishedApi
    internal var counter = 0

    @PublishedApi
    internal fun generateID(): String = "*gen${counter++}"

    // todo add for arrays/others???
   /* @PublishedApi
    internal */
    inline fun <reified T : Any> Iterable<T>.toDataSource(): ColumnReference<T> {
        val list = toList()
        val id = generateID()
        data[id] = list
        return source(id)
    }

    // todo how to hide?
   // val bindingCollector = BindingCollector()

    // todo how to hide?
    fun copyFrom(other: BindingContext, copyData: Boolean = true) {
        if (copyData) {
            // TODO!!!
            data.putAll(other.data)
        }
        this.bindingCollector.copyFrom(other.bindingCollector)
    }


    // todo move???
    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled() =
        SourceScaledUnspecifiedDefault(this.toDataSource())

    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
        SourceScaledPositionalUnspecified(this.toDataSource(), scale)


    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
        SourceScaledNonPositionalUnspecified(this.toDataSource(), scale)


    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(
        scale: PositionalScale<DomainType>
    ) = SourceScaledPositional(this.toDataSource(), scale)


    inline fun <reified DomainType : Any, RangeType : Any> Iterable<DomainType>.scaled(
        scale: NonPositionalScale<DomainType, RangeType>
    ) = SourceScaledNonPositional(this.toDataSource(), scale)


}


 */
abstract class LayerContext(parent: LayerCollectorContext) : TableBindingContext,
    SubTableBindingContext(parent) {
    val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

interface LayerCollectorContext : TableBindingContext {
    val layers: MutableList<Layer>

    fun addLayer(context: LayerContext, geom: Geom) {
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

open class GroupedBindingContext(
    override val data: GroupedDataInterface,
    override val layers: MutableList<Layer>,
    parentalBindingCollector: BindingCollector?
) : TableBindingContext, LayerCollectorContext, SubBindingContext(parentalBindingCollector)

/*
abstract class TableLayerContext : TableBindingContext, LayerContext {
    // todo hide?
    override val features = mutableMapOf<FeatureName, LayerFeature>()
}
*/

/*
abstract class MutableLayerContext : BindingContext(), LayerContext {
    // todo hide?
    override val features = mutableMapOf<FeatureName, LayerFeature>()
}

 */

/**
 * Creates a new [Layer] from this [LayerContext]
 *
 * @return new [Plot]
 */

// todo

//@PlotDslMarker

/*
@StatDslMarker
@PlotDslMarker

 */
interface PlotContext : LayerCollectorContext {
    val features: MutableMap<FeatureName, PlotFeature>
    fun toPlot(): Plot {
        return Plot(data, layers, features, bindingCollector.freeScales)
    }
}

class NamedDataPlotContext<T: NamedDataInterface>(
    override val data: T,
) : PlotContext, NameDataBindingContext {
    override val bindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    override inline fun groupBy(
        vararg columnPointers: ColumnPointer<*>,
        block: GroupedBindingContext.() -> Unit
    ) {
        GroupedBindingContext(
            data.groupBy(*columnPointers),
            layers,
            bindingCollector
        ).apply(block)
    }
}

class GroupedDataPlotContext(
    override val data: GroupedDataInterface,
    override val layers: MutableList<Layer> = mutableListOf()
) : PlotContext, GroupedBindingContext(data, layers, null) {
    override val bindingCollector = BindingCollector()
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
}

abstract class StatContext(parent: TableBindingContext) : LayerCollectorContext,
    SubTableBindingContext(parent)
