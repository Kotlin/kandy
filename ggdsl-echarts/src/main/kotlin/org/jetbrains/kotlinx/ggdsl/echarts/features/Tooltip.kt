package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EChartsLayout
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public inline fun EChartsLayout.tooltip(crossinline block: TooltipFeature.() -> Unit) {
    this.tooltip = TooltipFeature().apply(block)
}

public enum class Trigger(public val type: String) {
    ITEM("item"), AXIS("axis"), NONE("none")
}

@PlotDslMarker
public class TooltipFeature(
    public var trigger: Trigger? = null,
    public var formatter: String? = null
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("TOOLTIP_FEATURE")
    }
}