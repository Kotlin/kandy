package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.typeOf


public interface TableBindingContextInterfaceMutable : TableDataContext {
    public override val data: TableData
    public val dataBuffer: MutableTableData
    public fun generateID(): String
    public fun <T : Any> Iterable<T>.toColumnPointer(): ColumnPointer<T>
    public fun <T : Any> Iterable<T>.toColumnPointer(id: String): ColumnPointer<T>
}

public abstract class TableContextMutableBase() : TableBindingContextInterfaceMutable {
    override val bindingCollector: BindingCollector = BindingCollector()
    public abstract override val dataBuffer: MutableTableData
    override val data: TableData
        get() = dataBuffer.toTableData()

    private var counter: Int = 0
    public override fun generateID(): String = "*gen${counter++}"


    public override fun <T : Any> Iterable<T>.toColumnPointer(): ColumnPointer<T> = toColumnPointer(generateID())

    public override fun <T : Any> Iterable<T>.toColumnPointer(id: String): ColumnPointer<T> {
        dataBuffer.map[id] = toList()
        return columnPointer(id)
    }


    public fun <DomainType : Any> Iterable<DomainType>.scaled(): ColumnScaledUnspecifiedDefault<DomainType> =
        ColumnScaledUnspecifiedDefault(toColumnPointer())

    public fun <DomainType : Any> Iterable<DomainType>.scaled(
        scale: PositionalUnspecifiedScale
    ): ColumnScaledPositionalUnspecified<DomainType> = ColumnScaledPositionalUnspecified(toColumnPointer(), scale)


    public fun <DomainType : Any> Iterable<DomainType>.scaled(
        scale: NonPositionalUnspecifiedScale
    ): ColumnScaledNonPositionalUnspecified<DomainType> =
        ColumnScaledNonPositionalUnspecified(toColumnPointer(), scale)


    public fun <DomainType : Any> Iterable<DomainType>.scaled(
        scale: PositionalScale<DomainType>
    ): ColumnScaledPositional<DomainType> = ColumnScaledPositional(toColumnPointer(), scale)


    public fun <DomainType : Any, RangeType : Any> Iterable<DomainType>.scaled(
        scale: NonPositionalScale<DomainType, RangeType>
    ): ColumnScaledNonPositional<DomainType, RangeType> =
        ColumnScaledNonPositional(toColumnPointer(), scale)

    public inline operator fun <reified DomainType : Any> NonScalablePositionalAes.invoke(
        data: Iterable<DomainType>
    ) {
        context.bindingCollector.mappings[this.name] =
            NonScalablePositionalMapping<DomainType>(this.name, data.toColumnPointer(), typeOf<DomainType>())
    }

    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        data: Iterable<DomainType>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            data.toColumnPointer().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        dataToName: Pair<Iterable<DomainType>, String>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            dataToName.first.toColumnPointer(dataToName.second).scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

    public inline operator fun <reified DomainType : Any, RangeType : Any>
            MappableNonPositionalAes<RangeType>.invoke(
        data: Iterable<DomainType>
    ): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
        val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
            this.name,
            data.toColumnPointer().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }
}

public abstract class TableSubContextMutable(
    public open val parent: TableBindingContextInterfaceMutable,
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
    override val bindingCollector: BindingCollector  = if (copy) {
        BindingCollector(parent.bindingCollector, copyMappings, copySettings)
    } else {
        parent.bindingCollector
    }

    override fun generateID(): String = parent.generateID()
}

public interface LayerCollectorContextMutable
    : LayerCollectorContextInterface, TableBindingContextInterfaceMutable

public abstract class SubLayerCollectorContextMutable(
    parent: LayerCollectorContextMutable
) : LayerCollectorContextMutable, TableSubContextMutable(parent) {
    override val layers: MutableList<Layer> = parent.layers
}

public abstract class LayerContextMutable(parent: LayerCollectorContextMutable) :
    TableSubContextMutable(parent), LayerContextInterface {
    override val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

@PlotDslMarker
@StatDSLMarker
public class PlotContextMutable() : LayerPlotContext, LayerCollectorContextMutable, TableContextMutableBase() {
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val layers: MutableList<Layer> = mutableListOf()
    override val dataBuffer: MutableNamedData = MutableNamedData(mutableMapOf())
}
