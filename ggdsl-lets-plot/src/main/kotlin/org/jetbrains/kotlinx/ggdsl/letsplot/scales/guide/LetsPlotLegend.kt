package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.util.color.Color

sealed interface LegendType

class None internal constructor() : LegendType

data class DiscreteLegend internal constructor(
    val nRow: Int? = null,
    val nCol: Int? = null,
    val byRow: Boolean? = null
) : LegendType

data class ColorBar internal constructor(
    val barWidth: Double? = null,
    val barHeight: Double? = null,
    val nBin: Int? = null
) : LegendType

@PlotDslMarker
data class Legend<DomainType : Any, out RangeType : Any>(
    var name: String? = null,
    var breaks: List<DomainType>? = null,
    var labels: List<String>? = null, // todo pair list and format
    // todo expand & trans
    var type: LegendType? = null,
)

fun Legend<*, *>.none() = None()

fun Legend<*, *>.discreteLegend(
    nRow: Int? = null,
    nCol: Int? = null,
    byRow: Boolean? = null
) = DiscreteLegend(nRow, nCol, byRow)

fun Legend<*, Color>.colorBar(
    barWidth: Double? = null,
    barHeight: Double? = null,
    nBin: Int? = null
) = ColorBar(barWidth, barHeight, nBin)

inline operator fun <DomainType : Any, RangeType : Any>
        Legend<DomainType, RangeType>.invoke(block: Legend<DomainType, RangeType>.() -> Unit) {
    apply(block)
}
