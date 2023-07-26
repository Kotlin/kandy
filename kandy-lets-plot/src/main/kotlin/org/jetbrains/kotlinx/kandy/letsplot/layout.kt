/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot

import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.letsplot.feature.Layout

/**
 * Opens [Layout] context for layout configuration.
 */
public inline fun PlotContext.layout(block: Layout.() -> Unit) {
    if (plotFeatures[Layout.NAME] == null) {
        plotFeatures[Layout.NAME] = Layout().apply(block)
    }
    (plotFeatures[Layout.NAME] as Layout).apply(block)
}

/**
 * Plot [Layout] accessor. Returns an existed [Layout]
 * if it has not already been created or creates a new one otherwise.
 */
public val PlotContext.layout: Layout
    get() {
        if (plotFeatures[Layout.NAME] == null) {
            plotFeatures[Layout.NAME] = Layout()
        }
        return plotFeatures[Layout.NAME] as Layout
    }
