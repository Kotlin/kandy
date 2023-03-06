package org.jetbrains.kotlinx.ggdsl.letsplot.series
/*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LINE
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.LineContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public interface LineGatheringContextInterface : LineContextInterface, GatheringContextInterface {
    override val geom: Geom
        get() = LINE
}

public interface LineGatheringContextInterfaceImmutable : LineGatheringContextInterface
public interface LineGatheringContextInterfaceMutable : LineGatheringContextInterface

public class LineSeriesContextImmutable(parent: TableDataContext)
    : SeriesWithBorderLineContextImmutable(parent), LineContextInterface

public inline fun LineGatheringContextInterfaceImmutable.series(
    label: String,
    block: LineSeriesContextImmutable.() -> Unit
) {
    seriesCollector.add(LineSeriesContextImmutable(this).apply(block).toSeries(label))
}

public class LineGatheringContextImmutable(parent: NamedDataPlotContext, position: Position) :
    GatheringWithBorderLineContextImmutable(parent, position), LineGatheringContextInterfaceImmutable

public class LineSeriesPlotContext(
    override val data: NamedData,
    override val position: Position
) : SeriesPlotWithBorderLineContext(data, position), LineGatheringContextInterfaceImmutable

public class LineGatheringContextMutable(parent: TableBindingContextInterfaceMutable, position: Position) :
    GatheringWithBorderLineContextMutable(parent, position), LineGatheringContextInterfaceMutable {
    public fun series(label: String, block: LineSeriesContextMutable.() -> Unit) {
        seriesCollector.add(LineSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public class LineSeriesPlotContextMutable(
    position: Position,
) : SeriesPlotContextWithBorderLineMutable(position), LineGatheringContextInterface {
    public fun series(label: String, block: LineSeriesContextMutable.() -> Unit) {
        seriesCollector.add(LineSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public open class LineSeriesContextMutable(parent: TableBindingContextInterfaceMutable) :
    SeriesWithBorderLineContextImmutable(parent), LineContextInterface

public inline fun linePlot(
    dataset: NamedData,
    position: Position = Position.Identity,
    block: LineSeriesPlotContext.() -> Unit,
): Plot {
    return LineSeriesPlotContext(dataset, position).apply(block).toPlot()
}

public inline fun linePlot(
    position: Position = Position.Identity,
    block: LineSeriesPlotContextMutable.() -> Unit,
): Plot {
    return LineSeriesPlotContextMutable(position).apply(block).toPlot()
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
    block: LineGatheringContextMutable.() -> Unit
) {
    addGathering(
        LineGatheringContextMutable(this, position).apply(block).toGathering()
    )
}

 */