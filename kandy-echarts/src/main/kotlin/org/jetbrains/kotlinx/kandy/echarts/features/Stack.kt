/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features

import org.jetbrains.kotlinx.kandy.echarts.layers.context.AreaHandler
import org.jetbrains.kotlinx.kandy.echarts.layers.context.BarHandler
import org.jetbrains.kotlinx.kandy.echarts.layers.context.LineHandler
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature


// TODO(change stack, move to stack)
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
 * Stack property for [line][org.jetbrains.kotlinx.kandy.echarts.layers.line].
 */
public var LineHandler.stack: Stack
    get() = Stack((layerFeatures[StackFeature.FEATURE_NAME] as StackFeature).name)
    set(value) {
        layerFeatures[StackFeature.FEATURE_NAME] = StackFeature(value.name)
    }

/**
 * Stack property for [area][org.jetbrains.kotlinx.kandy.echarts.layers.area].
 */
public var AreaHandler.stack: Stack
    get() = Stack((layerFeatures[StackFeature.FEATURE_NAME] as StackFeature).name)
    set(value) {
        layerFeatures[StackFeature.FEATURE_NAME] = StackFeature(value.name)
    }

/**
 * Stack property for [bars][org.jetbrains.kotlinx.kandy.echarts.layers.bars].
 */
public var BarHandler.stack: Stack
    get() = Stack((layerFeatures[StackFeature.FEATURE_NAME] as StackFeature).name)
    set(value) {
        layerFeatures[StackFeature.FEATURE_NAME] = StackFeature(value.name)
    }

/**
 * Returns new stack.
 *
 * @param name the stack name
 * @return [Stack]
 */
public fun stack(name: String): Stack = Stack(name)


