/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.stat.bin

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.values
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.letsplot.stat.Statistic

public interface BinStatContext {
    public val Stat: StatHolder
        get() = StatHolder

    public object StatHolder {
        public val BINS: Statistic<Double> = column("STAT_BINS")
        public val COUNT: Statistic<Double> = column("STAT_COUNT")
        public val DENSITY: Statistic<Double> = column("STAT_DENSITY")
    }
}

// TODO

//@StatDSLMarker
public class BinLayerCollectorContext(parent: LayerCollectorContext, override val datasetIndex: Int) :
    LayerCollectorContext, BinStatContext {
    override val layers: MutableList<Layer> = parent.layers
    override val plotContext: PlotContext = parent.plotContext
}

public sealed interface Bins {
    public data class ByNumber internal constructor(val number: Int) : Bins
    public data class ByWidth internal constructor(val width: Double) : Bins

    public companion object {
        public fun byNumber(number: Int): ByNumber = ByNumber(number)
        public fun byWidth(width: Double): ByWidth = ByWidth(width)
    }
}

public sealed interface BinXPos {
    public val posValue: Double

    public data class Center internal constructor(override val posValue: Double) : BinXPos
    public data class Boundary internal constructor(override val posValue: Double) : BinXPos
    public data class None internal constructor(override val posValue: Double) : BinXPos

    public companion object {
        public fun none(posValue: Double): None = None(posValue)
        public fun center(posValue: Double): Center = Center(posValue)
        public fun boundary(posValue: Double): Boundary = Boundary(posValue)
    }
}

//todo type
/**
 * Counts `bin` statistics by given column. Creates [BinLayerCollectorContext].
 * In this context a dataset with calculated stats is used.
 * It also has properties - references to columns with these stats,
 * which can be used in mappings etc.
 *
 * Countable statistics:
 *  * [BinStatContext.StatHolder.BINS]
 *  * [BinStatContext.StatHolder.COUNT]
 *  * [BinStatContext.StatHolder.DENSITY]
 *
 *  @param column column for which the statistics are counted.
 *  @param bins [Bins] count parameter.Determines the parameter by which the bins will be calculated
 *  - either their number or their width.
 *  @param binXPos bins position adjustment.
 */
public inline fun LayerCollectorContext.statBin(
    column: ColumnReference<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    val newData = when (val data = datasetHandler.initialDataset) {
        is NamedData -> countBinsImpl(data, column, bins, binXPos)
        is GroupedData -> countBinsImpl(data, column, bins, binXPos)
    }
    plotContext.datasetHandlers.add(DatasetHandler(newData, true))
    BinLayerCollectorContext(this, plotContext.datasetHandlers.size - 1).apply(block)
}

/**
 * Counts `bin` statistics by given column. Creates [BinLayerCollectorContext].
 * In this context a dataset with calculated stats is used.
 * It also has properties - references to columns with these stats,
 * which can be used in mappings etc.
 *
 * Countable statistics:
 *  * [BinStatContext.StatHolder.BINS]
 *  * [BinStatContext.StatHolder.COUNT]
 *  * [BinStatContext.StatHolder.DENSITY]
 *
 *  @param column name of column for which the statistics are counted.
 *  @param bins [Bins] count parameter.Determines the parameter by which the bins will be calculated
 *  - either their number or their width.
 *  @param binXPos bins position adjustment.
 */
public inline fun LayerCollectorContext.statBin(
    column: String,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    statBin(column<Any>(column), bins, binXPos, block)
}

/**
 * Counts `bin` statistics for given values. Creates [BinLayerCollectorContext].
 * In this context a dataset with calculated stats is used.
 * It also has properties - references to columns with these stats,
 * which can be used in mappings etc.
 *
 * Countable statistics:
 *  * [BinStatContext.StatHolder.BINS]
 *  * [BinStatContext.StatHolder.COUNT]
 *  * [BinStatContext.StatHolder.DENSITY]
 *
 *  @param values values for which the statistics are counted.
 *  @param bins [Bins] count parameter.Determines the parameter by which the bins will be calculated
 *  - either their number or their width.
 *  @param binXPos bins position adjustment.
 */
public inline fun LayerCollectorContext.statBin(
    values: Iterable<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    val newData =
        countBinsImpl(NamedData(dataFrameOf("sample" to values.toList())), column<Double>("sample"), bins, binXPos)
    plotContext.datasetHandlers.add(DatasetHandler(newData, true))
    BinLayerCollectorContext(this, plotContext.datasetHandlers.size - 1).apply(block)
}

/**
 * Counts `bin` statistics by given column. Creates [BinLayerCollectorContext].
 * In this context a dataset with calculated stats is used.
 * It also has properties - references to columns with these stats,
 * which can be used in mappings etc.
 *
 * Countable statistics:
 *  * [BinStatContext.StatHolder.BINS]
 *  * [BinStatContext.StatHolder.COUNT]
 *  * [BinStatContext.StatHolder.DENSITY]
 *
 *  @param column column for which the statistics are counted.
 *  @param bins [Bins] count parameter.Determines the parameter by which the bins will be calculated
 *  - either their number or their width.
 *  @param binXPos bins position adjustment.
 */
public inline fun LayerCollectorContext.statBin(
    column: DataColumn<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    val initialDF = datasetHandler.initialNamedData.dataFrame
    if (initialDF.containsColumn(column.name()) && initialDF[column.name()] == column) {
        statBin(column.name(), bins, binXPos, block)
    } else {
        statBin(column.values, bins, binXPos, block)
    }
}
