package org.jetbrains.kotlinx.ggdsl.letsplot.stat.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BAR
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.BarsContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinStatContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.statBin

// TODO!!!

public class HistogramContext(parent: LayerCollectorContext) : BarsContext(parent), BinStatContext


//todo type
public inline fun LayerCollectorContext.histogram(
    sample: ColumnReference<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(sample, bins, binXPos) {
        addLayer(HistogramContext(this).apply {
            x(Stat.BINS)
            y(Stat.COUNT)
        }.apply(block), BAR)
    }
}

public inline fun <reified T:Any> LayerCollectorContext.histogram(
    sample: Iterable<T>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(sample, bins, binXPos) {
        addLayer(HistogramContext(this).apply {
            x(Stat.BINS)
            y(Stat.COUNT)
        }.apply(block), BAR)
    }
}


