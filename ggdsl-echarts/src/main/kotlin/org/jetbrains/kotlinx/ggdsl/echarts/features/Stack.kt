/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.echarts.layers.AreaContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.layers.LineContextImmutable
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

/**
 * Name of stack. On the same category axis, the series with the same stack name would be put on top of each other.
 *
 * @see stack
 */
public data class Stack internal constructor(val name: String)

internal data class StackFeature internal constructor(val name: String) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME: FeatureName = FeatureName("STACK_FEATURE")
    }
}

/**
 * Stack property for [line][org.jetbrains.kotlinx.ggdsl.echarts.layers.line].
 */
public var LineContextImmutable.stack: Stack
    get() = Stack((features[StackFeature.FEATURE_NAME] as StackFeature).name)
    set(value) {
        features[StackFeature.FEATURE_NAME] = StackFeature(value.name)
    }

/**
 * Stack property for [area][org.jetbrains.kotlinx.ggdsl.echarts.layers.area].
 */
public var AreaContextImmutable.stack: Stack
    get() = Stack((features[StackFeature.FEATURE_NAME] as StackFeature).name)
    set(value) {
        features[StackFeature.FEATURE_NAME] = StackFeature(value.name)
    }

/**
 * Stack property for [bars][org.jetbrains.kotlinx.ggdsl.echarts.layers.bars].
 */
public var BarContextImmutable.stack: Stack
    get() = Stack((features[StackFeature.FEATURE_NAME] as StackFeature).name)
    set(value) {
        features[StackFeature.FEATURE_NAME] = StackFeature(value.name)
    }

/**
 * Returns new stack.
 *
 * @param name the stack name
 * @return [Stack]
 */
public fun stack(name: String): Stack = Stack(name)


