package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.*
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

public inline fun LineGatheringContextInterface.series(label: String, block: LineSeriesContext.() -> Unit) {
    seriesCollector.add(LineSeriesContext(this).apply(block).toSeries(label))
}

public class LineGatheringContext(parent: NamedDataPlotContext<*>, position: Position) :
    GatheringContextBase(parent, position), LineGatheringContextInterface

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

public class LineSeriesContext(parent: TableBindingContext) : SeriesContext(parent), LineContextInterface

public class LineGatheringMutableContext(parent: MutableDataBindingContextInterface, position: Position) :
    GatheringMutableContextBase(parent, position), LineGatheringContextInterface {
    public inline fun series(
        label: String, block: LineSeriesMutableContext.() -> Unit
    ) {
        seriesCollector.add(LineSeriesMutableContext(this).apply(block).toSeries(label))
    }
}

public class LineGatheringPlotMutableContext(
    position: Position,
) : GatheringPlotMutableContextBase(position) {
    public fun series(label: String, block: LineSeriesMutableContext.() -> Unit) {
        seriesCollector.add(LineSeriesMutableContext(this).apply(block).toSeries(label))
    }
}

public abstract class SeriesMutableContextBase(parent: MutableDataBindingContextInterface) :
    SubMutableDataContext(parent, false) {
    public fun toSeries(label: String): Series {
        return Series(
            bindingCollector.mappings,
            bindingCollector.settings,
            label
        )
    }
}

public class LineSeriesMutableContext(parent: MutableDataBindingContextInterface) :
    SeriesMutableContextBase(parent), LineContextInterface


public inline fun linePlot(
    position: Position = Position.Identity,
    block: LineGatheringPlotMutableContext.() -> Unit,
): Plot {
    return LineGatheringPlotMutableContext(position).apply(block).toPlot()
}

public fun PlotContext.addGathering(gathering: Gathering) {
    (features.getOrPut(GatheringList.FEATURE_NAME) {
        GatheringList()
    } as GatheringList).gatheringList.add(gathering)
}


public inline fun NamedDataPlotContext<*>.lineGather(
    position: Position = Position.Identity,
    block: LineGatheringContext.() -> Unit
) {
    addGathering(
        LineGatheringContext(this, position).apply(block).toGathering()
    )
}


public inline fun PlotMutableDataContext.lineGather(
    position: Position = Position.Identity,
    block: LineGatheringMutableContext.() -> Unit
) {
    addGathering(
        LineGatheringMutableContext(this, position).apply(block).toGathering()
    )
}
/*
@Suppress("UNCHECKED_CAST")
public inline fun< reified T: GatherContext> PlotMutableDataContext.gather(
    position: Position = Position.Identity,
    noinline block: T.() -> Unit
) {
    //todo
    when(typeOf<T>()) {
        typeOf<LineGatheringMutableContext>() -> lineGather(position, block as (LineGatheringMutableContext.() -> Unit))
    }
}

 */