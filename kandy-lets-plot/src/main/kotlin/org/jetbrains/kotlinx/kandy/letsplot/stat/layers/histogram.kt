/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.stat.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.toColumnOf
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.BAR
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BarsContext
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.BinStatContext
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.statBin

// TODO!!!

public class HistogramContext(parent: LayerCollectorContext) : BarsContext(parent), BinStatContext


//todo type
public inline fun LayerCollectorContext.histogram(
    column: ColumnReference<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(column, bins, binXPos) {
        addLayer(HistogramContext(this).apply {
            x(Stat.BINS)
            y(Stat.COUNT)
        }.apply(block), BAR)
    }
}

public inline fun LayerCollectorContext.histogram(
    columnName: String,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(columnName.toColumnOf<Any>(), bins, binXPos) {
        addLayer(HistogramContext(this).apply {
            x(Stat.BINS)
            y(Stat.COUNT)
        }.apply(block), BAR)
    }
}

public inline fun <reified T : Any> LayerCollectorContext.histogram(
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

public inline fun LayerCollectorContext.histogram(
    column: DataColumn<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(column, bins, binXPos) {
        addLayer(HistogramContext(this).apply {
            x(Stat.BINS)
            y(Stat.COUNT)
        }.apply(block), BAR)
    }
}


