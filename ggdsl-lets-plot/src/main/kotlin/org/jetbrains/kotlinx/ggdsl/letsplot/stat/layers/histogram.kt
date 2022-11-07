package org.jetbrains.kotlinx.ggdsl.letsplot.stat.layers

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BAR
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BarContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinStatContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.statBin
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.invoke

public class HistogramContext(parent: LayerCollectorContext)
    : BarContextInterface, BinStatContext, LayerContext(parent)

//todo type
public inline fun LayerCollectorContext.histogram(
    sample: ColumnPointer<*>,
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
