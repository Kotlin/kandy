/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.letsPlot

public fun Plot.toLetsPlot(): org.jetbrains.letsPlot.intern.Plot {
    val columnTypes = dataset.columnTypes()
    val featureBuffer = buildList {
        layers.forEach { it.wrap(this, dataset is GroupedDataInterface, if (it.dataset == null) {
            columnTypes
        } else {
            null
        }) }
        freeScales.forEach { it.value.wrap(this) }
        features.forEach { it.value.wrap(this, columnTypes) }
        //  (layout as? LetsPlotLayout)?.wrap(this) // todo
    }
    return letsPlot(dataset.wrap()) + FeatureList(featureBuffer)
}
