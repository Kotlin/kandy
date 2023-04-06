/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.kandy.letsplot.feature.Layout

/**
 * Opens [Layout] context for layout configuration.
 */
public inline fun LayerPlotContext.layout(block: Layout.() -> Unit) {
    if (features[Layout.NAME] == null) {
        features[Layout.NAME] = Layout().apply(block)
    }
    (features[Layout.NAME] as Layout).apply(block)
}

/**
 * Plot [Layout] accessor. Returns an existed [Layout]
 * if it has not already been created or creates a new one otherwise.
 */
public val LayerPlotContext.layout: Layout
    get() {
        if (features[Layout.NAME] == null) {
            features[Layout.NAME] = Layout()
        }
        return features[Layout.NAME] as Layout
    }
