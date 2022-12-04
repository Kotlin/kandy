package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.echarts.features.LegendFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.TitleFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.text.TextStyleFeature
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
public class EChartsLayout : PlotFeature {

    public var title: String? = null
        set(value) {
            if (titleFeature == null) {
                titleFeature = TitleFeature(text = value)
            } else {
                titleFeature!!.text = value
            }
            field = value
        }

    public var subtitle: String? = null
        set(value) {
            if (titleFeature == null) {
                titleFeature = TitleFeature(subtext = value)
            } else {
                titleFeature!!.subtext = value
            }
            field = value
        }

    override val featureName: FeatureName = FEATURE_NAME

    @PublishedApi
    internal var titleFeature: TitleFeature? = null

    @PublishedApi
    internal var size: Pair<Int, Int>? = null

    @PublishedApi
    internal var textStyle: TextStyleFeature? = null

    @PublishedApi
    internal var legend: LegendFeature? = null

    @PublishedApi
    internal companion object {
        val FEATURE_NAME: FeatureName = FeatureName("ECHARTS_LAYOUT")
    }
}