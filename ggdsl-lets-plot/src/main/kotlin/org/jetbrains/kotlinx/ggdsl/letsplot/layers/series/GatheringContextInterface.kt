package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.*
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public interface GatheringContextInterface: TableBindingContext {
    public override val data: NamedDataInterface
    public val seriesCollector: MutableList<Series>
    public val position: Position
    public val geom: Geom

    public fun toGathering(): Gathering {
        return Gathering(
            geom,
            data,
            seriesCollector,
            bindingCollector.settings,
            position
        ).apply {
            println(this)
        }
    }
}

public abstract class SeriesPlotContextBase: PlotContextBase, GatheringContextInterface {
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val seriesCollector: MutableList<Series> = mutableListOf()
    override val bindingCollector: BindingCollector = BindingCollector(false)
    override fun toPlot(): Plot {
        gather()
        return Plot(
            data,
            listOf(),
            bindingCollector.mappings,
            features,
            bindingCollector.freeScales /*TODO free scales*/
        )
    }
    @PublishedApi
    internal fun gather() {
        features[GatheringList.FEATURE_NAME] = GatheringList().apply {
            gatheringList.add(toGathering())
        }
    }
}

public abstract class SeriesContext(parent: TableBindingContext) : SubTableBindingContext(parent) {
    public fun toSeries(label: String): Series {
        return Series(
            bindingCollector.mappings,
            bindingCollector.settings,
            label
        )
    }
}

public abstract class GatheringContextBase(
    parent: NamedDataPlotContext<*>,
    override val position: Position
) : SubTableBindingContext(parent), GatheringContextInterface {
    override val data: NamedDataInterface = parent.data
    override val seriesCollector: MutableList<Series> = mutableListOf()
}


public interface GatheringMutableContext : MutableDataBindingContextInterface, GatheringContextInterface

public abstract class GatheringMutableContextBase(
    parent: MutableDataBindingContextInterface,
    override val position: Position
) :
    SubMutableDataContext(parent), GatheringMutableContext {
    override val data: NamedDataInterface
        get() = super.data as NamedDataInterface
    override val seriesCollector: MutableList<Series> = mutableListOf()
}

public abstract class GatheringPlotMutableContextBase(
    override val position: Position,
) : PlotContextBase, LineGatheringContextInterface, MutableDataBindingContext() {
    override val dataBuffer: MutableTableData = MutableNamedData()
    override val data: NamedDataInterface get() = super.data as NamedDataInterface
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override fun toPlot(): Plot {
        gather()
        return Plot(
            data,
            listOf(),
            bindingCollector.mappings,
            features,
            bindingCollector.freeScales /*TODO free scales*/
        )
    }

    override val seriesCollector: MutableList<Series> = mutableListOf()
    override val bindingCollector: BindingCollector = BindingCollector(false)

    @PublishedApi
    internal fun gather() {
        features[GatheringList.FEATURE_NAME] = GatheringList().apply {
            gatheringList.add(toGathering())
        }
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

@PublishedApi
internal fun PlotContext.addGathering(gathering: Gathering) {
    (features.getOrPut(GatheringList.FEATURE_NAME) {
        GatheringList()
    } as GatheringList).gatheringList.add(gathering)
}
