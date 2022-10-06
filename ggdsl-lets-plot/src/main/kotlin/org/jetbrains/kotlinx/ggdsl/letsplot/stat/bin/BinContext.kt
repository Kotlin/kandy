package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.StatDSLMarker
import org.jetbrains.kotlinx.ggdsl.ir.data.*
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.StatContext

@StatDSLMarker
public class BinContext(parent: LayerCollectorContext, override val data: TableData): StatContext(parent) {
    public val Stat: StatHolder = StatHolder

    public object StatHolder {
        public val BINS: BinStatistic.Bins = BinStatistic.Bins
        public val COUNT: BinStatistic.Count = BinStatistic.Count
        public val DENSITY: BinStatistic.Density = BinStatistic.Density
    }
}

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

public inline fun LayerCollectorContext.statBin(
    column: ColumnPointer<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinContext.() -> Unit
){
    val newData = when(data) {
        is NamedDataInterface -> countBinsImpl(data as NamedDataInterface, column, bins, binXPos)
        is CountedGroupedDataInterface -> countBinsImpl((data as CountedGroupedDataInterface).toLazy(), column, bins, binXPos)
        is LazyGroupedDataInterface -> countBinsImpl(data as LazyGroupedDataInterface, column, bins, binXPos)
    }
    BinContext(this, newData).apply(block)
}
