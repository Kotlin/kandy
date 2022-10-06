package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.stat

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinContext
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.statBin

public inline fun<T:Any> LayerCollectorContext.statBin(
    column: ColumnReference<T>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinContext.() -> Unit
): Unit = this@statBin.statBin(column.toColRef(), bins, binXPos, block)
