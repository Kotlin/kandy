package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.theme.CustomTheme
import org.jetbrains.kotlinx.ggdsl.letsplot.theme.Theme


//todo
@PlotDslMarker
data class Layout(
    var title: String? = null,
    var subtitle: String? = null,
    var caption: String? = null,
    // todo remove?
    var xAxisLabel: String? = null,
    var yAxisLabel: String? = null,

    var size: Pair<Int, Int>? = null
) : PlotFeature {

    @PublishedApi
    internal var theme: Theme? = null
    @PublishedApi
    internal var customTheme: CustomTheme? = null

    inline fun theme(theme: Theme, block: CustomTheme.() -> Unit = {}) {
        this.theme = theme
        customTheme = CustomTheme().apply(block)
    }

    inline fun theme(block: CustomTheme.() -> Unit) {
        theme = CustomTheme().apply(block)
    }

    override val featureName: FeatureName = NAME

    companion object {
        val NAME = FeatureName("layout")
    }
}

inline fun PlotContext.layout(block: Layout.() -> Unit) {
    features[Layout.NAME] = Layout().apply(block)
}

val PlotContext.layout: Layout
    get() {
        if (features[Layout.NAME] == null) {
            features[Layout.NAME] = Layout()
        }
        return features[Layout.NAME] as Layout
    }
