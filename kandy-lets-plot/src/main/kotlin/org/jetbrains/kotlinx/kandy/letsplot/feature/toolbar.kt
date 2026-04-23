/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.plotFeatures
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * Adds an interactive toolbar to the plot.
 *
 * Works only in with Web (HTML) outputs.
 */
public fun PlotBuilder.toolbar() {
    plotFeatures[Toolbar.FEATURE_NAME] = Toolbar()
}

/**
 * Represents the toolbar feature data for the plot.
 */
public class Toolbar : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("toolbar")
    }
}