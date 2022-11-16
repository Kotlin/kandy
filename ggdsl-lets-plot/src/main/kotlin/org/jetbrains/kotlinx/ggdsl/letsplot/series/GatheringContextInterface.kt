package org.jetbrains.kotlinx.ggdsl.letsplot.series

import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public interface GatheringContextInterface : TableDataContext {
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
        )
    }
}

public abstract class SeriesPlotContextBase : PlotContextBase, GatheringContextInterface {
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val seriesCollector: MutableList<Series> = mutableListOf()
    override val bindingCollector: BindingCollector = BindingCollector()
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

public abstract class SeriesContextImmutable(parent: TableDataContext) :
    BindingSubContextImmutable(parent, copySettings = false) {
    public fun toSeries(label: String): Series {
        return Series(
            bindingCollector.mappings,
            bindingCollector.settings,
            label
        )
    }
}

public abstract class GatheringContextBaseImmutable(
    parent: NamedDataPlotContext, override val position: Position
) : TableSubContextImmutable(parent), GatheringContextInterface {
    override val data: NamedDataInterface = parent.data
    override val seriesCollector: MutableList<Series> = mutableListOf()
}

public interface GatheringContextMutable : TableBindingContextInterfaceMutable, GatheringContextInterface

public abstract class GatheringSubContextMutable(
    parent: TableBindingContextInterfaceMutable,
    override val position: Position
) :
    TableSubContextMutable(parent), GatheringContextMutable {
    override val data: NamedDataInterface
        get() = super.data as NamedDataInterface
    override val seriesCollector: MutableList<Series> = mutableListOf()
}

public abstract class SeriesPlotContextMutable(
    public override val position: Position,
) : PlotContextBase, GatheringContextInterface, TableContextMutableBase() {
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

    public override val seriesCollector: MutableList<Series> = mutableListOf()
    override val bindingCollector: BindingCollector = BindingCollector()

    @PublishedApi
    internal fun gather() {
        features[GatheringList.FEATURE_NAME] = GatheringList().apply {
            gatheringList.add(toGathering())
        }
    }
}

public abstract class SeriesContextMutable(parent: TableBindingContextInterfaceMutable) :
    TableSubContextMutable(parent, false) {
    public fun toSeries(label: String): Series {
        return Series(
            bindingCollector.mappings,
            bindingCollector.settings,
            label
        )
    }
}

@PublishedApi
internal fun LayerPlotContext.addGathering(gathering: Gathering) {
    (features.getOrPut(GatheringList.FEATURE_NAME) {
        GatheringList()
    } as GatheringList).gatheringList.add(gathering)
}
