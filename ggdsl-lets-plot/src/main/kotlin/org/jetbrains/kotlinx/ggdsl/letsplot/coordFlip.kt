/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

@Serializable
public object CoordFlip : PlotFeature {
    public val FEATURE_NAME: FeatureName = FeatureName("COORD_FLIP")
    override val featureName: FeatureName = FEATURE_NAME
}

public fun LayerPlotContext.coordFlip() {
    features[CoordFlip.FEATURE_NAME] = CoordFlip
}
