package org.jetbrains.kotlinx.ggdsl.letsplot.layers.gather

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.SubTableBindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LINE
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.LineContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
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

public interface GatherContext {
    public fun toGathering(): Gathering
}

public class LineGatheringContext(parent: NamedDataPlotContext<*>, private val position: Position):
    SubTableBindingContext(parent), LineContextInterface, GatherContext {
    override val data: NamedDataInterface = parent.data
    public val seriesCollector: MutableList<Series> = mutableListOf<Series>()

    public inline fun series(label: String, block: LineSeriesContext.() -> Unit){
        seriesCollector.add(LineSeriesContext(this).apply(block).toSeries(label))
    }

    public override fun toGathering(): Gathering {
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
public inline fun< reified T: GatherContext> NamedDataPlotContext<*>.gather(
    position: Position = Position.Identity,
    noinline block: T.() -> Unit
) {
    //todo
    when(typeOf<T>()) {
        typeOf<LineGatheringContext>() -> lineGather(position, block as (LineGatheringContext.() -> Unit))
    }
}
