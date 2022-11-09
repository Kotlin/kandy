package org.jetbrains.kotlinx.ggdsl.dsl.contexts

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.dsl.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.typeOf

public interface MutableTableData {
    public val map: MutableMap<String, List<Any>>

    public fun toTableData(): TableData
}

public open class MutableNamedData(
    public override val map: MutableMap<String, List<Any>> = mutableMapOf<String, List<Any>>()
): MutableTableData {
    public override fun toTableData(): NamedData {
        return NamedData(map.toMap())
    }

    public fun groupBy(keys: List<String>): MutableLazyGroupedData {
        return MutableLazyGroupedData(map.toMutableMap(), keys)
    }
}

public open class MutableLazyGroupedData(
    public override val map: MutableMap<String, List<Any>> = mutableMapOf<String, List<Any>>(),
    public val keys: List<String>
): MutableTableData {
    public override fun toTableData(): LazyGroupedData {
        return LazyGroupedData(keys, NamedData(map.toMap()))
    }
}

@PublishedApi
internal fun NamedData.toMutableNamedData(): MutableNamedData = MutableNamedData(map.toMutableMap())

public interface MutableDataBindingContextInterface : TableBindingContext {
    override val data: TableData
    public val dataBuffer: MutableTableData
    public val multiplier: Int
    public fun generateID(): String
    public fun <T : Any> Iterable<T>.toColumnPointer(): ColumnPointer<T>
    public fun <T : Any> Iterable<T>.toColumnPointer(id: String): ColumnPointer<T>
}

public abstract class MutableDataBindingContext : MutableDataBindingContextInterface {
    override val bindingCollector: BindingCollector = BindingCollector()
    public abstract override val dataBuffer: MutableTableData
    override val data: TableData
        get() = dataBuffer.toTableData()

    override val multiplier: Int = 1

    private var counter: Int = 0
    public override fun generateID(): String = "*gen${counter++}"


    public override fun <T : Any> Iterable<T>.toColumnPointer(): ColumnPointer<T> = toColumnPointer(generateID())

    public override fun <T : Any> Iterable<T>.toColumnPointer(id: String): ColumnPointer<T> {
        val list = toList()
        dataBuffer.map[id] = List(multiplier){list}.flatten()
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

    public inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
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

public abstract class SubMutableDataContext(
    public val parent: MutableDataBindingContextInterface,
    private val separated: Boolean = true,
) : MutableDataBindingContext() {
    override val dataBuffer: MutableTableData = if (separated){
        MutableNamedData(parent.dataBuffer.map.toMutableMap())
    } else {
        parent.dataBuffer
    }
    override val bindingCollector: BindingCollector =
        BindingCollector().apply {
            copyFrom(parent.bindingCollector)
        }

    override fun generateID(): String = parent.generateID()

    override val multiplier: Int = parent.multiplier
}

public interface LayerCollectorMutableDataContext : LayerCollectorContextInterface, MutableDataBindingContextInterface {
    public fun addLayer(context: LayerMutableDataContext, geom: Geom) {
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

public abstract class SubLayerCollectorMutableDataContext(
    parent: LayerCollectorMutableDataContext
) : LayerCollectorMutableDataContext, SubMutableDataContext(parent) {
    override val layers: MutableList<Layer> = parent.layers
}

public abstract class LayerMutableDataContext(parent: LayerCollectorMutableDataContext) :
    SubMutableDataContext(parent) {
    public val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

@GatherDslMarker
public class GatheredMutableDataContext<T : Any>(
    parent: LayerCollectorMutableDataContext,
    override val dataBuffer: MutableTableData,
    valuesColumnName: String,
    keysColumnName: String,
    override val multiplier: Int,
) : SubLayerCollectorMutableDataContext(parent) {
    public val GATHER_VALUES: ColumnPointer<T> = ColumnPointer<T>(valuesColumnName)
    public val GATHER_GROUP_KEYS: ColumnPointer<String> = ColumnPointer<String>(keysColumnName)
}

@PlotDslMarker
@GatherDslMarker
@StatDSLMarker
public class PlotMutableDataContext() : PlotContext, LayerCollectorMutableDataContext, MutableDataBindingContext() {
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val layers: MutableList<Layer> = mutableListOf()
    override val dataBuffer: MutableNamedData = MutableNamedData(mutableMapOf())
    override val multiplier: Int = 1

    public inline fun <T : Any> gather(
        valuesColumnName: String,
        keysColumnName: String,
        firstColumn: Iterable<T>,
        secondColumn: Iterable<T>,
        vararg columnPointers: Iterable<T>,
        block: GatheredMutableDataContext<T>.() -> Unit
    ) {
        val fC = firstColumn.toColumnPointer()
        val sC = secondColumn.toColumnPointer()
        val others = columnPointers.map { it.toColumnPointer() }
        GatheredMutableDataContext<T>(
            this,
            dataBuffer.toTableData().gather(
                valuesColumnName,
                keysColumnName,
                fC,
                sC,
                *others.toTypedArray(),
            ).toMutableNamedData(),
            valuesColumnName,
            keysColumnName,
            others.size + 2,
        ).apply(block)
    }

    public inline fun <T : Any> gather(
        valuesColumnName: String,
        keysColumnName: String,
        firstColumn: Pair<Iterable<T>, String>,
        secondColumn: Pair<Iterable<T>, String>,
        vararg columnPointers: Pair<Iterable<T>, String>,
        block: GatheredMutableDataContext<T>.() -> Unit
    ) {
        val fC = firstColumn.first.toColumnPointer(firstColumn.second)
        val sC = secondColumn.first.toColumnPointer(secondColumn.second)
        val others = columnPointers.map { it.first.toColumnPointer(it.second) }
        GatheredMutableDataContext<T>(
            this,
            dataBuffer.toTableData().gather(
                valuesColumnName,
                keysColumnName,
                fC,
                sC,
                *others.toTypedArray(),
            ).toMutableNamedData(),
            valuesColumnName,
            keysColumnName,
            others.size + 2,
        ).apply(block)
    }

    public inline fun <T : Any> gatherAndGroup(
        valuesColumnName: String,
        keysColumnName: String,
        firstColumn: Pair<Iterable<T>, String>,
        secondColumn: Pair<Iterable<T>, String>,
        vararg columnPointers: Pair<Iterable<T>, String>,
        block: GatheredMutableDataContext<T>.() -> Unit
    ) {
        //todo
        val fC = firstColumn.first.toColumnPointer(firstColumn.second)
        val sC = secondColumn.first.toColumnPointer(secondColumn.second)
        val others = columnPointers.map { it.first.toColumnPointer(it.second) }
        GatheredMutableDataContext<T>(
            this,
            dataBuffer.toTableData().gather(
                valuesColumnName,
                keysColumnName,
                fC,
                sC,
                *others.toTypedArray(),
            ).toMutableNamedData().groupBy(listOf(keysColumnName)),
            valuesColumnName,
            keysColumnName,
            others.size + 2,
        ).apply(block)
    }

    public inline fun <T : Any> gatherAndGroup(
        firstColumn: Pair<Iterable<T>, String>,
        secondColumn: Pair<Iterable<T>, String>,
        vararg columnPointers: Pair<Iterable<T>, String>,
        block: GatheredMutableDataContext<T>.() -> Unit
    ): Unit = gatherAndGroup("valuesColumnName", "keysColumnName", firstColumn, secondColumn, columnPointers= columnPointers, block)
}

public inline fun plot(block: PlotMutableDataContext.() -> Unit): Plot {
    return PlotMutableDataContext().apply(block).toPlot()
}
