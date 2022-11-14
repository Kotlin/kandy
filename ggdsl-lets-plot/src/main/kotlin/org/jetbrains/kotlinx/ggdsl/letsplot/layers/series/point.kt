package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.POINT
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.PointsContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public interface PointGatheringContextInterface :
    PointsContextInterface, GatheringContextInterface {
    override val geom: Geom
        get() = POINT
}

public inline fun PointGatheringContextInterface.series(label: String, block: PointSeriesContextImmutable.() -> Unit) {
    seriesCollector.add(PointSeriesContextImmutable(this).apply(block).toSeries(label))
}

public class PointGatheringContextImmutable(parent: NamedDataPlotContext, position: Position) :
    GatheringContextBaseImmutable(parent, position), PointGatheringContextInterface

public class PointSeriesPlotContext(
    override val data: NamedDataInterface,
    override val position: Position
) : SeriesPlotContextBase(), PointGatheringContextInterface

public inline fun pointPlot(
    dataset: NamedDataInterface,
    position: Position = Position.Identity,
    block: PointSeriesPlotContext.() -> Unit,
): Plot {
    return PointSeriesPlotContext(dataset, position).apply(block).toPlot()
}

public class PointSeriesContextImmutable(parent: TableDataContext) : SeriesContextImmutable(parent), PointsContextInterface

public class PointMutableGatheringSubContext(parent: TableBindingContextInterfaceMutable, position: Position) :
    GatheringSubContextMutable(parent, position), PointGatheringContextInterface {
    public inline fun series(
        label: String, block: PointMutableSeriesContextMutable.() -> Unit
    ) {
        seriesCollector.add(PointMutableSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public class PointMutableMutableSeriesPlotContext(
    position: Position,
) : SeriesPlotContextMutable(position), PointGatheringContextInterface {
    public fun series(label: String, block: PointMutableSeriesContextMutable.() -> Unit) {
        seriesCollector.add(PointMutableSeriesContextMutable(this).apply(block).toSeries(label))
    }
}


public class PointMutableSeriesContextMutable(parent: TableBindingContextInterfaceMutable) :
    SeriesContextMutable(parent), PointsContextInterface


public inline fun pointPlot(
    position: Position = Position.Identity,
    block: PointMutableMutableSeriesPlotContext.() -> Unit,
): Plot {
    return PointMutableMutableSeriesPlotContext(position).apply(block).toPlot()
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
    block: PointMutableGatheringSubContext.() -> Unit
) {
    addGathering(
        PointMutableGatheringSubContext(this, position).apply(block).toGathering()
    )
}
