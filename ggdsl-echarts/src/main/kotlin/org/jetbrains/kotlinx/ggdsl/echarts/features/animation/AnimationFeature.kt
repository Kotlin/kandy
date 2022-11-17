/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.animation

import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public class AnimationPlotFeature(
    public var enable: Boolean? = null,
    public var threshold: Int? = null,
    public var duration: Int? = null,
    public var easing: AnimationEasing? = null,
    public var delay: Int? = null
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("ANIMATION_PLOT_FEATURE")
    }
}

public class AnimationLineFeature(
    public var enable: Boolean? = null,
    public var threshold: Int? = null,
    public var duration: Int? = null,
    public var easing: AnimationEasing? = null,
    public var delay: Int? = null
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("ANIMATION_LINE_FEATURE")
    }
}

public class AnimationEasing private constructor(public val name: String) {
    public companion object {
        // todo (add function?)
        public val LINEAR: AnimationEasing = AnimationEasing("linear")
        public val CUBIC_OUT: AnimationEasing = AnimationEasing("cubicOut")
        public val ELASTIC_OUT: AnimationEasing = AnimationEasing("elasticOut")
    }
}