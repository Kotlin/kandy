/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.letsPlot

public fun Plot.toLetsPlot(): org.jetbrains.letsPlot.intern.Plot {
    // todo validate datasets
    val featureBuffer = buildList {
        layers.forEach { it.wrap(this, datasets, globalMappings, globalSettings) }
        freeScales.forEach { it.value.wrap(this) }
        features.forEach { it.value.wrap(this) }
        //  (layout as? LetsPlotLayout)?.wrap(this) // todo
    }
    return letsPlot(datasets[0].wrap()) + FeatureList(featureBuffer)
}
