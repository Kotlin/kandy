/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir

import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.FreeScale
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * The main IR structure describing the plot. Basically, plot is a collection of layers.
 *
 * @param datasets [List] of plot datasets. Each layer has [Layer.datasetIndex] of its dataset in this list.
 * @param layers the [List] of [Layer] in this plot.
 * @param globalMappings the map of aesthetic names to mappings defined at the top level (for internal use);
 * keys are aesthetic names, values are mappings on corresponding aesthetics.
 * @param features [Map] of the to plot features; keys are feature names,
 * values are features with corresponding names.
 * @param freeScales [Map] of free scales; keys are aesthetic names,
 * values are corresponding free scales.
 */
public data class Plot(
    val datasets: List<TableData>,
    val layers: List<Layer>,
    val globalMappings: Map<AesName, Mapping>,
    val globalSettings: Map<AesName, Setting>,
    val features: Map<FeatureName, PlotFeature>,
    val freeScales: Map<AesName, FreeScale>,
)
