package org.jetbrains.kotlinx.ggdsl.letsplot.layers.gather

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.*
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LINE
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LineContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import kotlin.reflect.typeOf

public interface LineGatheringContextInterface :
    LineContextInterface, GatherContext {
    override val geom: Geom
        get() = LINE
}

public class LineGatheringContext(parent: NamedDataPlotContext<*>, override val position: Position) :
    SubTableBindingContext(parent), LineGatheringContextInterface {
    override val data: NamedDataInterface = parent.data
    override val seriesCollector: MutableList<Series> = mutableListOf()

    public fun series(label: String, block: LineSeriesContext.() -> Unit) {
        seriesCollector.add(LineSeriesContext(this).apply(block).toSeries(label))
    }
}

public class LineGatheringPlotContext(
    override val data: NamedDataInterface,
    override val position: Position
) : PlotContextBase, LineGatheringContextInterface {
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override fun toPlot(): Plot {
        return Plot(
            data,
            listOf(),
            bindingCollector.mappings,
            features,
            bindingCollector.freeScales /*TODO free scales*/
        )
    }

    override val seriesCollector: MutableList<Series> = mutableListOf()
    override val bindingCollector: BindingCollector = BindingCollector()

    public fun series(label: String, block: LineSeriesContext.() -> Unit) {
        seriesCollector.add(LineSeriesContext(this).apply(block).toSeries(label))
    }

    @PublishedApi
    internal fun gather() {
        (features.getOrPut(GatheringList.FEATURE_NAME) {
            GatheringList()
        } as GatheringList).gatheringList.add(
            toGathering()
        )
    }
}

public inline fun linePlot(
    dataset: NamedDataInterface,
    position: Position = Position.Identity,
    block: LineGatheringPlotContext.() -> Unit,
): Plot {
    return LineGatheringPlotContext(dataset, position).apply(block).apply {
        gather()
    }.toPlot()
}


public class LineSeriesContext(parent: TableBindingContext) : SubTableBindingContext(parent), LineContextInterface {
    public fun toSeries(label: String): Series {
        return Series(
            bindingCollector.mappings,
            bindingCollector.settings,
            label
        )
    }
}

public class LineGatheringMutableContext(parent: MutableDataBindingContextInterface, override val position: Position) :
    SubMutableDataContext(parent), LineGatheringContextInterface {
    override val data: NamedDataInterface
        get() = super.data as NamedDataInterface
    override val seriesCollector: MutableList<Series> = mutableListOf()

    public fun series(label: String, block: LineSeriesMutableContext.() -> Unit) {
        seriesCollector.add(LineSeriesMutableContext(this).apply(block).toSeries(label))
    }
}

public class LineGatheringPlotMutableContext(
    override val position: Position,
) : PlotContextBase, LineGatheringContextInterface, MutableDataBindingContext() {
    override val multiplier: Int = 1
    override val dataBuffer: MutableTableData = MutableNamedData()
    override val data: NamedDataInterface get() = super.data as NamedDataInterface
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override fun toPlot(): Plot {
        return Plot(
            data,
            listOf(),
            bindingCollector.mappings,
            features,
            bindingCollector.freeScales /*TODO free scales*/
        )
    }

    override val seriesCollector: MutableList<Series> = mutableListOf()
    override val bindingCollector: BindingCollector = BindingCollector()

    public fun series(label: String, block: LineSeriesMutableContext.() -> Unit) {
        seriesCollector.add(LineSeriesMutableContext(this).apply(block).toSeries(label))
    }

    @PublishedApi
    internal fun gather() {
        (features.getOrPut(GatheringList.FEATURE_NAME) {
            GatheringList()
        } as GatheringList).gatheringList.add(
            toGathering()
        )
    }
}

public inline fun linePlot(
    position: Position = Position.Identity,
    block: LineGatheringPlotMutableContext.() -> Unit,
): Plot {
    return LineGatheringPlotMutableContext(position).apply(block).apply {
        gather()
    }.toPlot()
}

public class LineSeriesMutableContext(parent: MutableDataBindingContextInterface) :
    SubMutableDataContext(parent, false),
    LineContextInterface {
    public fun toSeries(label: String): Series {
        return Series(
            bindingCollector.mappings,
            bindingCollector.settings,
            label
        )
    }
}

public inline fun NamedDataPlotContext<*>.lineGather(
    position: Position = Position.Identity,
    block: LineGatheringContext.() -> Unit
) {
    (features.getOrPut(GatheringList.FEATURE_NAME) {
        GatheringList()
    } as GatheringList).gatheringList.add(
        LineGatheringContext(this, position).apply(block).toGathering()
    )
}

public typealias Line = LineGatheringContext

@Suppress("UNCHECKED_CAST")
public inline fun <reified T : GatherContext> NamedDataPlotContext<*>.gather(
    position: Position = Position.Identity,
    noinline block: T.() -> Unit
) {
    //todo
    when (typeOf<T>()) {
        typeOf<LineGatheringContext>() -> lineGather(position, block as (LineGatheringContext.() -> Unit))
    }
}

public inline fun PlotMutableDataContext.lineGather(
    position: Position = Position.Identity,
    block: LineGatheringMutableContext.() -> Unit
) {
    (features.getOrPut(GatheringList.FEATURE_NAME) {
        GatheringList()
    } as GatheringList).gatheringList.add(
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