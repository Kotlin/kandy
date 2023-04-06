/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.feature

/**
 * Layer feature.
 *
 * @property featureName the name of feature.
 */
public interface LayerFeature {
    public val featureName: FeatureName
}
