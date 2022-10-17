/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreeScale

/**
 * Layer is a collection of data and mappings from it.
 * It is characterized by its [Geom].
 *
 * @param data the dataset of this layer.
 * @param geom the [Geom] that describes this layer.
 * @param mappings the [Map] of the mappings; keys are aesthetic attributes,
 * values are mappings on corresponding attributes.
 * @param settings the [Map] of the setting; keys are aesthetic attributes,
 * values are setting of corresponding attributes.
 * @param features the [Map] of the layer features; keys are feature names,
 * values are features with corresponding names.
 */

public data class Layer(
    val data: TableData?,
    val geom: Geom,
    val mappings: Map<AesName, Mapping>,
    val settings: Map<AesName, Setting>,
    val features: Map<FeatureName, LayerFeature> = emptyMap(),
    val freeScales: Map<AesName, FreeScale> = emptyMap(),
)
