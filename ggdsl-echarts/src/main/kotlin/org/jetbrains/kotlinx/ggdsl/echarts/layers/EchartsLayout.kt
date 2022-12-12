package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.Grid
import org.jetbrains.kotlinx.ggdsl.echarts.features.Legend
import org.jetbrains.kotlinx.ggdsl.echarts.features.Title
import org.jetbrains.kotlinx.ggdsl.echarts.features.Tooltip
import org.jetbrains.kotlinx.ggdsl.echarts.features.text.TextStyle
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

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

    public var grid: Grid = Grid()

    @PublishedApi
    internal var legend: Legend? = null

    @PublishedApi
    internal var tooltip: Tooltip? = null

    @PublishedApi
    internal companion object {
        val FEATURE_NAME: FeatureName = FeatureName("ECHARTS_LAYOUT")
    }
}