package org.jetbrains.kotlinx.ggdsl.letsplot.layers.gather

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public data class Gathering(
    val geom: Geom,
    val data: NamedDataInterface,
    val series: List<Series>,
    val globalSettings: Map<AesName, Setting>,
    val position: Position
)

public class GatheringList : PlotFeature {
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

public interface GatherContext: BindingContext {
    public val data: NamedDataInterface
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

