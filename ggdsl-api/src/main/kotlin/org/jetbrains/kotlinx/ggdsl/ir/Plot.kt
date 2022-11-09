/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreeScale

/**
 * The main IR structure describing the plot. Basically, plot is a collection of layers.
 *
 * @param dataset the main dataset, from which the data are taken
 * in case the layer dataset is not overwritten.
 * @param layers the [List] of [Layer]
 * @param globalMappings the map of mappings defined at the top level (for internal use).
 * @param features the [Map] of the plot features; keys are feature names,
 * values are features with corresponding names.
 * @param freeScales map of free scales.
 */
public data class Plot(
    val dataset: TableData,
    val layers: List<Layer>,
    val globalMappings: Map<AesName, Mapping>,
    val features: Map<FeatureName, PlotFeature>,
    val freeScales: Map<AesName, FreeScale>,
)
