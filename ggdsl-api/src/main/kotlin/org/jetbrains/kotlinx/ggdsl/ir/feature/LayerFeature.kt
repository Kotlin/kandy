/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.feature

/**
 * Layer feature interface.
 *
 * @property featureName the name of feature
 */
public interface LayerFeature {
    public val featureName: FeatureName // todo remove?
}
