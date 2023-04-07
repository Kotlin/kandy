/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

public object CoordFlip : PlotFeature {
    public val FEATURE_NAME: FeatureName = FeatureName("COORD_FLIP")
    override val featureName: FeatureName = FEATURE_NAME
}