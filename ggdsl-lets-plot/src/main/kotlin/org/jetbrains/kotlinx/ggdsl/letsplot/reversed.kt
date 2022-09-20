package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

public data class Reversed(val value: Boolean) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("reversed")
    }
}

public var LayerContext.reversed: Boolean
    get() = true
    set(value) {
        features[Reversed.FEATURE_NAME] = Reversed(value)
    }
