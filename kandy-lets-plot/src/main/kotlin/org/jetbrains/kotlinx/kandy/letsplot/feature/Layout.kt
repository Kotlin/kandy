/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.feature

// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.letsplot.theme.CustomTheme
import org.jetbrains.kotlinx.kandy.letsplot.theme.Flavor
import org.jetbrains.kotlinx.kandy.letsplot.theme.Theme

// todo separate model and context!!!

/**
 * Plot layout parameters.
 *
 * @property title plot title.
 * @property subtitle plot subtitle.
 * @property caption plot caption.
 * @property flavor plot flavor (color scheme).
 * @property size plot size.
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
     * Sets up plot theme.
     *
     * @param theme one of pre-cooked theme.
     * @param block customization in addition to the main theme.
     */
    public inline fun theme(theme: Theme, block: CustomTheme.() -> Unit = {}) {
        this.theme = theme
        customTheme = CustomTheme().apply(block)
    }

    /**
     * Sets up plot theme.
     *
     * @param block custom theme builder.
     */
    public inline fun theme(block: CustomTheme.() -> Unit) {
        theme = CustomTheme().apply(block)
    }

    override val featureName: FeatureName = NAME

    public companion object {
        public val NAME: FeatureName = FeatureName("layout")
    }
}