package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.ir.data.*
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.StatLayerCollectorContextImmutable

public interface BinStatContext {
    public val Stat: StatHolder
        get() = StatHolder

    public object StatHolder {
        public val BINS: BinStatistic.Bins = BinStatistic.Bins
        public val COUNT: BinStatistic.Count = BinStatistic.Count
        public val DENSITY: BinStatistic.Density = BinStatistic.Density
    }
}

//@StatDSLMarker
public class BinLayerCollectorContextImmutable(parent: LayerCollectorContextImmutable, override val data: TableData)
    : StatLayerCollectorContextImmutable(parent), BinStatContext

/* TODO!!!
public class BinLayerCollectorContextMutable(parent: LayerCollectorContextMutable, override val data: TableData)
    : StatLayerCollectorContextMutable(parent), BinStatContext

 */

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
internal fun countData(
    data: TableData,
    column: ColumnPointer<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
): TableData {
    return when(data) {
        is NamedDataInterface -> countBinsImpl(data, column, bins, binXPos)
        is CountedGroupedDataInterface -> countBinsImpl(data.toLazy(), column, bins, binXPos)
        is LazyGroupedDataInterface -> countBinsImpl(data, column, bins, binXPos)
    }
}

@PublishedApi
internal inline fun statBinImpl(
    contextParent: LayerCollectorContextImmutable,
    data: TableData,
    column: ColumnPointer<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContextImmutable.() -> Unit
){
    val newData = when(data) {
        is NamedDataInterface -> countBinsImpl(data, column, bins, binXPos)
        is CountedGroupedDataInterface -> countBinsImpl(data.toLazy(), column, bins, binXPos)
        is LazyGroupedDataInterface -> countBinsImpl(data, column, bins, binXPos)
    }
    BinLayerCollectorContextImmutable(contextParent, newData).apply(block)
}
/*
@PublishedApi
internal inline fun statBinImpl(
    contextParent: LayerCollectorContextMutable,
    data: TableData,
    column: ColumnPointer<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContextMutable.() -> Unit
){
    val newData = when(data) {
        is NamedDataInterface -> countBinsImpl(data, column, bins, binXPos)
        is CountedGroupedDataInterface -> countBinsImpl(data.toLazy(), column, bins, binXPos)
        is LazyGroupedDataInterface -> countBinsImpl(data, column, bins, binXPos)
    }
    BinLayerCollectorContextMutable(contextParent, newData).apply(block)
}

 */

//todo type
public inline fun LayerCollectorContextImmutable.statBin(
    column: ColumnPointer<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContextImmutable.() -> Unit
): Unit = statBinImpl(this, data, column, bins, binXPos, block)

/*
public inline fun<T:Any> LayerCollectorContextMutable.statBin(
    source: Iterable<T>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContextMutable.() -> Unit
) {
    val columnPointer = source.toColumnPointer()
    statBinImpl(this, data, columnPointer, bins, binXPos, block)
}

 */
