/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.plotFeatures
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.letsplot.style.CustomStyle
import org.jetbrains.kotlinx.kandy.letsplot.style.Style
import org.jetbrains.kotlinx.kandy.letsplot.style.Theme

/**
 * Provides a context for configuring the layout of a plot.
 *
 * Inside this context, you can set various layout properties such as
 * title, subtitle, caption, size, and others.
 *
 * ### Example
 *
 * ```kotlin
 * plot {
 *     line { x(listOf(1, 2, 3)); y.constant(5) }
 *     layout {
 *         title = "Main Title"
 *         subtitle = "Subtitle"
 *         xAxisLabel = "X-Axis"
 *         yAxisLabel = "Y-Axis"
 *         style(Style.Grey)
 *     }
 * }
 * ```
 */
public inline fun PlotBuilder.layout(block: Layout.() -> Unit) {
    if (plotFeatures[Layout.NAME] == null) {
        plotFeatures[Layout.NAME] = Layout().apply(block)
    }
    (plotFeatures[Layout.NAME] as Layout).apply(block)
}

/**
 * Accessor for the [Layout] of a plot.
 * Returns an existing [Layout] if one exists, or creates a new one otherwise.
 */
public val PlotBuilder.layout: Layout
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
 * @property theme the theme of the plot.
 * @property size the dimensions (width x height) of the plot in pixels.
 */
public data class Layout(
    var title: String? = null,
    var subtitle: String? = null,
    var caption: String? = null,
    // todo remove?
    var xAxisLabel: String? = null,
    var yAxisLabel: String? = null,

    var theme: Theme? = null,

    var size: Pair<Int, Int>? = null
) : PlotFeature {

    @PublishedApi
    internal var style: Style? = null

    @PublishedApi
    internal var customStyle: CustomStyle? = null

    /**
     * Configures the style of the plot.
     *
     * @param style one of the predefined styles.
     * @param block additional customizations to apply on top of the main style.
     */
    public inline fun style(style: Style, block: CustomStyle.() -> Unit = {}) {
        this.style = style
        customStyle = CustomStyle().apply(block)
    }

    /**
     * Configures a custom style for the plot.
     *
     * @param block a lambda function to define the custom style.
     */
    public inline fun style(block: CustomStyle.() -> Unit) {
        style = CustomStyle().apply(block)
    }

    override val featureName: FeatureName = NAME

    public companion object {
        public val NAME: FeatureName = FeatureName("layout")
    }
}