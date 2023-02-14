package org.jetbrains.kotlinx.ggdsl.letsplot.feature

import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

//@Serializable
public data class Reversed(val value: Boolean) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("reversed")
    }
}