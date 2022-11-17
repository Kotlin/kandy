/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.letsPlot

public fun Plot.toLetsPlot(): org.jetbrains.letsPlot.intern.Plot {
    val featureBuffer = buildList {
        layers.forEach { it.wrap(this) }
        freeScales.forEach { it.value.wrap(this) }
        features.forEach { it.value.wrap(this) }
        //  (layout as? LetsPlotLayout)?.wrap(this) // todo
    }
    return letsPlot(dataset.wrap()) + FeatureList(featureBuffer)
}
