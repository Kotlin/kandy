package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.MutableDataBindingContextInterface
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotMutableDataContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.TableBindingContext
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

public inline fun PointGatheringContextInterface.series(label: String, block: PointSeriesContext.() -> Unit) {
    seriesCollector.add(PointSeriesContext(this).apply(block).toSeries(label))
}

public class PointGatheringContext(parent: NamedDataPlotContext<*>, position: Position) :
    GatheringContextBase(parent, position), PointGatheringContextInterface

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

public class PointSeriesContext(parent: TableBindingContext) : SeriesContext(parent), PointsContextInterface

public class PointGatheringMutableContext(parent: MutableDataBindingContextInterface, position: Position) :
    GatheringMutableContextBase(parent, position), PointGatheringContextInterface {
    public inline fun series(
        label: String, block: PointSeriesMutableContext.() -> Unit
    ) {
        seriesCollector.add(PointSeriesMutableContext(this).apply(block).toSeries(label))
    }
}

public class PointGatheringPlotMutableContext(
    position: Position,
) : GatheringPlotMutableContextBase(position) {
    public fun series(label: String, block: PointSeriesMutableContext.() -> Unit) {
        seriesCollector.add(PointSeriesMutableContext(this).apply(block).toSeries(label))
    }
}


public class PointSeriesMutableContext(parent: MutableDataBindingContextInterface) :
    SeriesMutableContextBase(parent), PointsContextInterface


public inline fun PointPlot(
    position: Position = Position.Identity,
    block: PointGatheringPlotMutableContext.() -> Unit,
): Plot {
    return PointGatheringPlotMutableContext(position).apply(block).toPlot()
}


public inline fun NamedDataPlotContext<*>.pointGather(
    position: Position = Position.Identity,
    block: PointGatheringContext.() -> Unit
) {
    addGathering(
        PointGatheringContext(this, position).apply(block).toGathering()
    )
}


public inline fun PlotMutableDataContext.pointGather(
    position: Position = Position.Identity,
    block: PointGatheringMutableContext.() -> Unit
) {
    addGathering(
        PointGatheringMutableContext(this, position).apply(block).toGathering()
    )
}
