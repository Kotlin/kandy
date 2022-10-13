package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.stat

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColumnPointer
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinLayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.statBin

public inline fun<T:Any> LayerCollectorContext.statBin(
    column: ColumnReference<T>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
): Unit = this@statBin.statBin(column.toColumnPointer(), bins, binXPos, block)

