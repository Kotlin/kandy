package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.StatDSLMarker
import org.jetbrains.kotlinx.ggdsl.ir.data.*
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.StatContext

@StatDSLMarker
class BinContext(parent: LayerCollectorContext, override val data: TableData): StatContext(parent) {
    val Stat = StatHolder

    object StatHolder {
        val BINS = BinStatistic.Bins
        val COUNT = BinStatistic.Count
        val DENSITY = BinStatistic.Density
    }
}

sealed interface Bins {
    data class ByNumber internal constructor(val number: Int) : Bins
    data class ByWidth internal constructor(val width: Double) : Bins

    companion object {
        fun byNumber(number: Int) = ByNumber(number)
        fun byWidth(width: Double) = ByWidth(width)
    }
}

sealed interface BinXPos {
    val posValue: Double

    data class Center internal constructor(override val posValue: Double) : BinXPos
    data class Boundary internal constructor(override val posValue: Double) : BinXPos
    data class None internal constructor(override val posValue: Double) : BinXPos

    companion object {
        fun none(posValue: Double) = None(posValue)
        fun center(posValue: Double) = Center(posValue)
        fun boundary(posValue: Double) = Boundary(posValue)
    }
}

inline fun LayerCollectorContext.countBin(
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
