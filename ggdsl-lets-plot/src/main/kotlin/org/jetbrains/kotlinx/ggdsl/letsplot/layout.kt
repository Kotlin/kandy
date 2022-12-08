/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.theme.CustomTheme
import org.jetbrains.kotlinx.ggdsl.letsplot.theme.Theme


//todo
@PlotDslMarker
@Serializable
public data class Layout(
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

    public inline fun theme(theme: Theme, block: CustomTheme.() -> Unit = {}) {
        this.theme = theme
        customTheme = CustomTheme().apply(block)
    }

    public inline fun theme(block: CustomTheme.() -> Unit) {
        theme = CustomTheme().apply(block)
    }

    override val featureName: FeatureName = NAME

    public companion object {
        public val NAME: FeatureName = FeatureName("layout")
    }
}

// TODO behavior
public inline fun LayerPlotContext.layout(block: Layout.() -> Unit) {
    if (features[Layout.NAME] == null) {
        features[Layout.NAME] = Layout().apply(block)
    }
    (features[Layout.NAME] as Layout).apply(block)
}

public val LayerPlotContext.layout: Layout
    get() {
        if (features[Layout.NAME] == null) {
            features[Layout.NAME] = Layout()
        }
        return features[Layout.NAME] as Layout
    }
