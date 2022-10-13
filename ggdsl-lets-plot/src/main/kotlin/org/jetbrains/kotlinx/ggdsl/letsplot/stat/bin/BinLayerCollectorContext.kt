package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import org.jetbrains.kotlinx.ggdsl.dsl.StatDSLMarker
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContextInterface
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorMutableDataContext
import org.jetbrains.kotlinx.ggdsl.ir.data.*
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.StatLayerCollectorContext

public interface BinStatContext {
    public val Stat: StatHolder
        get() = StatHolder

    public object StatHolder {
        public val BINS: BinStatistic.Bins = BinStatistic.Bins
        public val COUNT: BinStatistic.Count = BinStatistic.Count
        public val DENSITY: BinStatistic.Density = BinStatistic.Density
    }
}

@StatDSLMarker
public class BinLayerCollectorContext(parent: LayerCollectorContextInterface, override val data: TableData)
    : StatLayerCollectorContext(parent), BinStatContext

public sealed interface Bins {
    public data class ByNumber internal constructor(val number: Int) : Bins
    public data class ByWidth internal constructor(val width: Double) : Bins

    public companion object {
        public fun byNumber(number: Int): ByNumber = ByNumber(number)
        public fun byWidth(width: Double): ByWidth = ByWidth(width)
    }
}

public sealed interface BinXPos {
    public val posValue: Double

    public data class Center internal constructor(override val posValue: Double) : BinXPos
    public data class Boundary internal constructor(override val posValue: Double) : BinXPos
    public data class None internal constructor(override val posValue: Double) : BinXPos

    public companion object {
        public fun none(posValue: Double): None = None(posValue)
        public fun center(posValue: Double): Center = Center(posValue)
        public fun boundary(posValue: Double): Boundary = Boundary(posValue)
    }
}

@PublishedApi
internal inline fun statBinImpl(
    contextParent: LayerCollectorContextInterface,
    data: TableData,
    column: ColumnPointer<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
){
    val newData = when(data) {
        is NamedDataInterface -> countBinsImpl(data, column, bins, binXPos)
        is CountedGroupedDataInterface -> countBinsImpl(data.toLazy(), column, bins, binXPos)
        is LazyGroupedDataInterface -> countBinsImpl(data, column, bins, binXPos)
    }
    BinLayerCollectorContext(contextParent, newData).apply(block)
}

//todo type
public inline fun LayerCollectorContext.statBin(
    column: ColumnPointer<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
): Unit = statBinImpl(this, data, column, bins, binXPos, block)

public inline fun<T:Any> LayerCollectorMutableDataContext.statBin(
    source: Iterable<T>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    val columnPointer = source.toColumnPointer()
    statBinImpl(this, data, columnPointer, bins, binXPos, block)
}
