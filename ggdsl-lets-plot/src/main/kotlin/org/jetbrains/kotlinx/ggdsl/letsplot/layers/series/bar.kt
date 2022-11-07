package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.MutableDataBindingContextInterface
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotMutableDataContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.TableBindingContext
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BAR
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BarContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public interface BarGatheringContextInterface :
    BarContextInterface, GatheringContextInterface {
    override val geom: Geom
        get() = BAR
}

public inline fun BarGatheringContextInterface.series(label: String, block: BarSeriesContext.() -> Unit) {
    seriesCollector.add(BarSeriesContext(this).apply(block).toSeries(label))
}

public class BarGatheringContext(parent: NamedDataPlotContext<*>, position: Position) :
    GatheringContextBase(parent, position), BarGatheringContextInterface

public class BarSeriesPlotContext(
    override val data: NamedDataInterface,
    override val position: Position
) : SeriesPlotContextBase(), BarGatheringContextInterface

public inline fun barPlot(
    dataset: NamedDataInterface,
    position: Position = Position.Identity,
    block: BarSeriesPlotContext.() -> Unit,
): Plot {
    return BarSeriesPlotContext(dataset, position).apply(block).toPlot()
}

public class BarSeriesContext(parent: TableBindingContext) : SeriesContext(parent), BarContextInterface

public class BarGatheringMutableContext(parent: MutableDataBindingContextInterface, position: Position) :
    GatheringMutableContextBase(parent, position), BarGatheringContextInterface {
    public inline fun series(
        label: String, block: BarSeriesMutableContext.() -> Unit
    ) {
        seriesCollector.add(BarSeriesMutableContext(this).apply(block).toSeries(label))
    }
}

public class BarGatheringPlotMutableContext(
    position: Position,
) : GatheringPlotMutableContextBase(position) {
    public fun series(label: String, block: BarSeriesMutableContext.() -> Unit) {
        seriesCollector.add(BarSeriesMutableContext(this).apply(block).toSeries(label))
    }
}


public class BarSeriesMutableContext(parent: MutableDataBindingContextInterface) :
    SeriesMutableContextBase(parent), BarContextInterface


public inline fun barPlot(
    position: Position = Position.Identity,
    block: BarGatheringPlotMutableContext.() -> Unit,
): Plot {
    return BarGatheringPlotMutableContext(position).apply(block).toPlot()
}


public inline fun NamedDataPlotContext<*>.barGather(
    position: Position = Position.Identity,
    block: BarGatheringContext.() -> Unit
) {
    addGathering(
        BarGatheringContext(this, position).apply(block).toGathering()
    )
}


public inline fun PlotMutableDataContext.barGather(
    position: Position = Position.Identity,
    block: BarGatheringMutableContext.() -> Unit
) {
    addGathering(
        BarGatheringMutableContext(this, position).apply(block).toGathering()
    )
}
