/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.series
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.position.Position

public interface GatheringContextInterface : TableDataContext {
    public override val data: NamedData
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
    override val data: NamedData = parent.data
    override val seriesCollector: MutableList<Series> = mutableListOf()
}

public interface GatheringContextMutable : TableBindingContextInterfaceMutable, GatheringContextInterface

public abstract class GatheringSubContextMutable(
    parent: TableBindingContextInterfaceMutable,
    override val position: Position
) :
    TableSubContextMutable(parent), GatheringContextMutable {
    override val data: NamedData
        get() = super.data as NamedData
    override val seriesCollector: MutableList<Series> = mutableListOf()
}

public abstract class SeriesPlotContextMutable(
    public override val position: Position,
) : PlotContextBase, GatheringContextInterface, TableContextMutableBase() {
    override val dataBuffer: MutableTableData = MutableNamedData()
    override val data: NamedData get() = super.data as NamedData
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

 */