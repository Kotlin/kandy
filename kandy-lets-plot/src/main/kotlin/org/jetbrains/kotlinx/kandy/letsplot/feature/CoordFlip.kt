/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.plotFeatures
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.letsplot.feature.CoordFlip.FEATURE_NAME

/**
 * Flip the axes of the default coordinate system.
 *
 * After applying this function, the horizontal axis (typically the x-axis) will become vertical,
 * and the vertical axis (typically the y-axis) will become horizontal.
 *
 * > **Warning**: The API `coordFlip` will be revised in future releases.
 *
 * ### Example
 *
 * ```kotlin
 * plot {
 *     bars {
 *         x(listOf("a", "b", "c"))
 *         y(listOf(4, 3, 5))
 *     }
 *     coordFlip()
 * }
 * ```
 */
public fun PlotBuilder.coordFlip() {
    plotFeatures[CoordFlip.FEATURE_NAME] = CoordFlip
}

/**
 * Represents the flipping of axes in a plot.
 *
 * This object is added as a feature to the `PlotBuilder` when [coordFlip] is called,
 * and it controls the reorientation of the axes when the plot is rendered.
 *
 * @property FEATURE_NAME The unique name identifying this feature in the plot context.
 */
public object CoordFlip : PlotFeature {
    public val FEATURE_NAME: FeatureName = FeatureName("COORD_FLIP")
    override val featureName: FeatureName = FEATURE_NAME
}