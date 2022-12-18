package org.jetbrains.kotlinx.ggdsl.letsplot.series

import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.POINT
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.PointContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public interface PointGatheringContextInterface : PointContextInterface, GatheringContextInterface {
    override val geom: Geom
        get() = POINT
}

public interface PointGatheringContextInterfaceImmutable : PointGatheringContextInterface
public interface PointGatheringContextInterfaceMutable : PointGatheringContextInterface

public class PointSeriesContextImmutable(parent: TableDataContext)
    : SeriesWithBorderLineContextImmutable(parent), PointContextInterface

public inline fun PointGatheringContextInterfaceImmutable.series(
    label: String,
    block: PointSeriesContextImmutable.() -> Unit
) {
    seriesCollector.add(PointSeriesContextImmutable(this).apply(block).toSeries(label))
}

public class PointGatheringContextImmutable(parent: NamedDataPlotContext, position: Position) :
    GatheringWithBorderLineContextImmutable(parent, position), PointGatheringContextInterfaceImmutable

public class PointSeriesPlotContext(
    override val data: NamedDataInterface,
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
    SeriesWithBorderLineContextImmutable(parent), PointContextInterface

public inline fun pointPlot(
    dataset: NamedDataInterface,
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
