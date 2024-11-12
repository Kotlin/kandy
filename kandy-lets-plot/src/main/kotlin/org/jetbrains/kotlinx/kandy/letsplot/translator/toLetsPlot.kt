/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Coordinates
import org.jetbrains.kotlinx.kandy.letsplot.feature.DefaultCoordinates
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.letsPlot

public fun Plot.toLetsPlot(): org.jetbrains.letsPlot.intern.Plot {
    // TODO(https://github.com/Kotlin/kandy/issues/126)
    val featureBuffer = buildList {
        layers.forEach { it.wrap(this, datasets, globalMappings, globalSettings) }
        freeScales.forEach { it.value.wrap(this) }
        val featuresWithCoord = if (features.contains(Coordinates.FEATURE_NAME)) {
            features
        } else {
            features + (Coordinates.FEATURE_NAME to DefaultCoordinates)
        }
        featuresWithCoord.forEach { it.value.wrap(this, this@toLetsPlot) }
    }


    return letsPlot(datasets[0].wrap()) + FeatureList(featureBuffer)
}
