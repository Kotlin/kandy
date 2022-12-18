/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.animation

/**
 * Animation easing effects.
 *
 * @property LINEAR
 * @property CUBIC_OUT by default
 * @property ELASTIC_OUT
 */
public class AnimationEasing private constructor(public val name: String) {
    public companion object {
        // todo (add function?)
        public val LINEAR: AnimationEasing = AnimationEasing("linear")
        public val CUBIC_OUT: AnimationEasing = AnimationEasing("cubicOut")
        public val ELASTIC_OUT: AnimationEasing = AnimationEasing("elasticOut")
    }
}