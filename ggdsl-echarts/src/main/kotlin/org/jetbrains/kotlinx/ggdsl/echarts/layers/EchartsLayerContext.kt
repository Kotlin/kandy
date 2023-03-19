/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContext
import org.jetbrains.kotlinx.ggdsl.echarts.aes.NAME
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.Animation
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationBoxplotCandlestick
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationLayerFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationPie

public sealed class EchartsLayerContext(parent: LayerCollectorContext) : LayerContext(parent) {
    public var name: String? = null
        set(value) {
            addNonPositionalSetting(NAME, value)
        }

    public operator fun Animation.invoke(block: Animation.() -> Unit): Animation = apply(block).also {
        it.toAnimationLayerFeature()?.let { feature ->
            this@EchartsLayerContext.features[AnimationLayerFeature.FEATURE_NAME] = feature
        }
    }

    public operator fun AnimationPie.invoke(block: AnimationPie.() -> Unit): AnimationPie = apply(block).also {
        it.toAnimationFeature()?.let { feature ->
            this@EchartsLayerContext.features[AnimationLayerFeature.FEATURE_NAME] = feature
        }
    }

    public operator fun AnimationBoxplotCandlestick.invoke(block: AnimationBoxplotCandlestick.() -> Unit): AnimationBoxplotCandlestick =
        apply(block).also {
            it.toAnimationFeature()?.let { feature ->
                this@EchartsLayerContext.features[AnimationLayerFeature.FEATURE_NAME] = feature
            }
        }
}