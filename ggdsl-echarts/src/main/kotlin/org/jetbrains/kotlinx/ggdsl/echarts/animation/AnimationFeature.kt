package org.jetbrains.kotlinx.ggdsl.echarts.animation

import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

class AnimationFeature(
    var enable: Boolean = true,
    var threshold: Int = 2000,
    var duration: Int = 1000,
    var easing: AnimationEasing = AnimationEasing.CUBIC_OUT,
    var delay: Int = 0
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME = FeatureName("DATA_CHANGE_ANIMATION_FEATURE")
    }
}

class AnimationEasing internal constructor(val name: String) {
    companion object {
        // todo others
        val LINEAR = AnimationEasing("linear")
        val CUBIC_OUT = AnimationEasing("cubicOut")
    }
}