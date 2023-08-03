package org.jetbrains.kotlinx.kandy.dsl.contexts

import io.mockk.mockk
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
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
            override val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
            override val requiredAes: Set<AesName> = setOf()
            override val plotContext: PlotContext = mockk()
            override val datasetIndex: Int = 0
            override val bindingCollector: BindingCollector = BindingCollector()
            override val datasetHandler: DatasetHandler = mockk()
        }

        val layer = layerContextInterface.toLayer(mockGeom, layersInheritMappings)

        assertEquals(layerContextInterface.datasetIndex, layer.datasetIndex)
        assertEquals(mockGeom, layer.geom)
        assertEquals(layerContextInterface.bindingCollector.mappings, layer.mappings)
        assertEquals(layerContextInterface.bindingCollector.settings, layer.settings)
        assertEquals(layerContextInterface.bindingCollector.freeScales, layer.freeScales)
        assertEquals(layerContextInterface.layerFeatures, layer.features)
        assertEquals(layersInheritMappings, layer.inheritsBindings)
    }
}