package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.LegendFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.Title
import org.jetbrains.kotlinx.ggdsl.echarts.features.TooltipFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.text.TextStyle
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public inline fun LayerPlotContext.layout(block: EChartsLayout.() -> Unit) {
    features[EChartsLayout.FEATURE_NAME] = EChartsLayout().apply(block)
}

/**
 * Plot layout settings
 *
 * @param title the title of this plot
 * @param size the size of this plot
 */
@PlotDslMarker
public class EChartsLayout : PlotFeature {
    public var size: Pair<Int, Int>? = null

    override val featureName: FeatureName = FEATURE_NAME

    public var title: Title = Title()

    public var textStyle: TextStyle = TextStyle()

    @PublishedApi
    internal var legend: LegendFeature? = null

    @PublishedApi
    internal var tooltip: TooltipFeature? = null

    @PublishedApi
    internal companion object {
        val FEATURE_NAME: FeatureName = FeatureName("ECHARTS_LAYOUT")
    }
}