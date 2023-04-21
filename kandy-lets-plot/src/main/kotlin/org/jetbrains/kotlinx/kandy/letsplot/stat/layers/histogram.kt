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
import org.jetbrains.kotlinx.kandy.letsplot.position.Position
import org.jetbrains.kotlinx.kandy.letsplot.position.position
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.*
import org.jetbrains.kotlinx.kandy.letsplot.stat.layers.context.HistogramContext

@PublishedApi
internal inline fun BinLayerCollectorContext.hist(block: HistogramContext.() -> Unit = {}){
    addLayer(HistogramContext(this).apply {
        x(Stat.BINS)
        y(Stat.COUNT)
        position = Position.Dodge()
    }.apply(block), BAR)
}

//todo type
/**
 * Builds histogram by given column. Creates [HistogramContext], combining [BinStatContext] and [BarsContext].
 * @param column column for which the histogram are counted.
 * @param bins [Bins] count parameter.Determines the parameter
 * by which the bins will be calculated - either their number or their width.
 * @param binXPos bins position adjustment.
 * @see [BinStatContext]
 * @see [BarsContext]
 */
public inline fun LayerCollectorContext.histogram(
    column: ColumnReference<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(column, bins, binXPos) {
        hist(block)
    }
}

/**
 * Builds histogram by given column. Creates [HistogramContext], combining [BinStatContext] and [BarsContext].
 * @param columnName name of column for which the histogram are counted.
 * @param bins [Bins] count parameter.Determines the parameter
 * by which the bins will be calculated - either their number or their width.
 * @param binXPos bins position adjustment.
 * @see [BinStatContext]
 * @see [BarsContext]
 */
public inline fun LayerCollectorContext.histogram(
    columnName: String,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(columnName.toColumnOf<Any>(), bins, binXPos) {
        hist(block)
    }
}

/**
 * Builds histogram of given values. Creates [HistogramContext], combining [BinStatContext] and [BarsContext].
 * @param values values for which the histogram are counted.
 * @param bins [Bins] count parameter.Determines the parameter
 * by which the bins will be calculated - either their number or their width.
 * @param binXPos bins position adjustment.
 * @see [BinStatContext]
 * @see [BarsContext]
 */
public inline fun <reified T : Any> LayerCollectorContext.histogram(
    values: Iterable<T>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(values, bins, binXPos) {
        hist(block)
    }
}

/**
 * Builds histogram by given column. Creates [HistogramContext], combining [BinStatContext] and [BarsContext].
 * @param column column for which the histogram are counted.
 * @param bins [Bins] count parameter.Determines the parameter
 * by which the bins will be calculated - either their number or their width.
 * @param binXPos bins position adjustment.
 * @see [BinStatContext]
 * @see [BarsContext]
 */
public inline fun LayerCollectorContext.histogram(
    column: DataColumn<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: HistogramContext.() -> Unit = {}
) {
    statBin(column, bins, binXPos) {
        hist(block)
    }
}
