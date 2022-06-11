package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

import org.jetbrains.kotlinx.ggdsl.ir.scale.guide.Legend
import org.jetbrains.kotlinx.ggdsl.util.color.Color

sealed interface LegendType

class None internal constructor(): LegendType

data class DiscreteLegend internal constructor(
    val nRow: Int? = null,
    val nCol: Int? = null,
    val byRow: Boolean? = null
): LegendType

data class ColorBar internal constructor(
    val barWidth: Double? = null,
    val barHeight: Double? = null,
    val nBin: Int? = null
): LegendType

class LetsPlotLegend<DomainType: Any, RangeType: Any>: Legend {
    var name: String? = null
    var breaks: List<DomainType>? = null
    var labels: List<String>? = null // todo pair list and format
    // todo expand & trans
    var legendType: LegendType? = null
}

fun LetsPlotLegend<*, *>.none() = None()

fun LetsPlotLegend<*,*>.discreteLegend(
    nRow: Int? = null,
    nCol: Int? = null,
    byRow: Boolean? = null
) = DiscreteLegend(nRow, nCol, byRow)

fun LetsPlotLegend<*,Color>.colorBar(
    barWidth: Double? = null,
    barHeight: Double? = null,
    nBin: Int? = null
) = ColorBar(barWidth, barHeight, nBin)
/*
inline fun<DomainType : Any, RangeType : Any> NonPositionalScaleContext<DomainType, RangeType>.
        legend(block: LetsPlotLegend<DomainType, RangeType>.() -> Unit) {
    legend = LetsPlotLegend<DomainType, RangeType>().apply(block)
}

 */

