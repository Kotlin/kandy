/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.animation

import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public class AnimationFeature(
    public var enable: Boolean = true,
    public var threshold: Int = 2000,
    public var duration: Int = 1000,
    public var easing: AnimationEasing = AnimationEasing.CUBIC_OUT,
    public var delay: Int = 0
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("DATA_CHANGE_ANIMATION_FEATURE")
    }
}

public class AnimationEasing internal constructor(public val name: String) {
    public companion object {
        // todo others
        public val LINEAR: AnimationEasing = AnimationEasing("linear")
        public val CUBIC_OUT: AnimationEasing = AnimationEasing("cubicOut")
    }
}