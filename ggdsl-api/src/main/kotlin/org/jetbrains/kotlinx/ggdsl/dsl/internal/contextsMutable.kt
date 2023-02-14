package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.column.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.typeOf

/**
 * Context with mutable mappings (i.e. with dynamic dataset change - usage
 * iterable instead of prepared columns).
 *
 * @property data context dataset of type [TableData]. Consists of the dynamic data sources.
 * @property dataBuffer dataset buffer for dynamic data sources.
 * @property generateID internal method. Generates unique id (name of column in
 * dataframe) for an added source.
 */
public interface TableBindingContextInterfaceMutable : TableDataContext {
    public override val data: TableData
    public val dataBuffer: MutableTableData
    public fun generateID(): String
}

/**
 * Converts the given [Iterable] to the [ColumnReference] (while adding it to the dataset as a new column).
 *
 * @receiver context into which the iterable will be added.
 * @param iterable converting [Iterable] that will be added as a column to context dataset.
 * @return [ColumnReference] to a new column
 */
public inline fun <reified T> TableBindingContextInterfaceMutable.toColumnReference(iterable: Iterable<T>)
        : ColumnReference<T> = toColumnReference(iterable, generateID())

/**
 * Converts the given [Iterable] to the [ColumnReference] with the given id as
 * a column name (while adding it to the dataset).
 *
 * @receiver context into which the iterable will be added.
 * @param iterable converting [Iterable] that will be added as a column to context dataset.
 * @param id name of a new column.
 * @return [ColumnReference] to a new column
 */
public inline fun <reified T> TableBindingContextInterfaceMutable.toColumnReference(
    iterable: Iterable<T>,
    id: String
): ColumnReference<T> {
    dataBuffer.map[id] = TypedList(typeOf<T>(), iterable.toList())
    return ColumnReference(id)
}

/**
 * Context with immutable bindings base implementation.
 *
 * In this context the mappings and scales with [Iterable] are defined. TODO arrays etc.
 */
public abstract class TableContextMutableBase : TableBindingContextInterfaceMutable {
    override val bindingCollector: BindingCollector = BindingCollector()
    public abstract override val dataBuffer: MutableTableData
    override val data: TableData
        get() = dataBuffer.toTableData()

    private var counter: Int = 0
    public override fun generateID(): String = "*gen${counter++}"

    /**
     * Adds an [Iterable] to context dataset as a column and applies default
     * (i.e. without specifying the type and parameters;
     * they will be defined automatically; can be both used for positional and non-positional
     * mappings) scale to a new column.
     *
     * @receiver [Iterable] that will be added as a column to context dataset.
     * @param DomainType type of the domain.
     * @return scaled added column.
     */
    public inline fun <reified DomainType> Iterable<DomainType>.scaled(): ColumnScaledUnspecifiedDefault<DomainType> =
        ColumnScaledUnspecifiedDefault(toColumnReference(this))

    /**
     * Adds an [Iterable] to context dataset as a column with given name and applies default
     * (i.e. without specifying the type and parameters;
     * they will be defined automatically; can be both used for positional and non-positional
     * mappings) scale to a new column.
     *
     * @receiver [Pair] of [Iterable] that will be added as a column to context dataset to [String]
     * that will be used as a new column name.
     * @param DomainType type of the domain.
     * @return scaled added column.
     */
    public inline fun <reified DomainType> Pair<Iterable<DomainType>, String>.scaled()
            : ColumnScaledUnspecifiedDefault<DomainType> =
        ColumnScaledUnspecifiedDefault(toColumnReference(first, second))

    /**
     * Adds an [Iterable] to context dataset as a column and applies an unspecified
     * (i.e. without specifying the type and parameters;
     * they will be defined automatically) positional scale to a new column.
     *
     * @receiver [Iterable] that will be added as a column to context dataset.
     * @param DomainType type of the domain.
     * @param scale applying positional unspecified scale.
     * @return scaled added column.
     */
    public inline fun <reified DomainType> Iterable<DomainType>.scaled(
        scale: PositionalUnspecifiedScale
    ): ColumnScaledPositionalUnspecified<DomainType> = ColumnScaledPositionalUnspecified(toColumnReference(this), scale)

    /**
     * Adds an [Iterable] to context dataset as a column with given name and applies an unspecified
     * (i.e. without specifying the type and parameters;
     * they will be defined automatically) poistional scale to a new column.
     *
     * @receiver [Pair] of [Iterable] that will be added as a column to context dataset to [String]
     * that will be used as a new column name.
     * @param DomainType type of the domain.
     * @param scale applying positional unspecified scale.
     * @return scaled added column.
     */
    public inline fun <reified DomainType> Pair<Iterable<DomainType>, String>.scaled(
        scale: PositionalUnspecifiedScale
    ): ColumnScaledPositionalUnspecified<DomainType> =
        ColumnScaledPositionalUnspecified(toColumnReference(first, second), scale)

    /**
     * Adds an [Iterable] to context dataset as a column and applies an unspecified
     * (i.e. without specifying the type and parameters;
     * they will be defined automatically) non-positional scale to a new column.
     *
     * @receiver [Iterable] that will be added as a column to context dataset.
     * @param DomainType type of the domain.
     * @param scale applying non-positional unspecified scale.
     * @return scaled added column.
     */
    public inline fun <reified DomainType> Iterable<DomainType>.scaled(
        scale: NonPositionalUnspecifiedScale
    ): ColumnScaledNonPositionalUnspecified<DomainType> =
        ColumnScaledNonPositionalUnspecified(toColumnReference(this), scale)

    /**
     * Adds an [Iterable] to context dataset as a column with given name and applies an unspecified
     * (i.e. without specifying the type and parameters;
     * they will be defined automatically) non-positional scale to a new column.
     *
     * @receiver [Pair] of [Iterable] that will be added as a column to context dataset to [String]
     * that will be used as a new column name.
     * @param DomainType type of the domain.
     * @param scale applying non-positional unspecified scale.
     * @return scaled added column.
     */
    public inline fun <reified DomainType> Pair<Iterable<DomainType>, String>.scaled(
        scale: NonPositionalUnspecifiedScale
    ): ColumnScaledNonPositionalUnspecified<DomainType> =
        ColumnScaledNonPositionalUnspecified(toColumnReference(first, second), scale)

    /**
     * Adds an [Iterable] to context dataset as a column and applies
     * a positional scale to a new column.
     *
     * @receiver [Iterable] that will be added as a column to context dataset.
     * @param DomainType type of the domain.
     * @param scale applying positional scale.
     * @return scaled added column.
     */
    public inline fun <reified DomainType> Iterable<DomainType>.scaled(
        scale: PositionalScale<DomainType>
    ): ColumnScaledPositional<DomainType> = ColumnScaledPositional(toColumnReference(this), scale)

    /**
     * Adds an [Iterable] to context dataset as a column with given name and applies a
     * positional scale to a new column.
     *
     * @receiver [Pair] of [Iterable] that will be added as a column to context dataset to [String]
     * that will be used as a new column name.
     * @param DomainType type of the domain.
     * @param scale applying positional scale.
     * @return scaled added column.
     */
    public inline fun <reified DomainType> Pair<Iterable<DomainType>, String>.scaled(
        scale: PositionalScale<DomainType>
    ): ColumnScaledPositional<DomainType> = ColumnScaledPositional(toColumnReference(first, second), scale)

    /**
     * Adds an [Iterable] to context dataset as a column and applies
     * a non-positional scale to a new column.
     *
     * @receiver [Iterable] that will be added as a column to context dataset.
     * @param DomainType type of the domain.
     * @param scale applying non-positional scale.
     * @return scaled added column.
     */
    public inline fun <reified DomainType, RangeType> Iterable<DomainType>.scaled(
        scale: NonPositionalScale<DomainType, RangeType>
    ): ColumnScaledNonPositional<DomainType, RangeType> =
        ColumnScaledNonPositional(toColumnReference(this), scale)

    /**
     * Adds an [Iterable] to context dataset as a column with given name and applies a
     * non-positional scale to a new column.
     *
     * @receiver [Pair] of [Iterable] that will be added as a column to context dataset to [String]
     * that will be used as a new column name.
     * @param DomainType type of the domain.
     * @param scale applying non-positional scale.
     * @return scaled added column.
     */
    public inline fun <reified DomainType, RangeType> Pair<Iterable<DomainType>, String>.scaled(
        scale: NonPositionalScale<DomainType, RangeType>
    ): ColumnScaledNonPositional<DomainType, RangeType> =
        ColumnScaledNonPositional(toColumnReference(first, second), scale)

    /**
     * Adds an [Iterable] to context dataset as a column and maps it
     * to this non-scalable positional ("sub-positional") aesthetic attribute.
     *
     * @param iterable [Iterable] that will be added as a column.
     */
    public inline operator fun <reified DomainType> NonScalablePositionalAes.invoke(
        iterable: Iterable<DomainType>
    ) {
        context.bindingCollector.mappings[this.name] =
            NonScalablePositionalMapping<DomainType>(this.name, toColumnReference(iterable), typeOf<DomainType>())
    }

    /**
     * Adds an [Iterable] to context dataset as a column with a given name and maps it
     * to this non-scalable positional ("sub-positional") aesthetic attribute.
     *
     * @param dataToName [Pair] of [Iterable] that will be added as a column to the name of a new column.
     */
    public inline operator fun <reified DomainType> NonScalablePositionalAes.invoke(
        dataToName: Pair<Iterable<DomainType>, String>
    ) {
        context.bindingCollector.mappings[this.name] =
            NonScalablePositionalMapping<DomainType>(
                this.name, toColumnReference(dataToName.first, dataToName.second), typeOf<DomainType>()
            )
    }

    /**
     * Adds an [Iterable] to context dataset as a column and maps it
     * to this non-scalable non-positional aesthetic attribute.
     *
     * @param iterable [Iterable] that will be added as a column.
     */
    public inline operator fun <reified DomainType, RangeType>
            NonScalableNonPositionalAes<RangeType>.invoke(
        iterable: Iterable<DomainType>
    ) {
        context.bindingCollector.mappings[this.name] =
            NonScalableNonPositionalMapping<DomainType>(
                this.name, toColumnReference(iterable), typeOf<DomainType>()
            )
    }

    /**
     * Adds an [Iterable] to context dataset as a column with the given name and maps it
     * to this non-scalable non-positional aesthetic attribute.
     *
     * @param dataToName [Pair] of [Iterable] that will be added as a column to the name of a new column.
     */
    public inline operator fun <reified DomainType, RangeType>
            NonScalableNonPositionalAes<RangeType>.invoke(
        dataToName: Pair<Iterable<DomainType>, String>
    ) {
        context.bindingCollector.mappings[this.name] =
            NonScalablePositionalMapping<DomainType>(
                this.name, toColumnReference(dataToName.first, dataToName.second), typeOf<DomainType>()
            )
    }

    /**
     * Adds an [Iterable] to context dataset as a column and maps it
     * to this positional aesthetic attribute with an unspecified
     * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
     *
     * @param iterable [Iterable] that will be added as a column.
     */
    public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
        iterable: Iterable<DomainType>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            toColumnReference(iterable).scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

    /**
     * Adds an [Iterable] to context dataset as a column with the given name and maps it
     * to this positional aesthetic attribute with an unspecified
     * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
     *
     * @param dataToName [Pair] of [Iterable] that will be added as a column to the name of a new column.
     */
    public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
        dataToName: Pair<Iterable<DomainType>, String>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            toColumnReference(dataToName.first, dataToName.second).scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

    /**
     * Adds an [Iterable] to context dataset as a column and maps it
     * to this non-positional aesthetic attribute with an unspecified
     * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
     *
     * @param iterable [Iterable] that will be added as a column.
     */
    public inline operator fun <reified DomainType, RangeType>
            ScalableNonPositionalAes<RangeType>.invoke(
        iterable: Iterable<DomainType>
    ): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
        val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
            this.name,
            toColumnReference(iterable).scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

    /**
     * Adds an [Iterable] to context dataset as a column with the given name and maps it
     * to this non-positional aesthetic attribute with an unspecified
     * (i.e. without specifying the type and parameters; they will be defined automatically) scale.
     *
     * @param dataToName [Pair] of [Iterable] that will be added as a column to the name of a new column.
     */
    public inline operator fun <reified DomainType, RangeType>
            ScalableNonPositionalAes<RangeType>.invoke(
        dataToName: Pair<Iterable<DomainType>, String>
    ): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
        val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
            this.name,
            toColumnReference(dataToName.first, dataToName.second).scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }
}

/**
 * Base class for nested contexts (that can inherit bindings from parent) with mutable mappings.
 *
 * @constructor Constructor with copying bindings from parent collector.
 * @param parent parental context.
 * @param separatedData whether to create its own data buffer and copy data from the parent dataset
 * or to use the parent dataset.
 * @param copy whether to inherit bindings from parental context.
 * @param copyMappings whether to inherit the mappings.
 * @param copySettings whether to inherit the settings.
 */
public abstract class TableSubContextMutable(
    public val parent: TableBindingContextInterfaceMutable,
    separatedData: Boolean = true,
    copy: Boolean = true,
    copyMappings: Boolean = true,
    copySettings: Boolean = true,
) : TableContextMutableBase() {
    override val dataBuffer: MutableTableData = if (separatedData) {
        MutableNamedData(parent.dataBuffer.map.toMutableMap())
    } else {
        parent.dataBuffer
    }
    override val bindingCollector: BindingCollector = if (copy) {
        BindingCollector(parent.bindingCollector, copyMappings, copySettings)
    } else {
        parent.bindingCollector
    }

    override fun generateID(): String = parent.generateID()
}

/**
 * Context with mutable bindings and layer collecting.
 */
public interface LayerCollectorContextMutable
    : LayerCollectorContextInterface, TableBindingContextInterfaceMutable

/*
public abstract class SubLayerCollectorContextMutable(
    parent: LayerCollectorContextMutable
) : LayerCollectorContextMutable, TableSubContextMutable(parent) {
    override val layers: MutableList<Layer> = parent.layers
}

 */

/**
 * Layer building context with mutable bindings.
 */
public abstract class LayerContextMutable(parent: LayerCollectorContextMutable) :
    TableSubContextMutable(parent), LayerContextInterface {
    override val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

/**
 * Plot context with mutable bindings.
 */
/*@PlotDslMarker*/
public class PlotContextMutable : LayerPlotContext, LayerCollectorContextMutable, TableContextMutableBase() {
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val layers: MutableList<Layer> = mutableListOf()
    override val dataBuffer: MutableNamedData = MutableNamedData(mutableMapOf())
}
