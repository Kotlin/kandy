/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir

import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.FreeScale
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

/**
 * [Layer] is a collection of data and mappings from it.
 * It is characterized by its [Geom].
 *
 * @param datasetIndex index of layer dataset in the [Plot.datasets].
 * @param geom the [Geom] that describes this layer.
 * @param mappings the [Map] of the mappings; keys are aesthetic attributes,
 * values are mappings on corresponding attributes.
 * @param settings the [Map] of the setting; keys are aesthetic attributes,
 * values are setting of corresponding attributes.
 * @param features the [Map] of the layer features; keys are feature names,
 * values are features with corresponding names.
 */
public data class Layer(
    val datasetIndex: Int,
    val geom: Geom,
    val mappings: Map<AesName, Mapping>,
    val settings: Map<AesName, Setting>,
    val features: Map<FeatureName, LayerFeature>,
    val freeScales: Map<AesName, FreeScale>,
)
