package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EChartsLayout
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public inline fun EChartsLayout.legend(crossinline block: LegendFeature.() -> Unit) {
    this.legend = LegendFeature().apply(block)
}

public enum class LegendAlign(public val align: String) {
    AUTO("auto"), LEFT("left"), RIGHT("right")
}

public enum class LegendType(public val type: String) {
    PLAIN("plain"), SCROLL("scroll")
}

public enum class Orient(public val type: String) {
    HORIZONTAL("horizontal"), VERTICAL("vertical")
}

@PlotDslMarker
public class LegendFeature(
    public var type: LegendType? = null,
    public var left: Int? = null,
    public var top: Int? = null,
    public var right: Int? = null,
    public var bottom: Int? = null,
    public var width: Int? = null,
    public var height: Int? = null,
    public var orient: Orient? = null,
    public var formatter: String? = null,
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("LEGEND_FEATURE")
    }
}