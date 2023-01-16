/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.NameAes
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.Animation
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationBoxplotCandlestick
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationLayerFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationPie

public sealed class EchartsLayerContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerContextImmutable(parent) {
    public val name: NameAes = NameAes(this)

    public operator fun Animation.invoke(block: Animation.() -> Unit): Animation = apply(block).also {
        it.toAnimationLayerFeature()?.let { feature ->
            this@EchartsLayerContextImmutable.features[AnimationLayerFeature.FEATURE_NAME] = feature
        }
    }

    public operator fun AnimationPie.invoke(block: AnimationPie.() -> Unit): AnimationPie = apply(block).also {
        it.toAnimationFeature()?.let { feature ->
            this@EchartsLayerContextImmutable.features[AnimationLayerFeature.FEATURE_NAME] = feature
        }
    }

    public operator fun AnimationBoxplotCandlestick.invoke(block: AnimationBoxplotCandlestick.() -> Unit): AnimationBoxplotCandlestick =
        apply(block).also {
            it.toAnimationFeature()?.let { feature ->
                this@EchartsLayerContextImmutable.features[AnimationLayerFeature.FEATURE_NAME] = feature
            }
        }
}