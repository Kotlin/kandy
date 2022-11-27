package org.jetbrains.kotlinx.ggdsl.letsplot.stat.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BAR
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BarContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinStatContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.statBin
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.invoke

// TODO!!!

public interface HistogramContextInterface: BarContextInterface, BinStatContext, LayerContextInterface

public class HistogramContextImmutable(parent: LayerCollectorContextImmutable) :
    BarContextImmutable(parent), HistogramContextInterface
/*
//todo
public class HistogramContextMutable(parent: LayerCollectorContextMutable) :
    BarContextMutable(parent), HistogramContextInterface

 */

//todo type
public inline fun LayerCollectorContextImmutable.histogram(
    sample: ColumnPointer<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContextImmutable.() -> Unit = {}
) {
    statBin(sample, bins, binXPos) {
        addLayer(HistogramContextImmutable(this).apply {
            x(Stat.BINS)
            y(Stat.COUNT)
        }.apply(block), BAR)
    }
}

public inline fun <T:Any> LayerCollectorContextMutable.histogram(
    sample: Iterable<T>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContextImmutable.() -> Unit = {}
) {
    statBin(sample, bins, binXPos) {
        addLayer(HistogramContextImmutable(this).apply {
            x(Stat.BINS)
            y(Stat.COUNT)
        }.apply(block), BAR)
    }
}

