/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir

import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.scale.FreeScale

/**
 * Represents the core structure for constructing visualizations.
 *
 * A `Plot` serves as the primary canvas on which various graphical elements,
 * such as layers, scales, and mappings, converge to create a cohesive visualization of data.
 * Each [layer][Layer] contributes distinct graphical elements,
 * such as points, bars, or lines, and their associated aesthetic properties.
 * Global mappings and settings apply overarching aesthetic definitions across layers,
 * while individual layer [aesthetics][Aes] can either adopt or override these global attributes.
 *
 * Features allow for additional customization or annotations, and free scales offer flexibility in data-to-aesthetic mappings beyond any predefined scaling.
 *
 * @property datasets a list of datasets that can be visualized within the plot.
 * Each layer references a specific dataset from this list via its [Layer.datasetIndex].
 * @property layers a collection of [Layer] objects,
 * each defining a specific graphical element, its data source, and associated aesthetic properties.
 * @property globalMappings a set of aesthetic mappings defined at the plot level, dictating how data values map to specific aesthetic attributes.
 * These mappings provide a default aesthetic across layers unless overridden within individual layers.
 * @property globalSettings a collection of aesthetic settings defined at the plot level
 * that influence the appearance of graphical elements across all layers unless overridden within individual layers.
 * @property features a map of features that add enhancements or annotations to the plot, identified by their unique feature names.
 * @property freeScales a collection of scales that allow custom mappings of data values to aesthetic properties,
 * offering additional flexibility beyond any fixed or predefined scales.
 */
public data class Plot(
    val datasets: List<TableData>,
    val layers: List<Layer>,
    val globalMappings: Map<Aes, Mapping>,
    val globalSettings: Map<Aes, Setting>,
    val features: Map<FeatureName, PlotFeature>,
    val freeScales: Map<Aes, FreeScale>,
)
