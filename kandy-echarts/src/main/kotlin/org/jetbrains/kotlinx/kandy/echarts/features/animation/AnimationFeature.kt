/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features.animation

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

internal class AnimationPlotFeature(
    var enable: Boolean? = null,
    var threshold: Int? = null,
    var duration: Int? = null,
    var easing: String? = null,
    var delay: Int? = null
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    internal companion object {
        val FEATURE_NAME: FeatureName = FeatureName("ANIMATION_PLOT_FEATURE")
    }
}

internal class AnimationLayerFeature(
    var enable: Boolean? = null,
    var type: String? = null,
    var threshold: Int? = null,
    var duration: Int? = null,
    var easing: String? = null,
    var delay: Int? = null
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    internal companion object {
        val FEATURE_NAME: FeatureName = FeatureName("ANIMATION_LAYER_FEATURE")
    }
}
