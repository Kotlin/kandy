/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.letsPlot.intern.FeatureList

public interface ExternalLetsPlotFeature: PlotFeature {
    public fun wrap(): FeatureList
}
