/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.letsplot.theme.CustomTheme
import org.jetbrains.kotlinx.kandy.letsplot.theme.Flavor
import org.jetbrains.kotlinx.kandy.letsplot.theme.Theme

/**
 * Provides a context for configuring the layout of a plot.
 *
 * Inside this context, you can set various layout properties such as
 * title, subtitle, caption, size, and others.
 *
 * ### Example
 *
 * ```kotlin
 * val p = plotOf(data)
 *     .layout {
 *         title = "Main Title"
 *         subtitle = "Subtitle"
 *         xAxisLabel = "X-Axis"
 *         yAxisLabel = "Y-Axis"
 *     }
 * ```
 */
public inline fun PlotContext.layout(block: Layout.() -> Unit) {
    if (plotFeatures[Layout.NAME] == null) {
        plotFeatures[Layout.NAME] = Layout().apply(block)
    }
    (plotFeatures[Layout.NAME] as Layout).apply(block)
}

/**
 * Accessor for the [Layout] of a plot.
 * Returns an existing [Layout] if one exists, or creates a new one otherwise.
 */
public val PlotContext.layout: Layout
    get() {
        if (plotFeatures[Layout.NAME] == null) {
            plotFeatures[Layout.NAME] = Layout()
        }
        return plotFeatures[Layout.NAME] as Layout
    }

// todo separate model and context!!!
/**
 * Represents the layout configuration parameters for a plot.
 *
 * @property title the main title of the plot.
 * @property subtitle the subtitle displayed below the main title.
 * @property caption additional text displayed at the bottom of the plot.
 * @property xAxisLabel label for the X-Axis.
 * @property yAxisLabel label for the Y-Axis.
 * @property flavor the color scheme (flavor) of the plot.
 * @property size the dimensions (width x height) of the plot in pixels.
 */
public data class Layout(
    var title: String? = null,
    var subtitle: String? = null,
    var caption: String? = null,
    // todo remove?
    var xAxisLabel: String? = null,
    var yAxisLabel: String? = null,

    var flavor: Flavor? = null,

    var size: Pair<Int, Int>? = null
) : PlotFeature {

    @PublishedApi
    internal var theme: Theme? = null

    @PublishedApi
    internal var customTheme: CustomTheme? = null

    /**
     * Configures the theme of the plot.
     *
     * @param theme one of the predefined themes.
     * @param block additional customizations to apply on top of the main theme.
     */
    public inline fun theme(theme: Theme, block: CustomTheme.() -> Unit = {}) {
        this.theme = theme
        customTheme = CustomTheme().apply(block)
    }

    /**
     * Configures a custom theme for the plot.
     *
     * @param block a lambda function to define the custom theme.
     */
    public inline fun theme(block: CustomTheme.() -> Unit) {
        theme = CustomTheme().apply(block)
    }

    override val featureName: FeatureName = NAME

    public companion object {
        public val NAME: FeatureName = FeatureName("layout")
    }
}