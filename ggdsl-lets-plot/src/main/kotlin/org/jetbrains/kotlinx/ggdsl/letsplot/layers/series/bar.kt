package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BAR
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BarContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public interface BarGatheringContextInterface : BarContextInterface, GatheringContextInterface {
    override val geom: Geom
        get() = BAR
}

public interface BarGatheringContextInterfaceImmutable : BarGatheringContextInterface
public interface BarGatheringContextInterfaceMutable : BarGatheringContextInterface

public class BarSeriesContextImmutable(parent: TableDataContext)
    : SeriesWithBorderLineContextImmutable(parent), BarContextInterface

public inline fun BarGatheringContextInterfaceImmutable.series(
    label: String,
    block: BarSeriesContextImmutable.() -> Unit
) {
    seriesCollector.add(BarSeriesContextImmutable(this).apply(block).toSeries(label))
}

public class BarGatheringContextImmutable(parent: NamedDataPlotContext, position: Position) :
    GatheringWithBorderLineContextImmutable(parent, position), BarGatheringContextInterfaceImmutable

public class BarSeriesPlotContext(
    override val data: NamedDataInterface,
    override val position: Position
) : SeriesPlotWithBorderLineContext(data, position), BarGatheringContextInterfaceImmutable

public class BarGatheringContextMutable(parent: TableBindingContextInterfaceMutable, position: Position) :
    GatheringWithBorderLineContextMutable(parent, position), BarGatheringContextInterfaceMutable {
    public fun series(label: String, block: BarSeriesContextMutable.() -> Unit) {
        seriesCollector.add(BarSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public class BarSeriesPlotContextMutable(
    position: Position,
) : SeriesPlotContextWithBorderLineMutable(position), BarGatheringContextInterface {
    public fun series(label: String, block: BarSeriesContextMutable.() -> Unit) {
        seriesCollector.add(BarSeriesContextMutable(this).apply(block).toSeries(label))
    }
}

public open class BarSeriesContextMutable(parent: TableBindingContextInterfaceMutable) :
    SeriesWithBorderLineContextImmutable(parent), BarContextInterface

public inline fun barPlot(
    dataset: NamedDataInterface,
    position: Position = Position.Identity,
    block: BarSeriesPlotContext.() -> Unit,
): Plot {
    return BarSeriesPlotContext(dataset, position).apply(block).toPlot()
}

public inline fun barPlot(
    position: Position = Position.Identity,
    block: BarSeriesPlotContextMutable.() -> Unit,
): Plot {
    return BarSeriesPlotContextMutable(position).apply(block).toPlot()
}

public inline fun NamedDataPlotContext.barGather(
    position: Position = Position.Identity,
    block: BarGatheringContextImmutable.() -> Unit
) {
    addGathering(
        BarGatheringContextImmutable(this, position).apply(block).toGathering()
    )
}

public inline fun PlotContextMutable.barGather(
    position: Position = Position.Identity,
    block: BarGatheringContextMutable.() -> Unit
) {
    addGathering(
        BarGatheringContextMutable(this, position).apply(block).toGathering()
    )
}
