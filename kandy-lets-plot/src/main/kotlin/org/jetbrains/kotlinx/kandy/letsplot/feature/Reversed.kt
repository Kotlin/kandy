/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature

/**
 * Indicates whether the axes in the layer should be reversed.
 *
 * When set to `true`, the direction of the axes will be reversed.
 * For example, a y-axis that normally goes from bottom to top could go from top to bottom.
 *
 * > **Warning**: The API `reversed` will be revised in future releases.
 *
 * @see [Reversed]
 */
public var LayerBuilder.reversed: Boolean
    get() = true
    set(value) {
        @Suppress("INVISIBLE_MEMBER")
        layerFeatures[Reversed.FEATURE_NAME] = Reversed(value)
    }

/**
 * Represents the `reversed` feature data for the layer.
 *
 * This feature controls the direction of the axes in the layer.
 * If [value] is set to true, the direction of the axes will be reversed.
 *
 * @property value Indicates whether the axes should be reversed.
 */
public data class Reversed(val value: Boolean) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("reversed")
    }
}