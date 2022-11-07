package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.stat

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColumnPointer
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinLayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.statBin
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.layers.HistogramContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.layers.histogram

public inline fun LayerCollectorContext.statBin(
    column: ColumnReference<Any>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
): Unit = statBin(column.toColumnPointer(), bins, binXPos, block)

//todo type
public inline fun LayerCollectorContext.histogram(
    sample: ColumnReference<Any>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    histogram(sample.toColumnPointer(), bins, binXPos, block)
}

