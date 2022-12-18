/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.feature.CoordFlip

/**
 * Flip axis of default coordinate system so that horizontal axis becomes vertical and vice versa.
 */
public fun LayerPlotContext.coordFlip() {
    features[CoordFlip.FEATURE_NAME] = CoordFlip
}
