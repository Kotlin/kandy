package org.jetbrains.kotlinx.kandy.letsplot.series
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContextMutable
import org.jetbrains.kotlinx.kandy.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.kandy.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.layers.LINE
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.LineContextInterface
import org.jetbrains.kotlinx.kandy.letsplot.position.Position

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