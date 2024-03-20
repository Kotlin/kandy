/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.contexts

import io.mockk.mockk
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for LayerContextInterface
 */
class LayerContextInterfaceTest {

    @Test
    fun `test toLayer`() {
        val mockGeom = mockk<Geom>()
        val layersInheritMappings = true

        val layerContextInterface = object : LayerContextInterface {
            override val geom: Geom = mockGeom
            override val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
            override val requiredAes: Set<Aes> = setOf()
            override fun toLayer(layersInheritMappings: Boolean): Layer {
                return Layer(
                    0,
                    geom,
                    bindingCollector.mappings,
                    bindingCollector.settings,
                    layerFeatures,
                    bindingCollector.freeScales,
                    layersInheritMappings
                )
            }

            override val bindingCollector: BindingCollector = BindingCollector()
        }

        val layer = layerContextInterface.toLayer(layersInheritMappings)

        assertEquals(mockGeom, layer.geom)
        assertEquals(layerContextInterface.bindingCollector.mappings, layer.mappings)
        assertEquals(layerContextInterface.bindingCollector.settings, layer.settings)
        assertEquals(layerContextInterface.bindingCollector.freeScales, layer.freeScales)
        assertEquals(layerContextInterface.layerFeatures, layer.features)
        assertEquals(layersInheritMappings, layer.inheritsBindings)
    }
}