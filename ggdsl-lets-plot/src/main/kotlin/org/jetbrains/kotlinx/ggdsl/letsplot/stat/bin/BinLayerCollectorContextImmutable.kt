package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.values
import org.jetbrains.kotlinx.ggdsl.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.Statistic

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
public class BinLayerCollectorContext(parent: LayerCollectorContext, override val datasetIndex: Int)
    : LayerCollectorContext, BinStatContext {
    override val layers: MutableList<Layer> = parent.layers
    override val plotContext: PlotContext = parent.plotContext
}

/*
public class BinLayerCollectorContextMutable(parent: LayerCollectorContextMutable, override val data: TableData)
    : StatLayerCollectorContext(parent), BinStatContext{
    // TODO
    override val bindingCollector: BindingCollector = BindingCollector()
}

 */



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
/*
@PublishedApi
internal fun countData(
    data: TableData,
    column: ColumnReference<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
): TableData {
    return when(data) {
        is NamedDataInterface -> countBinsImpl(data, column, bins, binXPos)
        is CountedGroupedDataInterface -> countBinsImpl(data.toLazy(), column, bins, binXPos)
        is LazyGroupedDataInterface -> countBinsImpl(data, column, bins, binXPos)
    }
}

 */

/*
@PublishedApi
internal inline fun LayerCollectorContext.statBinImpl(
    data: TableData,
    column: ColumnReference<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
){
    val newData = when(data) {
        is NamedData -> countBinsImpl(data, column, bins, binXPos)
        is GroupedData -> countBinsImpl(data, column, bins, binXPos)
    }
    plotContext.datasetHandlers.add(DatasetHandler(newData))
    BinLayerCollectorContext(this, plotContext.datasetHandlers.size-1).apply(block)
}
*/



//todo type
public inline fun LayerCollectorContext.statBin(
    column: ColumnReference<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    val newData = when(val data = datasetHandler.initialDataset) {
        is NamedData -> countBinsImpl(data, column, bins, binXPos)
        is GroupedData -> countBinsImpl(data, column, bins, binXPos)
    }
    plotContext.datasetHandlers.add(DatasetHandler(newData, true))
    BinLayerCollectorContext(this, plotContext.datasetHandlers.size-1).apply(block)
}

public inline fun LayerCollectorContext.statBin(
    column: String,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    statBin(column<Any>(column), bins, binXPos, block)
}

public inline fun LayerCollectorContext.statBin(
    values: Iterable<*>,
    bins: Bins = Bins.byNumber(20),
    binXPos: BinXPos = BinXPos.none(0.0),
    block: BinLayerCollectorContext.() -> Unit
) {
    val newData = countBinsImpl(NamedData(dataFrameOf("sample" to values.toList())), column<Double>("sample"), bins, binXPos)
    plotContext.datasetHandlers.add(DatasetHandler(newData, true))
    BinLayerCollectorContext(this, plotContext.datasetHandlers.size-1).apply(block)
}

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
