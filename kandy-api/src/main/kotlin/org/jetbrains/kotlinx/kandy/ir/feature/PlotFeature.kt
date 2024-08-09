/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.feature

/**
 * Represents a feature specific to the overall plot.
 * Such features might influence or enhance the global behavior or appearance of the entire plot.
 *
 * @property featureName the unique identifier or name of the plot-wide feature.
 */
public interface PlotFeature {
    // TODO(https://github.com/Kotlin/kandy/issues/409)
    public val featureName: FeatureName
}