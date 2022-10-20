package org.jetbrains.kotlinx.ggdsl.letsplot.layers.gather

import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.dsl.categorical
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.BindingCollector
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.SubTableBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.invoke
import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.DefaultUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.letsplot.COLOR
import org.jetbrains.kotlinx.ggdsl.letsplot.X
import org.jetbrains.kotlinx.ggdsl.letsplot.Y
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LINE
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LineContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.wrap
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.reflect.typeOf

public data class Gathering(
    val geom: Geom,
    val data: NamedDataInterface,
    val series: List<Series>,
    val globalSettings: Map<AesName, Setting>,
    val position: Position
)

public class GatheringList: PlotFeature {
    public val gatheringList: MutableList<Gathering> = mutableListOf<Gathering>()
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("gathering")
    }
}

public data class Series(
    val mappings: Map<AesName, Mapping>,
    val settings: Map<AesName, Setting>,
    val label: String,
)

public class LineGatheringContext(parent: NamedDataPlotContext<*>, private val position: Position): SubTableBindingContext(parent), LineContextInterface {
    override val data: NamedDataInterface = parent.data
    public val seriesCollector: MutableList<Series> = mutableListOf<Series>()

    public inline fun series(label: String, block: LineSeriesContext.() -> Unit){
        seriesCollector.add(LineSeriesContext(this).apply(block).toSeries(label))
    }

    public fun toGathering(): Gathering {
        return Gathering(
            LINE,
            data,
            seriesCollector,
            bindingCollector.settings,
            position
        )
    }
}

public class LineSeriesContext(parent: LineGatheringContext): SubTableBindingContext(parent), LineContextInterface {
    public fun toSeries(label: String): Series {
        return Series(
            bindingCollector.mappings,
            bindingCollector.settings,
            label
        )
    }
}

public fun NamedDataPlotContext<*>.lineGather(
    position: Position = Position.Identity,
    block: LineGatheringContext.() -> Unit) {
    (features.getOrPut(GatheringList.FEATURE_NAME) {
        GatheringList()
    } as GatheringList).gatheringList.add(
        LineGatheringContext(this, position).apply(block).toGathering()
    )
}
