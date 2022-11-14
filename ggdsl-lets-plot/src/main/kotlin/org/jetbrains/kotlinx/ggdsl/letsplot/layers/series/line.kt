package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LINE
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LineContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public interface LineGatheringContextInterface :
    LineContextInterface, GatheringContextInterface {
    override val geom: Geom
        get() = LINE
}

public inline fun LineGatheringContextInterface.series(label: String, block: LineSeriesContextImmutable.() -> Unit) {
    seriesCollector.add(LineSeriesContextImmutable(this).apply(block).toSeries(label))
}

public class LineGatheringContextImmutable(parent: NamedDataPlotContext, position: Position) :
    GatheringContextBaseImmutable(parent, position), LineGatheringContextInterface

public class LineSeriesPlotContext(
    override val data: NamedDataInterface,
    override val position: Position
) : SeriesPlotContextBase(), LineGatheringContextInterface

public inline fun linePlot(
    dataset: NamedDataInterface,
    position: Position = Position.Identity,
    block: LineSeriesPlotContext.() -> Unit,
): Plot {
    return LineSeriesPlotContext(dataset, position).apply(block).toPlot()
}

public class LineSeriesContextImmutable(parent: TableDataContext) : SeriesContextImmutable(parent), LineContextInterface

public class LineMutableGatheringSubContext(parent: TableBindingContextInterfaceMutable, position: Position) :
    GatheringSubContextMutable(parent, position), LineGatheringContextInterface {
    public inline fun series(
        label: String, block: LineMutableSeriesContextMutable.() -> Unit
    ) {
        seriesCollector.add(LineMutableSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public class LineMutableMutableSeriesPlotContext(
    position: Position,
) : SeriesPlotContextMutable(position), LineGatheringContextInterface {
    public fun series(label: String, block: LineMutableSeriesContextMutable.() -> Unit) {
        seriesCollector.add(LineMutableSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public class LineMutableSeriesContextMutable(parent: TableBindingContextInterfaceMutable) :
    SeriesContextMutable(parent), LineContextInterface


public inline fun linePlot(
    position: Position = Position.Identity,
    block: LineMutableMutableSeriesPlotContext.() -> Unit,
): Plot {
    return LineMutableMutableSeriesPlotContext(position).apply(block).toPlot()
}


public inline fun NamedDataPlotContext.lineGather(
    position: Position = Position.Identity,
    block: LineGatheringContextImmutable.() -> Unit
) {
    addGathering(
        LineGatheringContextImmutable(this, position).apply(block).toGathering()
    )
}


public inline fun PlotContextMutable.lineGather(
    position: Position = Position.Identity,
    block: LineMutableGatheringSubContext.() -> Unit
) {
    addGathering(
        LineMutableGatheringSubContext(this, position).apply(block).toGathering()
    )
}
