/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature

/**
 * Whether to reverse axes.
 */
public var LayerContextInterface.reversed: Boolean
    get() = true
    set(value) {
        layerFeatures[Reversed.FEATURE_NAME] = Reversed(value)
    }

public data class Reversed(val value: Boolean) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("reversed")
    }
}