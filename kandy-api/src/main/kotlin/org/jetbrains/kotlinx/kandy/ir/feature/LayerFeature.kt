/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.feature

/**
 * Represents a feature specific to a layer within the plotting system.
 * Such features might affect or enhance the behavior or appearance of individual layers in the plot.
 *
 * @property featureName the unique identifier or name of the layer-specific feature.
 */
public interface LayerFeature {
    // todo remove
    public val featureName: FeatureName
}
