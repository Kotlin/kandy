package org.jetbrains.kotlinx.ggdsl.letsplot.feature

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

@Serializable
public object CoordFlip : PlotFeature {
    public val FEATURE_NAME: FeatureName = FeatureName("COORD_FLIP")
    override val featureName: FeatureName = FEATURE_NAME
}