package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.stat

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.internal.toColumnPointer
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.validateColumn
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinLayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.statBin
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.layers.HistogramContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.layers.histogram

public inline fun LayerCollectorContextImmutable.statBin(
    column: ColumnReference<Any>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    data.validateColumn(column.name())
    statBin(column.toColumnPointer(), bins, binXPos, block)
}
//todo type
public inline fun LayerCollectorContextImmutable.histogram(
    column: ColumnReference<Any>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContextImmutable.() -> Unit = {}
) {
    data.validateColumn(column.name())
    histogram(column.toColumnPointer(), bins, binXPos, block)
}
