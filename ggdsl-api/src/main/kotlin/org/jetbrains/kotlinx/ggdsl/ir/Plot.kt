/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreeScale

/**
 * Plot consists of a set of layers.
 *
 * @param layers the [List] of [Layer]
 * @param layout layout settings
 * @param features the [Map] of the plot features; keys are feature names,
 * values are features with corresponding names.
 */
data class Plot(
    val dataset: NamedData, // todo remove???
    val layers: List<Layer>,
    val layout: Layout? = null,
    val features: Map<FeatureName, PlotFeature> = emptyMap(),
    val freeScales: Map<AesName, FreeScale> = emptyMap(),
)
