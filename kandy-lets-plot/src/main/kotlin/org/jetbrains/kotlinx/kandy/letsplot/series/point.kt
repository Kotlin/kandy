/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.series
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContextMutable
import org.jetbrains.kotlinx.kandy.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.kandy.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.layers.POINT
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.PointsContext
import org.jetbrains.kotlinx.kandy.letsplot.position.Position

public interface PointGatheringContextInterface : PointsContext, GatheringContextInterface {
    override val geom: Geom
        get() = POINT
}

public interface PointGatheringContextInterfaceImmutable : PointGatheringContextInterface
public interface PointGatheringContextInterfaceMutable : PointGatheringContextInterface

public class PointSeriesContextImmutable(parent: TableDataContext)
    : SeriesWithBorderLineContextImmutable(parent), PointsContext

public inline fun PointGatheringContextInterfaceImmutable.series(
    label: String,
    block: PointSeriesContextImmutable.() -> Unit
) {
    seriesCollector.add(PointSeriesContextImmutable(this).apply(block).toSeries(label))
}

public class PointGatheringContextImmutable(parent: NamedDataPlotContext, position: Position) :
    GatheringWithBorderLineContextImmutable(parent, position), PointGatheringContextInterfaceImmutable

public class PointSeriesPlotContext(
    override val data: NamedData,
    override val position: Position
) : SeriesPlotWithBorderLineContext(data, position), PointGatheringContextInterfaceImmutable

public class PointGatheringContextMutable(parent: TableBindingContextInterfaceMutable, position: Position) :
    GatheringWithBorderLineContextMutable(parent, position), PointGatheringContextInterfaceMutable {
    public fun series(label: String, block: PointSeriesContextMutable.() -> Unit) {
        seriesCollector.add(PointSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public class PointSeriesPlotContextMutable(
    position: Position,
) : SeriesPlotContextWithBorderLineMutable(position), PointGatheringContextInterface {
    public fun series(label: String, block: PointSeriesContextMutable.() -> Unit) {
        seriesCollector.add(PointSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public open class PointSeriesContextMutable(parent: TableBindingContextInterfaceMutable) :
    SeriesWithBorderLineContextImmutable(parent), PointsContext

public inline fun pointPlot(
    dataset: NamedData,
    position: Position = Position.Identity,
    block: PointSeriesPlotContext.() -> Unit,
): Plot {
    return PointSeriesPlotContext(dataset, position).apply(block).toPlot()
}

public inline fun pointPlot(
    position: Position = Position.Identity,
    block: PointSeriesPlotContextMutable.() -> Unit,
): Plot {
    return PointSeriesPlotContextMutable(position).apply(block).toPlot()
}

public inline fun NamedDataPlotContext.pointGather(
    position: Position = Position.Identity,
    block: PointGatheringContextImmutable.() -> Unit
) {
    addGathering(
        PointGatheringContextImmutable(this, position).apply(block).toGathering()
    )
}

public inline fun PlotContextMutable.pointGather(
    position: Position = Position.Identity,
    block: PointGatheringContextMutable.() -> Unit
) {
    addGathering(
        PointGatheringContextMutable(this, position).apply(block).toGathering()
    )
}


 */