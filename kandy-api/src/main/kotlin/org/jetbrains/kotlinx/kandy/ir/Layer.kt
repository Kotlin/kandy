/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir

import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.ir.scale.FreeScale

/**
 * Represents a foundational component in constructing a plot.
 * Each `Layer` defines a specific graphical element on the plot, such as `points`, `lines`, or `bars`.
 * Plots are incrementally built by stacking layers upon one another.
 *
 * Within a `Layer`, several elements come together:
 * - **Data**: The data intended for visualization.
 * - **Geom**: Specifies the type of geometric object to render.
 * - **Aesthetic Mappings**: A collection of rules mapping data to the visual properties of the geom,
 * such as `position`, `color`, `size`, etc.
 *
 * @property datasetIndex the index referencing the dataset for this layer within [Plot.datasets].
 * @property geom the geometric object ([Geom]) characterizing the type of visual representation for this layer.
 * @property mappings the dictionary associating [aesthetic attributes][Aes] to their respective data mappings.
 * @property settings the dictionary associating [aesthetic attributes][Aes] to their settings or configurations.
 * @property features the dictionary defining unique features of this layer,
 * where keys are feature names and values represent the corresponding feature details.
 * @property freeScales the dictionary associating [aesthetic attributes][Aes] to their [free scales][FreeScale].
 * @property inheritsBindings indicates if this layer should inherit any bindings from its predecessors. Default is `true`.
 */
public data class Layer(
    val datasetIndex: Int,
    val geom: Geom,
    val mappings: Map<Aes, Mapping>,
    val settings: Map<Aes, Setting>,
    val features: Map<FeatureName, LayerFeature>,
    val freeScales: Map<Aes, FreeScale>,
    val inheritsBindings: Boolean = true
)
